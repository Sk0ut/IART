package test.simulated_annealing;


import Core.DataSet;
import algorithms.SimulatedAnnealing;
import algorithms.simulated_annealing.OneRandomTribunalStateTransition;
import algorithms.simulated_annealing.State;
import algorithms.simulated_annealing.StateTransitionFunction;
import cityparser.CityParser;
import cityparser.Data;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;

public class coolingRateVariationTest {
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        CityParser parser = new CityParser();
        Data data = parser.getData("cities.ser");

        int numTribunals = 20;

        DataSet dataSet = new DataSet(data, 1000, 500, 75000, numTribunals);

        StateTransitionFunction function = new OneRandomTribunalStateTransition(numTribunals, data.getCities().size());

        PrintWriter writer = new PrintWriter("coolRateVar.csv", "UTF-8");

        int iterations = 30;

        for(double coolingRate = 0.01; coolingRate <= 1; coolingRate += 0.01) {

            SimulatedAnnealing algorithm = new SimulatedAnnealing(function, 100, coolingRate, dataSet);
            BigInteger accumulatedTime =  BigInteger.ZERO;
            BigInteger accumulatedFitness = BigInteger.ZERO;


            for (int i = 0; i < iterations; ++i) {
                long begin = System.nanoTime();
                State result = algorithm.run();
                long end = System.nanoTime();

                double fitnessValue = (long) result.getValue();
                long elapsedTime = (long) ((end - begin) / 1000000.0);

                accumulatedFitness = accumulatedFitness.add(BigInteger.valueOf((long)fitnessValue));
                accumulatedTime = accumulatedTime.add(BigInteger.valueOf(elapsedTime));
            }

            long fitnessValue = accumulatedFitness.divide(BigInteger.valueOf(iterations)).longValue();
            long elapsedTime = accumulatedTime.divide(BigInteger.valueOf(iterations)).longValue();

            writer.println("" + (int)(coolingRate*100) + "," + fitnessValue + "," + elapsedTime);
        }

        writer.close();
    }
}
