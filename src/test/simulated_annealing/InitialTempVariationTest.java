package test.simulated_annealing;

import Core.DataSet;
import algorithms.GeneticAlgorithm;
import algorithms.SimulatedAnnealing;
import algorithms.simulated_annealing.OneRandomTribunalStateTransition;
import algorithms.simulated_annealing.State;
import algorithms.simulated_annealing.StateTransitionFunction;
import cityparser.CityParser;
import cityparser.Data;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Locale;

public class InitialTempVariationTest {

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        CityParser parser = new CityParser();
        Data data = parser.getData("cities.ser");

        int numTribunals = 20;

        DataSet dataSet = new DataSet(data, 1000, 500, 75000, numTribunals);

        StateTransitionFunction function = new OneRandomTribunalStateTransition(numTribunals, data.getCities().size());

        PrintWriter writer = new PrintWriter("initTempVar.csv", "UTF-8");

        for(int initialTemperature = 100; initialTemperature <= 10000; initialTemperature += 100) {
            SimulatedAnnealing algorithm = new SimulatedAnnealing(function, initialTemperature, 0.01, dataSet);
            long begin = System.nanoTime();
            State result = algorithm.run();
            long end = System.nanoTime();

            double fitnessValue = result.getValue();
            long elapsedTime = (long) ((end - begin) / 1000000.0);

            writer.println("" + initialTemperature + ". " + String.format("%f", fitnessValue) + ". " + elapsedTime);
        }

        writer.close();
    }
}
