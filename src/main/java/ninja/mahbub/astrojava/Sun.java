/**
 * 
 */
package ninja.mahbub.astrojava;

/**
 * @author Mahbub Zaman
 *
 */
public class Sun {

	final static double degreeToRadian = Math.PI / 180;

	public double getApproximateTimeByConvertingLongitudeToHour(double longitude, int dayOfYear, int constant) {
		double lngHour = longitude / 15;
		return (dayOfYear + ((constant - lngHour) / 24));
	}
	
	CalendarHelper calendarHelper = new CalendarHelper();

	public double getApproximateTimeForSunrise(double longitude, int dayOfYear) {
		return getApproximateTimeByConvertingLongitudeToHour(longitude, dayOfYear, 6);
	}
	
	public double getApproximateTimeForSunset(double longitude, int dayOfYear) {
		return getApproximateTimeByConvertingLongitudeToHour(longitude, dayOfYear, 18);
	}
	
	public String getSunrise(double latitude, double longitude, int dayOfYear) {
		return "";
	}

	public String getSunrise(double latitude, double longitude) {
		return getSunrise(latitude, longitude, calendarHelper.getDayOfYear());
	}

	public String getSunrise(String cityName, int dayOfYear) {
		double latitude = 0;
		double longitude = 0;
		return getSunrise(latitude, longitude, dayOfYear);
	}

	public String getSunrise(String cityName) {
		double latitude = 0;
		double longitude = 0;
		return getSunrise(latitude, longitude, calendarHelper.getDayOfYear());
	}


	public double meanAnomaly(double approximateTime) {
		return (0.9856 * approximateTime) - 3.289;
	}

	public double trueLongitude(double meanAnomaly) {
		// L potentially needs to be adjusted into the range [0,360) by
		// adding/subtracting 360
		double L = meanAnomaly + (1.916 * Math.sin(meanAnomaly * Calc.DEGREE_TO_RADIAN))
				+ (0.020 * Math.sin(2 * meanAnomaly * Calc.DEGREE_TO_RADIAN)) + 282.634;

		if (L > 360) {
			L = L - 360;
		} else if (L < 0) {
			L = L + 360;
		}

		return L;
	}

	public double rightAscension(double trueLongitude) {
		// RA potentially needs to be adjusted into the range [0,360) by
		// adding/subtracting 360
		double RA = Math.atan(0.91764 * Math.tan(trueLongitude * Calc.DEGREE_TO_RADIAN));
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

		double sinDec = 0.39782 * Math.sin(trueLongitude);
		double cosDec = Math.cos(Math.asin(sinDec));

		return new double[] { sinDec, cosDec };
	}

	public double localHourAngle(double sinDec, double cosDec, String method) {
		/*
		double cosH = (cos(zenith) - (sinDec * sin(latitude))) / (cosDec * cos(latitude))
				
		if (cosH >  1) 
		  the sun never rises on this location (on the specified date)
		if (cosH < -1)
		  the sun never sets on this location (on the specified date)

				  
		// finish calculating H and convert into hours
		double H;
		if (method == "SUNRISE")
			H = 360 - acos(cosH);
		else if (method == "SUNSET")
			H = acos(cosH);
		else
			
			
			
			H = H / 15;
			*/
		
		return 0;
	}

}