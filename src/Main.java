import cityparser.City;
import cityparser.CityParser;
import cityparser.Data;
import utils.Chromosome;

import java.util.List;
import java.util.Vector;

/**
 * Created by up201304205 on 01-03-2016.
 */
public class Main {
    /* File to store the city info */
    private final static String fileName = "cities.ser";
    public static void main (String args[]) {
        CityParser parser = new CityParser();
        Data data = parser.getData(fileName);
        if (data.getCities() != null) {
            data.getCities().forEach(System.out::println);
            for(int i = 0; i < data.getCities().size(); ++i) {
                System.out.println(data.getCities().get(0).getName() + " - " + data.getCities().get(i).getName() + ": " + data.getDistances().get(0).get(i) / 1000);
            }
            System.out.println("Nº total de concelhos: " + data.getCities().size());
        }
        /*GeneticAlgorithmTest ga = new GeneticAlgorithmTest();
        Chromosome bestChromosome = ga.run();
        System.out.println("Solution: " + bestChromosome);
        System.out.println("Value: " + bestChromosome.getValue());*/
    }
}
