/**
 *
 */
package ninja.mahbub.astrojava;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Mahbub Zaman
 * The algorithm is taken from
 * http://edwilliams.org/sunrise_sunset_algorithm.htm
 */
public class Sun {

    final static String SUNRISE = "sunrise";
    final static String SUNSET = "sunset";
    // offical = 90 degrees 50'
    final static double ZENITH = 90.8333;
    public boolean isSunRise;
    public boolean isSunSet;

    CalendarHelper calendarHelper = new CalendarHelper();

    public double getLngHour(double longitude) {
        return longitude / 15;
    }

    public double getApproximateTimeByConvertingLongitudeToHour(double lngHour, int dayOfYear, int constant) {
        return (dayOfYear + ((constant - lngHour) / 24));
    }

    public double getApproximateTimeForSunrise(double lngHour, int dayOfYear) {
        return getApproximateTimeByConvertingLongitudeToHour(lngHour, dayOfYear, 6);
    }

    public double getApproximateTimeForSunset(double lngHour, int dayOfYear) {
        return getApproximateTimeByConvertingLongitudeToHour(lngHour, dayOfYear, 18);
    }

    public double meanAnomaly(double approximateTime) {
        return (0.9856 * approximateTime) - 3.289;
    }

    public double trueLongitude(double meanAnomaly) {
        // L potentially needs to be adjusted into the range [0,360) by adding/subtracting 360
        double L = meanAnomaly + (1.916 * Math.sin(meanAnomaly * MathHelper.DEGREE_TO_RADIAN))
                + (0.020 * Math.sin(2 * meanAnomaly * MathHelper.DEGREE_TO_RADIAN)) + 282.634;

        if (L > 360) {
            L = L - 360;
        } else if (L < 0) {
            L = L + 360;
        }

        return L;
    }

    public double rightAscension(double trueLongitude) {
        // RA potentially needs to be adjusted into the range [0,360) by adding/subtracting 360
        double RA = MathHelper.RADIAN_TO_DEGREE * Math.atan(0.91764 * Math.tan(trueLongitude * MathHelper.DEGREE_TO_RADIAN));
        if (RA > 360) {
            RA = RA - 360;
        } else if (RA < 0) {
            RA = RA + 360;
        }

        // right ascension value needs to be in the same quadrant as L
        double Lquadrant = (Math.floor(trueLongitude / 90)) * 90;
        double RAquadrant = (Math.floor(RA / 90)) * 90;
        RA = RA + (Lquadrant - RAquadrant);

        // right ascension value needs to be converted into hours
        return RA / 15;
    }

    public double[] declination(double trueLongitude) {

        double sinDec = 0.39782 * Math.sin(trueLongitude * MathHelper.DEGREE_TO_RADIAN);
        double cosDec = Math.cos(Math.asin(sinDec));

        return new double[]{sinDec, cosDec};
    }

    public double localHourAngle(double sinDec, double cosDec, double latitude, String eventType) {

        isSunRise = true;
        isSunSet = true;
        double cosH = (Math.cos(ZENITH * MathHelper.DEGREE_TO_RADIAN) - (sinDec * Math.sin(latitude * MathHelper.DEGREE_TO_RADIAN))) / (cosDec * Math.cos(latitude * MathHelper.DEGREE_TO_RADIAN));

        if (eventType.equalsIgnoreCase(SUNRISE) && cosH > 1) {
            // the sun never rises on this location(on the specified date)
            // Polar night
            isSunRise = false;
        } else if (eventType.equalsIgnoreCase(SUNSET) && cosH < -1) {
            // the sun never sets on this location(on the specified date)
            // Polar day
            isSunSet = false;
        }

        // finish calculating H and convert into hours
        double H = 0;
        if (eventType.equalsIgnoreCase(SUNRISE))
            H = 360 - MathHelper.RADIAN_TO_DEGREE * Math.acos(cosH);
        else if (eventType.equalsIgnoreCase(SUNSET))
            H = MathHelper.RADIAN_TO_DEGREE * Math.acos(cosH);

        return H / 15;
    }

    public double localMeanTime(double localHourAngle, double rightAscension, double approximateTime) {
        return localHourAngle + rightAscension - (0.06571 * approximateTime) - 6.622;
    }

    public long toUTC(double localMeanTime, double lngHour) {
        double UT = localMeanTime - lngHour;
        if (UT > 24) {
            UT = UT - 24;
        } else if (UT < 0) {
            UT = UT + 24;
        }
        // convert to milliseconds
        return (long) (UT * 3600 * 1000);
    }

    public String toLocalTimeZone(long utc) {
        return (new SimpleDateFormat("hh:mm:ss a z")).format(new Date(utc));
    }

    private String commonCalc(double latitude, double longitude, String date, String eventType, String timeZone) {
        // 1. first calculate the day of the year
        Calendar calendar = calendarHelper.getCalendarDate(date);
        int dayOfYear = 0;
        if (calendar != null) {
            dayOfYear = calendarHelper.getDayOfYear(calendar);
        } else {
            return "unparseable date";
        }
        // 2. convert the longitude to hour value and calculate an approximate time
        double lngHour = getLngHour(longitude);
        double approximateTime = -1;
        if (eventType.equals(SUNRISE)) {
            approximateTime = getApproximateTimeForSunrise(lngHour, dayOfYear);
        } else if (eventType.equals(SUNSET)) {
            approximateTime = getApproximateTimeForSunset(lngHour, dayOfYear);
        }
        // 3. calculate the Sun's mean anomaly
        double meanAnomaly = meanAnomaly(approximateTime);
        // 4. calculate the Sun's true longitude
        double trueLongitude = trueLongitude(meanAnomaly);
        // 5. calculate the Sun's right ascension
        double rightAscension = rightAscension(trueLongitude);
        // 6. calculate the Sun's declination
        double declination[] = declination(trueLongitude);
        // 7. calculate the Sun's local hour angle
        double localHourAngle = localHourAngle(declination[0], declination[1], latitude, eventType);

        if (!isSunRise) {
            return "no sunrise";
        } else if (!isSunSet) {
            return "no sunset";
        }

        // 8. calculate local mean time of rising/setting
        double localMeanTime = localMeanTime(localHourAngle, rightAscension, approximateTime);
        // 9. adjust back to UTC
        long UTC = toUTC(localMeanTime, lngHour);
        // 10. convert UT value to local time zone of latitude/longitude
        String localTime = toLocalTimeZone(UTC);

        if (timeZone.equalsIgnoreCase(CalendarHelper.TIME_ZONE_UTC)) {
            return "" + UTC;
        } else if (timeZone.equalsIgnoreCase(CalendarHelper.TIME_ZONE_LOCAL)) {
            return localTime;
        } else {
            return localTime;
        }
    }

    public String getSunrise(double latitude, double longitude, String date, String timeZone) {
        if (latitude >= -91 && latitude <= 90 && longitude >= -180 && longitude <= 180) {
            return commonCalc(latitude, longitude, date, SUNRISE, timeZone);
        } else {
            return "invalid latitude or longitude";
        }
    }

    public String getSunrise(double latitude, double longitude, String timeZone) {
        return getSunrise(latitude, longitude, calendarHelper.getTodaysDate(), timeZone);
    }

    public String getSunrise(String cityName, String countryName, String date, String timeZone) {
        Cities cities = new Cities();
        double latLong[] = cities.getLatLong(cityName, countryName);
        if (latLong[0] == 91 && latLong[1] == 181) {
            return "city not found";
        } else {
            return getSunrise(latLong[0], latLong[1], date, timeZone);
        }
    }

    public String getSunrise(String cityName, String countryName, String timeZone) {
        return getSunrise(cityName, countryName, calendarHelper.getTodaysDate(), timeZone);
    }

    public String getSunset(double latitude, double longitude, String date, String timeZone) {
        return commonCalc(latitude, longitude, date, SUNSET, timeZone);
    }

    public String getSunset(double latitude, double longitude, String timeZone) {
        return getSunset(latitude, longitude, calendarHelper.getTodaysDate(), timeZone);
    }

    public String getSunset(String cityName, String countryName, String date, String timeZone) {
        Cities cities = new Cities();
        double latLong[] = cities.getLatLong(cityName, countryName);
        if (latLong[0] == 91 && latLong[1] == 181) {
            return "city not found";
        } else {
            return getSunset(latLong[0], latLong[1], date, timeZone);
        }
    }

    public String getSunset(String cityName, String countryName, String timeZone) {
        return getSunset(cityName, countryName, calendarHelper.getTodaysDate(), timeZone);
    }
}