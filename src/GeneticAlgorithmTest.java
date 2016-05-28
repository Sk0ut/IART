import algorithms.*;
import utils.Chromosome;
import utils.ChromosomeEvaluator;
import utils.DataSet;

public class GeneticAlgorithmTest extends GeneticAlgorithm {

    private ChromosomeEvaluator evaluator;

    public GeneticAlgorithmTest(ChromosomeEvaluator evaluator, int populationSize, int settings) {
        super(evaluator.chromosomeLength(), populationSize, settings);
        this.evaluator = evaluator;
        setUniformRate(0.5);
        setElitismSize(100);
        setMutationRate(0.002);
    }

    @Override
    public double evaluate(Chromosome chromosome) {
        return evaluator.evaluate(chromosome);
    }

    @Override
    public boolean stopCondition() {
        return getIterations() > 20;
    }
}
