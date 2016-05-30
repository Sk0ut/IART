package test.genetic_algorithm;

import Core.DataSet;
import cityparser.CityParser;
import cityparser.Data;

public class PopulationVariationTest {

    public static void main() {
        CityParser parser = new CityParser();
        Data data = parser.getData("cities.ser");

        DataSet dataSet = new DataSet(data, 1000, 500, 50000, 20);


    }
}
