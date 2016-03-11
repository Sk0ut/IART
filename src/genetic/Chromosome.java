package genetic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by up201304205 on 08-03-2016.
 */
public class Chromosome implements Comparable<Chromosome>{
    private double value;
    private List<Boolean> genome;

    public double getValue() {
        return value;
    }

    protected void setValue(double value) {
        this.value = value;
    }

    protected Chromosome(int length){
        genome = new ArrayList<>();
        for(int i = 0; i < length; ++i){
            genome.add(false);
        }
    }

    protected void randomizeChromosomeGenes() {
        for(int i = 0; i < genome.size(); ++i){
            genome.set(i, new Random().nextBoolean());
        }
    }

    protected void setGene(int geneNo, boolean gene){
        genome.set(geneNo, gene);
    }

    public boolean getGene(int geneNo){
        return genome.get(geneNo);
    }

    public int size(){
        return genome.size();
    }

    public int cardinality(){
        int c = 0;
        for (Boolean gene : genome) {
            if (gene)
                c++;
        }
        return c;
    }

    protected void flip(int geneNo){ genome.set(geneNo, !genome.get(geneNo)); }

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
