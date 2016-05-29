package Core;

import GUI.AlgorithmUI;
import algorithms.DeadPopulationException;
import algorithms.GeneticAlgorithm;
import algorithms.SimmulatedAnnealing;
import cityparser.City;
import cityparser.Data;
import utils.Chromosome;
import utils.DataSet;
import utils.State;

import java.util.LinkedList;
import java.util.List;
public class SimmulatedAnnealingAlgorithmRunner extends  AlgorithmRunner{

    private DataSet dataSet;
    private boolean finished;
    private SimmulatedAnnealing simmulatedAnnealing;


    public SimmulatedAnnealingAlgorithmRunner(Data data, int numTribunals, int maxDistance) {
        super(data, numTribunals);

        dataSet = new DataSet(data, 1000, 500, maxDistance, numTribunals);
        finished = false;
    }

    @Override
    public List<City> getCurrentSolution() {
        State best = simmulatedAnnealing.getBestState();
        if (best == null)
            return null;
        return dataSet.getTribunals(best);
    }

    @Override
    public double getCurrentFitnessValue() {
        return dataSet.evaluate(simmulatedAnnealing.getBestState());
    }

    @Override
    public int getCurrentIteration() {
        return simmulatedAnnealing.getIterations();
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
            State initialState = new State(getNumTribunals());
            simmulatedAnnealing.run(initialState);
            successful = true;
        }
        finished = true;
    }
}


}
