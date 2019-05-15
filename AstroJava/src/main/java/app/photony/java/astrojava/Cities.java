package app.photony.java.astrojava;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Mahbub Zaman
 * https://simplemaps.com/data/world-cities
 */

public class Cities {

    private ArrayList<String> cityList = new ArrayList<String>();

    public Cities() {

        try {
            // need to fix the path issue
            File file = new File("AstroJava/src/main/resources/cities.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String city;
            while (true) {
                if (!((city = br.readLine()) != null))
                    break;
                cityList.add(city);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public double[] getLatLong(String cityName, String countryName) {
        String city[];
        for (int i = 0; i < cityList.size(); ++i) {
            city = cityList.get(i).split("/");
            if (cityName.equalsIgnoreCase(city[0]) && countryName.equalsIgnoreCase(city[3])) {
                return new double[]{Double.parseDouble(city[1]), Double.parseDouble(city[2])};
            }
        }
        return new double[]{91, 181};
    }

    public ArrayList<String> getCities() {
        return (ArrayList<String>) this.cityList.clone();
    }
}