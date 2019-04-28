package ninja.mahbub.astrojava;

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

    public ArrayList<String> cities = new ArrayList<String>();
    public Cities() {
        File file = new File("src/main/java/ninja/mahbub/astrojava/cities.txt");
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String city;
            while (true) {
                if (!((city = br.readLine()) != null))
                    break;
                cities.add(city);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public double[] getLatLong(String cityName, String countryName) {
        String city [];
        for (int i = 0; i < cities.size(); ++i) {
            city = cities.get(i).split("/");
            if (cityName.equalsIgnoreCase(city[0]) && countryName.equalsIgnoreCase(city[3])) {
                return new double[] {Double.parseDouble(city[1]), Double.parseDouble(city[2])};
            }
        }
        return new double[] {91, 181};
    }
}