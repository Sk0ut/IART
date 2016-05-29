package Core;

import algorithms.SimulatedAnnealing;
import algorithms.simulated_annealing.OneRandomTribunalStateTransition;
import algorithms.simulated_annealing.State;
import cityparser.City;
import cityparser.Data;

import java.util.List;
public class SimmulatedAnnealingAlgorithmRunner extends  AlgorithmRunner{

    private DataSet dataSet;
    private boolean finished;
    private SimulatedAnnealing simulatedAnnealing;


    public SimmulatedAnnealingAlgorithmRunner(Data data, int numTribunals, int maxDistance, int temperature, double coolingRate) {
        super(data, numTribunals);

        dataSet = new DataSet(data, 1000, 500, maxDistance, numTribunals);
        finished = false;

        OneRandomTribunalStateTransition function = new OneRandomTribunalStateTransition(numTribunals, data.getCities().size());
        simulatedAnnealing = new SimulatedAnnealing(function, temperature, coolingRate, dataSet);
    }

    @Override
    public List<City> getCurrentSolution() {
        State best = simulatedAnnealing.getBestState();
        if (best == null)
            return null;
        return dataSet.getTribunals(best);
    }

    @Override
    public double getCurrentFitnessValue() {
        return dataSet.evaluate(simulatedAnnealing.getBestState());
    }

    @Override
    public int getCurrentIteration() {
        return simulatedAnnealing.getIterations();
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
            simulatedAnnealing.run();
            successful = true;
        }
        finished = true;
    }
}