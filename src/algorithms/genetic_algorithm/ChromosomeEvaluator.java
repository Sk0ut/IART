package algorithms.genetic_algorithm;

import algorithms.genetic_algorithm.Chromosome;

public interface ChromosomeEvaluator {
    int chromosomeLength();
    double evaluate(Chromosome chromosome);
}
