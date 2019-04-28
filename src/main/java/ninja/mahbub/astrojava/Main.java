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
		print (sun.getSunrise ("Sydney", "Australia", "LOCAL")); // 06:27:08 AM AEST
		print (sun.getSunset ("Sydney", "Australia", "LOCAL"));  // 05:18:11 PM AEST

		print("------------");

		print (sun.getSunrise ("Sydney", "Australia","28-04-2019", "LOCAL"));
		print (sun.getSunset ("Sydney", "Australia","28-04-2019", "LOCAL"));

		print("------------");

		print (sun.getSunrise (latitude, longitude, "LOCAL")); // 06:26:57 AM AEST
		print (sun.getSunset (latitude, longitude, "LOCAL"));  // 05:18:10 PM AEST

		print("------------");

		print (sun.getSunrise (latitude, longitude, "28-04-2019", "LOCAL")); // 06:26:57 AM AEST
		print (sun.getSunset (latitude, longitude, "28-04-2019", "LOCAL"));  // 05:18:10 PM AEST

		print("------------");

		print (sun.getSunrise ("Dhaka", "Bangladesh","28-04-2019", "UTC"));
		print (sun.getSunset ("Dhaka", "Bangladesh","28-04-2019", "UTC"));

		print("------------");

		print(sun.getSunrise ("Zone-1", "Mars", "28-04-2019", "LOCAL"));
		print(sun.getSunrise ("Sydney", "Australia", "28-04-nnnn", "LOCAL"));
	}

	public static void print(String s) {
		System.out.println(s);
	}
}
