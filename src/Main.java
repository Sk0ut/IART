import java.io.*;
import java.util.List;

/**
 * Created by up201304205 on 01-03-2016.
 */
public class Main {
    public static void main (String args[]) {
        List<City> cities;
        /*CityParser parser = new CityParser();
        cities = parser.getCities();
        serializeCities(cities);*/
        cities = unserializeCities();
        if (cities != null)
            cities.forEach(System.out::println);

    }

    private static void serializeCities(List<City> cities) {
        try {
            FileOutputStream fileOut = new FileOutputStream("cities.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(cities);
            out.close();
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<City> unserializeCities() {
        List<City> cities;
        try {
            FileInputStream fileIn = new FileInputStream("cities.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            cities = (List<City>) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return cities;
    }
}
