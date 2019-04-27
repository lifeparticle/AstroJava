/**
 * 
 */
package ninja.mahbub.astrojava;

/**
 * @author Mahbub Zaman
 * 
 * The algorithm is taken from
 * http://edwilliams.org/sunrise_sunset_algorithm.htm
 *
 */
public class Main {

	public static void main(String[] args) {

		// Sydney
		double latitude = -33.868;
		double longitude = 151.21;
		Sun sun = new Sun();
		CalendarHelper calendarHelper = new CalendarHelper();

		// 1. first calculate the day of the year
		int dayOfYear = calendarHelper.getDayOfYear();
		
		int i = calendarHelper.getDayOfYear(calendarHelper.getCalendarDate("17-06-1991"));

		print(""+i);
		
		
		// today
		sun.getSunrise (latitude, longitude, dayOfYear);
		// returns today's sunrise
		sun.getSunrise (latitude, longitude);
		sun.getSunrise ("Sydney", dayOfYear);
		// returns today's sunrise
		sun.getSunrise ("Sydney");
		

		print("" + dayOfYear);

		// 2. convert the longitude to hour value and calculate an approximate time
		
		double approximateTime = sun.getApproximateTimeForSunrise(longitude, dayOfYear);

		print("" + approximateTime);

		// 3. calculate the Sun's mean anomaly
		
		double meanAnomaly = sun.meanAnomaly(approximateTime);

		// 4. calculate the Sun's true longitude
		double trueLongitude = sun.trueLongitude(meanAnomaly);

		// 5. calculate the Sun's right ascension
		double rightAscension = sun.rightAscension(trueLongitude);

		// 6. calculate the Sun's declination

		// 7a. calculate the Sun's local hour angle

		// 7b. finish calculating H and convert into hours

		// 8. calculate local mean time of rising/setting

		// 9. adjust back to UTC

		// 10. convert UT value to local time zone of latitude/longitude

	}

	public static void print(String s) {
		System.out.println(s);
	}
}
