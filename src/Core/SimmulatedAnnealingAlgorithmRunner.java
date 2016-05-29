package Core;

import GUI.AlgorithmUI;
import algorithms.DeadPopulationException;
import algorithms.GeneticAlgorithm;
import cityparser.City;
import cityparser.Data;
import utils.Chromosome;
import utils.DataSet;

import java.util.LinkedList;
import java.util.List;
public class SimmulatedAnnealingAlgorithmRunner extends  AlgorithmRunner{



        private DataSet dataSet;
        private boolean finished;

        public SimmulatedAnnealingAlgorithmRunner(Data data, int numTribunals, int maxDistance, int populationSize, int settings) {
            super(data, numTribunals);

            dataSet = new DataSet(data, 1000, 500, maxDistance, numTribunals);
            finished = false;
        }

        @Override
        public List<City> getCurrentSolution() {
            Chromosome best = simmulatedAnnealing.getBestChromosomeOverall();
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


}
