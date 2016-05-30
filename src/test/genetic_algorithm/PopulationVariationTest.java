package test.genetic_algorithm;

import Core.DataSet;
import algorithms.GeneticAlgorithm;
import algorithms.SimulatedAnnealing;
import algorithms.genetic_algorithm.Chromosome;
import algorithms.genetic_algorithm.DeadPopulationException;
import algorithms.simulated_annealing.OneRandomTribunalStateTransition;
import algorithms.simulated_annealing.State;
import algorithms.simulated_annealing.StateTransitionFunction;
import cityparser.CityParser;
import cityparser.Data;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;

public class PopulationVariationTest {

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        CityParser parser = new CityParser();
        Data data = parser.getData("cities.ser");

        int numTribunals = 20;

        DataSet dataSet = new DataSet(data, 1000, 500, 75000, numTribunals);

        int settings = GeneticAlgorithm.ELITISM | GeneticAlgorithm.ROULETTESELECTION | GeneticAlgorithm.ROULETTECROSSOVER;



        PrintWriter writer = new PrintWriter("initPopVar.csv", "UTF-8");

        int iterations = 5;

        for(int initialPopulation = 10; initialPopulation <= 150; initialPopulation += 10) {

            GeneticAlgorithm algorithm = new GeneticAlgorithm(dataSet, dataSet.chromosomeLength(), initialPopulation, settings);
            BigInteger accumulatedTime =  BigInteger.ZERO;
            BigInteger accumulatedFitness = BigInteger.ZERO;

            System.out.println("current population: " + initialPopulation);

            for (int i = 0; i < iterations; ++i) {
                boolean successful = false;
                System.out.println("i: " + i);

                double fitnessValue = 0;
                long elapsedTime = 0;

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

                accumulatedFitness = accumulatedFitness.add(BigInteger.valueOf((long)fitnessValue));
                accumulatedTime = accumulatedTime.add(BigInteger.valueOf(elapsedTime));
            }

            long fitnessValue = accumulatedFitness.divide(BigInteger.valueOf(iterations)).longValue();
            long elapsedTime = accumulatedTime.divide(BigInteger.valueOf(iterations)).longValue();

            writer.println("" + initialPopulation + "," + (long)fitnessValue + "," + elapsedTime);
        }

        writer.close();
    }
}
