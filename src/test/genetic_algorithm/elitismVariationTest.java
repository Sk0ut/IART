package test.genetic_algorithm;

import Core.DataSet;
import algorithms.GeneticAlgorithm;
import algorithms.genetic_algorithm.Chromosome;
import algorithms.genetic_algorithm.DeadPopulationException;
import cityparser.CityParser;
import cityparser.Data;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;

/**
 * Created by Luís on 30/05/2016.
 */
public class elitismVariationTest {

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        CityParser parser = new CityParser();
        Data data = parser.getData("cities.ser");

        int numTribunals = 20;

        DataSet dataSet = new DataSet(data, 1000, 500, 75000, numTribunals);

        int commonSettings = GeneticAlgorithm.ROULETTESELECTION | GeneticAlgorithm.ROULETTECROSSOVER;


        PrintWriter writer = new PrintWriter("elitism.csv", "UTF-8");
        int iterations = 50;
        GeneticAlgorithm algorithm = new GeneticAlgorithm(dataSet, dataSet.chromosomeLength(), 100, commonSettings);
        GeneticAlgorithm elitismAlgorithm = new GeneticAlgorithm(dataSet, dataSet.chromosomeLength(), 100, GeneticAlgorithm.ELITISM | commonSettings);
        for (int i = 0; i < iterations; ++i) {
            boolean successful = false;
            System.out.println("i: " + i);

            double fitnessValue = 0;
            long elapsedTime = 0;
            double elitismFitnessValue = 0;
            double elitismElapsedTime = 0;

            while (!successful) {
                try {
                    long begin = System.nanoTime();
                    Chromosome result = algorithm.run();
                    long end = System.nanoTime();
                    elapsedTime = (long) ((end - begin) / 1000000.0);
                    fitnessValue = (long) result.getValue();
                    successful = true;
                } catch (DeadPopulationException e) {
                    System.out.println("The testing population is dead");
                }
            }

            successful = false;

            while (!successful) {
                try {
                    long begin = System.nanoTime();
                    Chromosome result = elitismAlgorithm.run();
                    long end = System.nanoTime();
                    elitismElapsedTime = (long) ((end - begin) / 1000000.0);
                    elitismFitnessValue = (long) result.getValue();
                    successful = true;
                } catch (DeadPopulationException e) {
                    System.out.println("The testing population is dead");
                }
            }

            writer.println("" + (long)fitnessValue + "," + elapsedTime + "," + (long)elitismFitnessValue + "," + (long) elitismElapsedTime);
        }


        writer.close();
    }
}
