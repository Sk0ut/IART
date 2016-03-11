import cityparser.City;
import cityparser.CityParser;
import genetic.Chromosome;

import java.util.List;

/**
 * Created by up201304205 on 01-03-2016.
 */
public class Main {
    /* File to store the city info */
    private final static String fileName = "cities.ser";
    public static void main (String args[]) {
        CityParser parser = new CityParser();
        List<City> cities = parser.getCities(fileName);
        /*if (cities != null) {
            cities.forEach(System.out::println);
            System.out.println("Nº total de concelhos: " + cities.size());
        }*/
        GeneticAlgorithmTest ga = new GeneticAlgorithmTest();
        Chromosome bestChromosome = ga.run();
        System.out.println("Solution: " + bestChromosome);
        System.out.println("Value: " + bestChromosome.getValue());
    }
}
