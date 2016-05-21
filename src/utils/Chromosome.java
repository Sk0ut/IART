package utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Holds information regarding each chromosome.
 */
public class Chromosome implements Comparable<Chromosome>{
    /**
     * The value of the chromosome.
     */
    private double value;
    /**
     * The chromosome's genome.
     */
    private List<Boolean> genome;

    /**
     * Value getter.
     * @return The value.
     */
    public double getValue() {
        return value;
    }

    /**
     * Value setter.
     * @param value The value.
     */
    public void setValue(double value) {
        this.value = value;
    }

    /**
     * The chromosome constructor.
     * @param length Length of the chromosome's genome.
     */
    public Chromosome(int length){
        genome = new ArrayList<>();
        for(int i = 0; i < length; ++i){
            genome.add(false);
        }
    }

    /**
     * Randomize the chromosome's genes. Used in the first generation.
     */
    public void randomizeChromosomeGenes() {
        for(int i = 0; i < genome.size(); ++i){
            genome.set(i, new Random().nextBoolean());
        }
    }

    /**
     * Gene setter. Used in the crossover phase.
     * @param geneNo Number of the gene to set.
     * @param gene Gene value.
     */
    public void setGene(int geneNo, boolean gene){
        genome.set(geneNo, gene);
    }

    /**
     * Gene getter.
     * @param geneNo Gene to get.
     * @return Gene value.
     */
    public boolean getGene(int geneNo){
        return genome.get(geneNo);
    }

    /**
     * @return The genome's size.
     */
    public int size(){
        return genome.size();
    }

    /**
     * Returns the cardinality of the genome. The cardinality is the number of genes set to 1.
     * @return The cardinality.
     */
    public int cardinality(){
        int c = 0;
        for (Boolean gene : genome) {
            if (gene)
                c++;
        }
        return c;
    }

    /**
     * Flips a gene's value. Used in the mutation phase.
     * @param geneNo Gene to flip.
     */
    public void flip(int geneNo){ genome.set(geneNo, !genome.get(geneNo)); }


    @Override
    public String toString() {
        String ret = "";

        for(int i = 0; i < size(); ++i){
            if(getGene(i))
                ret += 1;
            else
                ret += 0;
        }

        return ret;
    }

    @Override
    public int compareTo(Chromosome o) {
        return Double.compare(value, o.getValue());
    }
}
