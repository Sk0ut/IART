package Core;

import algorithms.genetic_algorithm.Chromosome;
import algorithms.genetic_algorithm.ChromosomeEvaluator;
import cityparser.City;
import cityparser.Data;
import algorithms.simulated_annealing.State;
import algorithms.simulated_annealing.StateEvaluator;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.*;


public class DataSet implements ChromosomeEvaluator, StateEvaluator {
    private Data data;
    private double citizenValue;
    private double citizenCost;
    private double maxDistance;
    private int numTribunals;

    private Vector<List<Integer>> nearestCities;

    public DataSet(Data data, double citizenValue, double citizenCost, double maxDistance, int numTribunals) {
        this.data = data;
        this.citizenValue = citizenValue;
        this.citizenCost = citizenCost;
        this.maxDistance = maxDistance;
        this.numTribunals = numTribunals;

        Vector<City> cities = data.getCities();
        nearestCities = new Vector<>();
        for (int i = 0; i < cities.size(); ++i) {
            List<Integer> nearestFromCity = new LinkedList<>();
            for (int j = 0; j < cities.size(); ++j) {
                if (j != i && data.getDistances().get(i).get(j) < maxDistance)
                    nearestFromCity.add(j);
            }
            getNearestCities().add(nearestFromCity);
        }
    }

    public double getCitizenValue() {
        return citizenValue;
    }

    public double getCitizenCost() {
        return citizenCost;
    }

    public double getMaxDistance() {
        return maxDistance;
    }

    public Data getData() {
        return data;
    }

    public Vector<List<Integer>> getNearestCities() {
        return nearestCities;
    }

    public int geneLength() {
        return (int) Math.ceil(Math.log(data.getCities().size())/Math.log(2));
    }

    public int numberOfGenes() {
        return numTribunals;
    }

    @Override
    public int chromosomeLength() {
        return geneLength() * numberOfGenes();
    }

    public double evaluate(Chromosome chromosome) {
        if (chromosome.length() != chromosomeLength())
            return 0;

        BitSet citiesWithTribunals = getTribunalsBitSet(chromosome);

        return evaluateTribunalBitSet(citiesWithTribunals);
    }

    private double evaluateTribunalBitSet(BitSet citiesWithTribunals) {
        double result  = 0;

        if (citiesWithTribunals.cardinality() != numTribunals)
            return 0;

        for (int i = 0; i < data.getCities().size(); ++i) {
            City city = data.getCities().get(i);
            if (citiesWithTribunals.get(i)) {
                Integer constructionCost = city.getCost();
                result += city.getPopulation() * citizenValue - constructionCost;
            } else {
                double shortestDistance = Double.POSITIVE_INFINITY;
                for (int j : nearestCities.get(i)) {
                    if (!citiesWithTribunals.get(j))
                        continue;
                    double distance = data.getDistances().get(i).get(j);
                    if (distance < shortestDistance) {
                        shortestDistance = distance;
                    }
                }
                if (shortestDistance != Double.POSITIVE_INFINITY) {
                    result += city.getPopulation() * citizenValue * (maxDistance - shortestDistance) / maxDistance;
                } else {
                    result += -city.getPopulation() * citizenCost;
                }
            }
        }

        return result;
    }


    private BitSet getTribunalsBitSet(Chromosome chromosome) {
        BitSet bitSet = chromosome.getGenome();
        BitSet citiesWithTribunals = new BitSet();

        for (int i = 0; i < numberOfGenes(); ++i) {
            BitSet subSet = bitSet.get(i*geneLength(), (i+1)*geneLength());

            ByteBuffer buffer = ByteBuffer.wrap(Arrays.copyOf(subSet.toByteArray(), Integer.BYTES)).order(ByteOrder.LITTLE_ENDIAN);
            int selectedCity = buffer.getInt() % data.getCities().size();

            citiesWithTribunals.set(selectedCity);
        }
        return citiesWithTribunals;
    }

    private BitSet getTribunalsBitSet(State state) {
        BitSet citiesWithTribunals = new BitSet();

        for (int i = 0; i < numberOfGenes(); ++i) {
            citiesWithTribunals.set(state.getTribunal(i));
        }

        return citiesWithTribunals;
    }

    public List<City> getTribunals(Chromosome chromosome) {
        BitSet tribunalsBitSet = getTribunalsBitSet(chromosome);
        List<City> tribunals = new LinkedList<>();
        for (int i = 0; i < data.getCities().size(); ++i) {
            if (tribunalsBitSet.get(i))
                tribunals.add(data.getCities().get(i));
        }
        return tribunals;
    }

    public List<City> getTribunals(State state) {
        List<City> tribunals = new LinkedList<>();
        for (int i = 0; i < state.numTribunals(); ++i)
            tribunals.add(data.getCities().get(state.getTribunal(i)));
        return tribunals;
    }

    @Override
    public double evaluate(State state) {
        if (state.numTribunals() != numTribunals)
            return 0;

        BitSet citiesWithTribunals = getTribunalsBitSet(state);

        return evaluateTribunalBitSet(citiesWithTribunals);
    }
}
