import algorithms.DeadPopulationException;
import algorithms.GeneticAlgorithm;
import cityparser.City;
import cityparser.CityParser;
import cityparser.Data;
import utils.Chromosome;
import utils.DataSet;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.*;


public class Main {
    /* File to store the city info */
    private final static String fileName = "cities.ser";
    public static void main (String args[]) {

        CityParser parser = new CityParser();
        Data data = parser.getData(fileName);
        DataSet dataSet = new DataSet(data, 1000, 500, 50000, 40);
        Chromosome chromosome = new Chromosome(dataSet.chromosomeLength());
        /*
        if (data.getCities() != null) {
            data.getCities().forEach(System.out::println);
            for(int i = 0; i < data.getCities().size(); ++i) {
                System.out.println(data.getCities().get(0).getName() + " - " + data.getCities().get(i).getName() + ": " + data.getDistances().get(0).get(i) / 1000);
            }
            for (int i = 0; i < dataSet.getNearestCities().get(0).size(); ++i) {
                System.out.println(data.getCities().get(dataSet.getNearestCities().get(0).get(i)).getName());
            }
            System.out.println("Nº total de concelhos: " + data.getCities().size());
        }*/
        GeneticAlgorithmTest ga = new GeneticAlgorithmTest(dataSet, 2000, GeneticAlgorithm.ELITISM | GeneticAlgorithm.ROULETTESELECTION | GeneticAlgorithm.ROULETTECROSSOVER);
        Chromosome bestChromosome = null;

        boolean sucessful = false;
        while (!sucessful) {
            try {
                bestChromosome = ga.run();
                sucessful = true;
            } catch (DeadPopulationException e) {
                System.out.println("The testing population is dead");
            }
        }
        System.out.println("Solution: " + bestChromosome);
        for (City city : dataSet.getTribunals(bestChromosome)) {
            System.out.println(city.getName());
        }
        System.out.println("Evaluation: " + dataSet.evaluate(bestChromosome));
        System.out.println("Value: " + bestChromosome.getValue());
    }

    public static void test() {
        byte[] bytes = new byte[]{0b01001111, 0b10010};
        for (byte aByte : bytes) {
            System.out.println(aByte);
        }
        BitSet set = BitSet.valueOf(bytes);
        System.out.println(set);


        BitSet bitSet = BitSet.valueOf(bytes);
        BitSet tribunals = new BitSet();

        for (int i = 0; i < 4; ++i) {
            BitSet subSet = bitSet.get(i*3, (i+1)*3);

            ByteBuffer buffer = ByteBuffer.wrap(Arrays.copyOf(subSet.toByteArray(), Integer.BYTES)).order(ByteOrder.LITTLE_ENDIAN);
            tribunals.set(buffer.getInt());
        }

        System.out.println(tribunals);
        System.out.println(tribunals.cardinality());
    }
}
