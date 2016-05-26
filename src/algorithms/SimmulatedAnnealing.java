package algorithms;

import utils.Chromosome;
import utils.OptimizationAlgorithm;

/**
 * Created by Afonso on 21/05/2016.
 */
public abstract class SimmulatedAnnealing extends OptimizationAlgorithm {
    private double temperature;
    private double coolingRate;
    private int chromosomeLength;
    private Chromosome bestChromosome;

    public SimmulatedAnnealing(double temperature, double coolingRate, int chromosomeLength){
        this.temperature = temperature;
        this.coolingRate = coolingRate;
        this.chromosomeLength = chromosomeLength;
    }

    private Chromosome run(){
        while(!stopCondition()){
            Chromosome newCandidate = generateNewChromosome();
            if (acceptNewChromosome(bestChromosome.getValue(), newCandidate.getValue()))
                bestChromosome = newCandidate;
            temperature *= 1-coolingRate;
            incrementIterations();
        }
        return bestChromosome;
    }

    private boolean acceptNewChromosome(double bestValue, double candidateValue) {
        double delta = candidateValue - bestValue;
        return delta > 0 || Math.exp(delta / temperature) > Math.random();
    }

    private Chromosome generateNewChromosome() {
        Chromosome ret = new Chromosome(chromosomeLength);
        ret.randomizeChromosomeGenes();
        ret.setValue(evaluate(ret));
        return ret;
    }
}
