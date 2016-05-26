/**
 * Created by Afonso on 10-03-2016.
 */

import algorithms.*;
import utils.Chromosome;
import utils.DataSet;

public class GeneticAlgorithmTest extends GeneticAlgorithm {

    private DataSet dataset;

    public GeneticAlgorithmTest(DataSet dataset) {
        super(30, 20, GeneticAlgorithm.UNIFORMCROSSOVER|GeneticAlgorithm.TOURNAMENTSELECTION|GeneticAlgorithm.ELITISM);
        this.dataset = dataset;
        setUniformRate(0.5);
        setElitismSize(1);
        setMutationRate(0.01);
    }

    @Override
    public double evaluate(Chromosome chromosome) {
        return chromosome.cardinality();
    }

    @Override
    public boolean stopCondition() {
        return getBestChromosome().getValue() > 29;
    }
}
