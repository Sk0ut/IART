package utils;

import java.util.ArrayList;
import java.util.BitSet;
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
    private byte[] genome;
    /**
     *
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
        genomeLength = length;
        genome = new byte[(int) Math.ceil(genomeLength / 8.0)];
    }

    /**
     * Randomize the chromosome's genes. Used in the first generation.
     */
    public void randomizeChromosomeGenes() {
        randomizer.nextBytes(genome);
    }

    /**
     * Gene setter. Used in the crossover phase.
     * @param geneNo Number of the gene to set.
     * @param gene Gene value.
     */
    public void setGene(int geneNo, boolean gene){
        genome[geneNo / 8] = (byte) (gene ? genome[geneNo / 8] | (1 << (geneNo % 8)) : genome[geneNo / 8] & ~(1 << (geneNo % 8)));
    }

    /**
     * Gene getter.
     * @param geneNo Gene to get.
     * @return Gene value.
     */
    public boolean getGene(int geneNo){
        return genome[geneNo / 8] >> (geneNo % 8) != 0;
    }

    /**
     * @return The genome's size.
     */
    public int size(){
        return genomeLength;
    }

    /**
     * Returns the cardinality of the genome. The cardinality is the number of genes set to 1.
     * @return The cardinality.
     */
    public int cardinality(){
       return BitSet.valueOf(genome).cardinality();
    }

    /**
     * Flips a gene's value. Used in the mutation phase.
     * @param geneNo Gene to flip.
     */
    public void flip(int geneNo){
        genome[geneNo / 8] ^= 1 << (geneNo % 8);
    }


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
