package genetic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by up201304205 on 08-03-2016.
 */
public class GeneticAlgorithm {
    List<Pair> chromosomes;
    EvaluationFunction function;

    public GeneticAlgorithm(int length, int initialPopulation, EvaluationFunction function){
        chromosomes = new ArrayList<>();
        this.function = function;
        for(int chromosome = 0; chromosome < initialPopulation; ++chromosome){
            chromosomes.add(new Pair(new Chromosome(length)));
        }
    }

    private void evaluation(){
        for(Pair chromosome : chromosomes) {
            chromosome.value = function.evaluate(chromosome.c);
        }
    }

    private void selection(){
        int totalValue = 0;
        List<Integer> probabilities = new ArrayList<>();
        for(Pair chromosome : chromosomes){
            totalValue += chromosome.value;
        }
        for(Pair chromosome : chromosomes){
            probabilities.add(chromosome.value/totalValue);
        }
        float prob = new Random().nextFloat();
    }

    class Pair {
        Chromosome c;
        int value;

        Pair(Chromosome c){
            this.c = c;
        }

    }
}
