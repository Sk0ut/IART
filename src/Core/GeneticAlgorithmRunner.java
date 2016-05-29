package Core;

import algorithms.GeneticAlgorithm;
import algorithms.genetic_algorithm.DeadPopulationException;
import cityparser.City;
import cityparser.Data;
import algorithms.genetic_algorithm.Chromosome;

import java.util.List;

public class GeneticAlgorithmRunner extends AlgorithmRunner {
    private DataSet dataSet;
    private boolean finished;

    private GeneticAlgorithm geneticAlgorithm;

    public GeneticAlgorithmRunner(Data data, int numTribunals, int maxDistance, int populationSize, int settings) {
        super(data, numTribunals);

        dataSet = new DataSet(data, 1000, 500, maxDistance, numTribunals);
        finished = false;
        geneticAlgorithm = new GeneticAlgorithm(dataSet, dataSet.chromosomeLength(), populationSize, settings);
    }

    @Override
    public List<City> getCurrentSolution() {
        Chromosome best = geneticAlgorithm.getBestChromosomeOverall();
        if (best == null)
            return null;
        return dataSet.getTribunals(best);
    }

    @Override
    public double getCurrentFitnessValue() {
        return dataSet.evaluate(geneticAlgorithm.getBestChromosomeOverall());
    }

    @Override
    public int getCurrentIteration() {
        return geneticAlgorithm.getIterations();
    }

    @Override
    public boolean isFinished() {
        return finished;
    }

    @Override
    public void run() {
        finished = false;

        boolean successful = false;
        while (!successful) {
            try {
                geneticAlgorithm.run();
                successful = true;
            } catch (DeadPopulationException e) {
                System.out.println("The testing population is dead");
            }
        }

        finished = true;
    }
}
