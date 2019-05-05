package app.photony.java.astrojava;


import java.util.ArrayList;

/**
 * @author Mahbub Zaman
 */
public class Main {

    public static void main(String[] args) {
        // Sydney
        double latitude = -33.92;
        double longitude = 151.1852;

        Sun sun = new Sun();
        print(sun.getSunrise("Sydney", "Australia", "LOCAL")); // 06:27:08 AM AEST
        print(sun.getSunset("Sydney", "Australia", "LOCAL"));  // 05:18:11 PM AEST

        print("------------");

        print(sun.getSunrise("Sydney", "Australia", "29-04-2019", "LOCAL"));
        print(sun.getSunset("Sydney", "Australia", "29-04-2019", "LOCAL"));

        print("------------");

        print(sun.getSunrise(latitude, longitude, "LOCAL")); // 06:26:57 AM AEST
        print(sun.getSunset(latitude, longitude, "LOCAL"));  // 05:18:10 PM AEST

        print("------------");

        print(sun.getSunrise(latitude, longitude, "29-04-2019", "LOCAL")); // 06:26:57 AM AEST
        print(sun.getSunset(latitude, longitude, "29-04-2019", "LOCAL"));  // 05:18:10 PM AEST

        print("------------");

        print(sun.getSunrise("Dhaka", "Bangladesh", "29-04-2019", "UTC"));
        print(sun.getSunset("Dhaka", "Bangladesh", "29-04-2019", "UTC"));

        print("------------");

        print(sun.getSunrise("Zone-1", "Mars", "29-04-2019", "LOCAL"));
        print(sun.getSunrise("Sydney", "Australia", "29-04-nnnn", "LOCAL"));


        print(sun.getSunrise("Kiruna", "Sweden", "29-06-2019", "LOCAL"));
        print(sun.getSunset("Kiruna", "Sweden", "29-06-2019", "LOCAL"));

        print(sun.getSunrise("Kiruna", "Sweden", "12-12-2019", "LOCAL"));
        print(sun.getSunset("Kiruna", "Sweden", "12-12-2019", "LOCAL"));

        print("------------");

        String sunRise = sun.getSunrise("Sydney", "Australia", "LOCAL");
        String sunSet = sun.getSunset("Sydney", "Australia", "LOCAL");
        print(CalendarHelper.getDayLength(sunRise, sunSet));


        print("------------");
        Cities cities = new Cities();
        ArrayList<String> cityList = cities.getCities();
        print(cityList.toString());
    }

    public static void print(String s) {
        System.out.println(s);
    }
}
