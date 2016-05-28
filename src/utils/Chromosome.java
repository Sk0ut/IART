package utils;

import java.util.BitSet;
import java.util.Random;

/**
 * Holds information regarding each chromosome.
 */
public class Chromosome implements Comparable<Chromosome>{
    /**
     * The value of the chromosome.
     */
    private double value;
    private BitSet genome;
    /**
     * The genome's length.
     */
    private int genomeLength;

    private Random randomizer = new Random();

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
        setLength(length);
        genome = new BitSet(length);
    }

    /**
     * Randomize the chromosome's genes. Used in the first generation.
     */
    public void randomizeChromosomeGenes() {
        for(int i = 0; i < length(); ++i) {
            genome.set(i, randomizer.nextBoolean());
        }
    }

    /**
     * Gene setter. Used in the crossover phase.
     * @param geneNo Number of the gene to set.
     * @param gene Gene value.
     */
    public void setGene(int geneNo, boolean gene){
        if (geneNo >= length())
            throw new IndexOutOfBoundsException();
        getGenome().set(geneNo, gene);
    }

    /**
     * Gene getter.
     * @param geneNo Gene to get.
     * @return Gene value.
     */
    public boolean getGene(int geneNo){
        if (geneNo >= length())
            throw new IndexOutOfBoundsException();
        return getGenome().get(geneNo);
    }

    /**
     * @return The genome's length.
     */
    public int length(){
        return genomeLength;
    }

    /**
     * Returns the cardinality of the genome. The cardinality is the number of genes set to 1.
     * @return The cardinality.
     */
    public int cardinality(){
       return getGenome().cardinality();
    }

    /**
     * Flips a gene's value. Used in the mutation phase.
     * @param geneNo Gene to flip.
     */
    public void flip(int geneNo){
        if (geneNo >= length())
            throw new IndexOutOfBoundsException();
        getGenome().flip(geneNo);
    }


    @Override
    public String toString() {
        String ret = "";

        for(int i = length() - 1; i >= 0; --i){
            if(getGene(i))
                ret += '1';
            else
                ret += '0';
        }

        return ret;
    }

    @Override
    public int compareTo(Chromosome o) {
        return Double.compare(value, o.getValue());
    }

    private void setLength(int length) {
        genomeLength = length;
    }

    /**
     * The chromosome's genome.
     */
    public BitSet getGenome() {
        return genome;
    }
}
