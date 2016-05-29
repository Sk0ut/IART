package algorithms;

import cityparser.City;
import utils.Chromosome;
import utils.OptimizationAlgorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * This class provides an implementation of a Genetic Algorithm. In order to use it, you need to override two methods:
 * evaluate() and stopCondition(). The class supports three main customizable things: the selection algorithm, the
 * crossover algorithm and the option to use elitism or not.
 * The algorithm consists in several steps:
 * 1- Randomize an initial population with size N.
 * 2- Select two parents to pass down their genes (selection phase).
 * 3- Pass genes from both parents to one offspring (crossover phase).
 * 4- Each gene from each offspring has a small chance of being mutated. Go through each gene and mutate it according to
 * the given probability.
 * 5- Check if the end condition applies. If not, return to step 2.
 */
public abstract class GeneticAlgorithm extends OptimizationAlgorithm {

    /**
     * Uses the tournament approach in selecting the two parents. The tournament approach consists in choosing N
     * random chromosomes and picking the best.
     */
    public static final int TOURNAMENTSELECTION = 1;

    /**
     * Uses the roulette approach in selecting the two parents. The roulette approach assigns a probability to each
     * chromosome to be picked, proportional to its value (in other words, value divided by the sum of all values) and
     * then picks one.
     */
    public static final int ROULETTESELECTION = 0;

    /**
     * Uses the segment approach in the crossover phase. The segment approach chooses a random gene N between 1 and L
     * (L being the length of the chromosome) and passes the father's genes from 1 to N and the mother's N to L genes
     * to the offspring.
     */
    public static final int SEGMENTCROSSOVER = 2;

    /**
     * Uses the uniform approach in the crossover phase. In the uniform approach, given a probability P, for each gene N,
     * the father has P chances of passing its Nth gene to the offspring, with the mother having 1-P chances of passing
     * the aforementioned gene.
     */
    public static final int UNIFORMCROSSOVER = 0;

    /**
     * Uses the roulette approach in the crossover phase. In the roulette approach, for each gene N, the father has
     * F/(F+M) chances of passing down its N gene, whereas the mother has M/(F+M) chances of passing down its N gene,
     * F and M being the father and mother's value, respectively.
     */
    public static final int ROULETTECROSSOVER = 4;

    /**
     * Uses elitism. Elitism consists in keeping the N best chromosomes for the next generation.
     */
    public static final int ELITISM = 8;


    /**
    * The current population.
    */
    private List<Chromosome> chromosomes;

    /**
     * The algorithm being used for the selection phase.
     */
    private int selectionType;

    /**
     * The algorithm being used for the crossover phase.
     */
    private int crossoverType;

    /**
     * The size of the tournament. When the tournament algorithm is selected for selection, this determines how many
     * individuals will be placed in the tournament. Defaults to populationSize / 4.
     */
    private int tournamentSize;

    /**
     * The number of individuals to keep for the next generation, should elitism be in use. Defaults to 1 in that case.
     */
    private int elitismSize = 0;

    /**
     * The probability of the father passing its gene down to the offspring instead of the mother in the uniform
     * crossover algorithm. Defaults to 0.5.
     */
    private double uniformRate;

    /**
     * The mutation rate after the creation of a new generation. Defaults to 0.01.
     */
    private double mutationRate = 0.01;

    /**
     * The probabilities list used for the roulette pairing method.
     */
    List<Double> probabilities = null;

    /**
     * The best chromosome in all iterations.
     */
    Chromosome bestChromosomeOverall = null;

    /**
     * The Genetic Algorithm constructor.
     * @param chromosomeLength The length in genes of each chromosome.
     * @param initialPopulation The population length.
     * @param settings Settings. Define the crossover and selection algorithms to use, as well as the use of elitism,
     *                 in this parameter. Defaults to roulette selection, uniform crossover, and no elitism. To define
     *                 them, just use bitwise or with the symbolic constans provided.
     *                 E.g.: GeneticAlgorithm.UNIFORMCROSSOVER|GeneticAlgorithm.TOURNAMENTSELECTION|GeneticAlgorithm.ELITISM
     */



    public GeneticAlgorithm(int chromosomeLength, int initialPopulation, int settings){
        chromosomes = new ArrayList<>();
        for(int c = 0; c < initialPopulation; ++c){
            Chromosome chromosome = new Chromosome(chromosomeLength);
            chromosomes.add(chromosome);
        }

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

    /**
     * Run the algorithm.
     * @return When the algorithm ends, the best chromosome.
     */
    public Chromosome run() throws DeadPopulationException {
        int i = 0;
        while (i < chromosomes.size()) {
            //System.out.println("Generating chromosome " + i + "/" + chromosomes.size());
            chromosomes.get(i).randomizeChromosomeGenes();
            chromosomes.get(i).setValue(evaluate(chromosomes.get(i)));
            //if (chromosomes.get(i).getValue() > 0)
                ++i;
        }

        double averageValue = getAverageValue();
        System.out.println("Start: Best Value: " + getBestChromosome().getValue() + " Average Value: " + averageValue);

        resetIterations();
        while(!stopCondition()) {
            checkDeadPopulation();
            breeding();
            mutation();
            evaluation();

            averageValue = getAverageValue();
            incrementIterations();
            Chromosome chromosome = getBestChromosome();
            System.out.println("Iteration " + getIterations() + ": Best Value: " + chromosome.getValue() + " Average Value: " + averageValue);
        }

        return bestChromosomeOverall;
    }

    private void checkDeadPopulation() throws DeadPopulationException {
        for (Chromosome chromosome: chromosomes) {
            if (chromosome.getValue() > 0)
                return;
        }

        throw new DeadPopulationException();
    }

    /**
     * Gets the average value of the population.
     * @return The average value.
     */
    private double getAverageValue() {
        double averageValue = 0;
        for (Chromosome chromosome : chromosomes) {
            averageValue += chromosome.getValue();
        }
        averageValue = averageValue/chromosomes.size();
        return averageValue;
    }

    /**
     * The size of the tournament. When the tournament algorithm is selected for selection, this determines how many
     * individuals will be placed in the tournament. Defaults to populationSize / 4.
     * @param tournamentSize The tournament size.
     */
    public void setTournamentSize(int tournamentSize){
        this.tournamentSize = tournamentSize;
    }

    /**
     * The number of individuals to keep for the next generation, should elitism be in use. Defaults to 1 in that case.
     * @param elitismSize The number of individuals to keep.
     */
    public void setElitismSize(int elitismSize){
        this.elitismSize = elitismSize;
    }

    /**
     * The probability of the father passing its gene down to the offspring instead of the mother in the uniform
     * crossover algorithm. Defaults to 0.5.
     * @param uniformRate The uniform rate.
     */
    public void setUniformRate(double uniformRate){
        this.uniformRate = uniformRate;
    }

    /**
     * The mutation rate after the creation of a new generation. Defaults to 0.01.
     * @param mutationRate The mutation rate.
     */
    public void setMutationRate(double mutationRate){
        this.mutationRate = mutationRate;
    }

    /**
     * Deals with the pairing and crossover phases. Chooses two parents and generates an offspring using the specified
     * algorithms.
     * @return The offspring.
     */
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

        int length = father.length();
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

    /**
     * Takes care of the creation of the next generation.
     */
    private void breeding(){
        List<Chromosome> newChromosomes = new ArrayList<>();

        if(elitismSize != 0) {
            Collections.sort(chromosomes);
            for(int i = 0; i < elitismSize; ++i){
                newChromosomes.add(chromosomes.get(i));
            }
        }

        if(selectionType == ROULETTESELECTION)
            populateProbabilities();

        for(int i = elitismSize; i < chromosomes.size(); ++i){
            newChromosomes.add(pairingAndCrossover());
        }

        probabilities = null;
        chromosomes = newChromosomes;
    }

    /**
     * Evaluates all the new chromosomes after the mutation phase is over or the initial population is created.
     */
    private void evaluation(){
        for(Chromosome chromosome : chromosomes)
            chromosome.setValue(Math.max(evaluate(chromosome), 0));
    }

    /**
     * When all the new chromosomes are created, goes through each gene of each one and has a P chance of flipping them,
     * P being the mutation rate.
     */
    private void mutation(){
        for (Chromosome chromosome : chromosomes) {
            for (int j = 0; j < chromosome.length(); ++j) {
                if (Math.random() < mutationRate)
                    chromosome.flip(j);
            }
        }
    }

    /**
     * Returns the best chromosome from the current generation.
     * @return The best chromosome.
     */
    public Chromosome getBestChromosome() {
        double bestChromosomeValue = -1;
        Chromosome bestChromosome = null;

        for (Chromosome chromosome : chromosomes) {
            if (chromosome.getValue() > bestChromosomeValue) {
                bestChromosomeValue = chromosome.getValue();
                bestChromosome = chromosome;
            }
        }


        if (bestChromosomeOverall == null || bestChromosome.getValue() > bestChromosomeOverall.getValue()) {
            bestChromosomeOverall = (Chromosome) bestChromosome.clone();
        }

        return bestChromosome;
    }

    /**
     * Handles the choosing of a chromosome in the tournament selection algorithm.
     * @return The chosen chromosome.
     */
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

    /**
     * Handles the choosing of a chromosome in the roulette selection algorithm.
     * @return The chosen chromosome.
     */
    private Chromosome rouletteSelection() {
        while (true) {
            float prob = new Random().nextFloat();
            for (int i = 0; i < probabilities.size(); ++i) {
                if (probabilities.get(i) >= prob)
                    return chromosomes.get(i);
            }
        }
    }

    /**
     * Fills the probabilities list with the appropriate values in the roulette selection method.
     */
    private void populateProbabilities() {
        int totalValue = 0;
        int currentValue = 0;

        probabilities = new ArrayList<>();
        for(Chromosome chromosome : chromosomes){
            totalValue += chromosome.getValue();
        }
        for(Chromosome chromosome : chromosomes){
            probabilities.add((chromosome.getValue() + currentValue)/totalValue);
            currentValue += chromosome.getValue();
        }
    }
}
