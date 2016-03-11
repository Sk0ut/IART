package genetic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public abstract class GeneticAlgorithm {

    public static final int TOURNAMENTSELECTION = 1;
    public static final int ROULETTESELECTION = 0;
    public static final int SEGMENTCROSSOVER = 2;
    public static final int UNIFORMCROSSOVER = 0;
    public static final int ROULETTECROSSOVER = 4;
    public static final int ELITISM = 8;

    List<Chromosome> chromosomes;

    int selectionType;
    int crossoverType;

    int tournamentSize;
    int elitismSize = 0;
    double uniformRate;
    double mutationRate = 0.01;

    public GeneticAlgorithm(int chromosomeLength, int initialPopulation, int settings){
        chromosomes = new ArrayList<>();
        for(int c = 0; c < initialPopulation; ++c){
            Chromosome chromosome = new Chromosome(chromosomeLength);
            chromosome.randomizeChromosomeGenes();
            chromosomes.add(chromosome);
        }

        evaluation();

        if((settings & TOURNAMENTSELECTION) != 0){
            tournamentSize = initialPopulation/4;
            selectionType = TOURNAMENTSELECTION;
        }
        else
            selectionType = ROULETTESELECTION;

        if((settings & SEGMENTCROSSOVER) != 0)
            crossoverType = SEGMENTCROSSOVER;
        else if ((settings & ROULETTECROSSOVER) != 0)
            crossoverType = ROULETTECROSSOVER;
        else {
            crossoverType = UNIFORMCROSSOVER;
            uniformRate = 0.5;
        }

        if((settings & ELITISM) != 0)
            elitismSize = 1;
    }

    public Chromosome run(){
        int iterations = 0;
        double averageValue = getAverageValue();
        System.out.println("Start: Best Value: " + getBestChromosome().getValue() + " Average Value: " + averageValue);

        while(true){
            breeding();
            evaluation();
            mutation();

            averageValue = getAverageValue();
            ++iterations;
            System.out.println("Iteration " + iterations + ": Best Value: " + getBestChromosome().getValue() + " Average Value: " + averageValue);
            if(stopCondition(iterations, getBestChromosome()))
                return getBestChromosome();
            }
    }

    private double getAverageValue() {
        double averageValue = 0;
        for (Chromosome chromosome : chromosomes) {
            averageValue += chromosome.getValue();
        }
        averageValue = averageValue/chromosomes.size();
        return averageValue;
    }

    public abstract double evaluate(Chromosome chromosome);
    public abstract boolean stopCondition(int iterations, Chromosome bestChromosome);

    public void setTournamentSize(int tournamentSize){
        this.tournamentSize = tournamentSize;
    }

    public void setElitismSize(int elitismSize){
        this.elitismSize = elitismSize;
    }

    public void setUniformRate(double uniformRate){
        this.uniformRate = uniformRate;
    }

    public void setMutationRate(double mutationRate){
        this.mutationRate = mutationRate;
    }

    private Chromosome pairingAndCrossover(){
        Chromosome father, mother;

        if(selectionType == TOURNAMENTSELECTION){
            father = tournamentSelection();
            mother = tournamentSelection();
        }
        else {
            father = rouletteSelection();
            mother = rouletteSelection();
        }

        int length = father.size();
        Chromosome offspring = new Chromosome(length);

        if(crossoverType == UNIFORMCROSSOVER)
            for(int i = 0; i < length; ++i){
                if(Math.random() < uniformRate){
                    offspring.setGene(i, father.getGene(i));
                }
                else{
                    offspring.setGene(i, mother.getGene(i));
                }
            }

        else if (crossoverType == SEGMENTCROSSOVER){
            int crossoverPoint = new Random().nextInt(length-1) + 1;

            for(int i = 0; i < crossoverPoint; ++i){
                offspring.setGene(i, father.getGene(i));
            }
            for(int i = crossoverPoint; i < length; ++i){
                offspring.setGene(i, mother.getGene(i));
            }
        }

        else { /* ROULETTE CROSSOVER */
            double prob = father.getValue() / (father.getValue() + mother.getValue());
            for(int i = 0; i < length; ++i){
                if(Math.random() < prob){
                    offspring.setGene(i, father.getGene(i));
                }
                else{
                    offspring.setGene(i, mother.getGene(i));
                }
            }
        }

        return offspring;
    }

    private void breeding(){
        List<Chromosome> newChromosomes = new ArrayList<>();

        if(elitismSize != 0) {
            Collections.sort(chromosomes);
            for(int i = 0; i < elitismSize; ++i){
                newChromosomes.add(chromosomes.get(i));
            }
        }

        for(int i = elitismSize; i < chromosomes.size(); ++i){
            newChromosomes.add(pairingAndCrossover());
        }

        chromosomes = newChromosomes;
    }

    private void evaluation(){
        for(Chromosome chromosome : chromosomes)
            chromosome.setValue(evaluate(chromosome));
    }

    private void mutation(){
        for (Chromosome chromosome : chromosomes) {
            for (int j = 0; j < chromosome.size(); ++j) {
                if (Math.random() < mutationRate)
                    chromosome.flip(j);
            }
        }
    }

    private Chromosome getBestChromosome() {
        double bestChromosomeValue = 0;
        Chromosome bestChromosome = null;

        for (Chromosome chromosome : chromosomes) {
            if (chromosome.getValue() > bestChromosomeValue) {
                bestChromosomeValue = chromosome.getValue();
                bestChromosome = chromosome;
            }
        }

        return bestChromosome;
    }

    private Chromosome tournamentSelection() {
        List<Chromosome> tournamentChromosomes = new ArrayList<>();

        for(int i = 0; i < tournamentSize; ++i) {
            int id = new Random().nextInt(chromosomes.size());
            tournamentChromosomes.add(chromosomes.get(id));
        }

        double bestValue = 0;
        int bestChromosome = 0;

        for(int i = 0; i < tournamentSize; ++i){
            if(tournamentChromosomes.get(i).getValue() > bestValue){
                bestValue = tournamentChromosomes.get(i).getValue();
                bestChromosome = i;
            }
        }

        return tournamentChromosomes.get(bestChromosome);
    }

    private Chromosome rouletteSelection() {
        int totalValue = 0;
        int currentValue = 0;
        List<Double> probabilities = new ArrayList<>();
        for(Chromosome chromosome : chromosomes){
            totalValue += chromosome.getValue();
        }
        for(Chromosome chromosome : chromosomes){
            probabilities.add((chromosome.getValue() + currentValue)/totalValue);
            currentValue += chromosome.getValue();
        }
        float prob = new Random().nextFloat();

        int i = 0;
        while(true){
            if(probabilities.get(i) > prob)
                break;
            ++i;
        }

        return chromosomes.get(i);
    }
}
