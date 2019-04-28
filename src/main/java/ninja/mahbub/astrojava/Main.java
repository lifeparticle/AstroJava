/**
 * 
 */
package ninja.mahbub.astrojava;

/**
 * @author Mahbub Zaman
 */
public class Main {

	public static void main(String[] args) {
		// Sydney
		double latitude = -33.868;
		double longitude = 151.21;

		Sun sun = new Sun();
		print (sun.getSunrise ("Sydney", "Australia","28-04-2019", "LOCAL"));
		print (sun.getSunset ("Sydney", "Australia","28-04-2019", "LOCAL"));

		print (sun.getSunrise ("Dhaka", "Bangladesh","28-04-2019", "UTC"));
		print (sun.getSunset ("Dhaka", "Bangladesh","28-04-2019", "UTC"));
		// returns today's sunrise
		//sun.getSunrise (latitude, longitude);
		//sun.getSunrise ("Sydney", dayOfYear);
		// returns today's sunrise
		//sun.getSunrise ("Sydney");
	}

	public static void print(String s) {
		System.out.println(s);
	}
}
