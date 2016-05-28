package utils;

import cityparser.City;
import cityparser.Data;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.*;


public class DataSet implements ChromosomeEvaluator {
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
        double result = 0;

        if (chromosome.length() != chromosomeLength())
            return 0;

        BitSet bitSet = BitSet.valueOf(chromosome.toByteArray());
        BitSet citiesWithTribunals = new BitSet(data.getCities().size());

        for (int i = 0; i < numberOfGenes(); ++i) {
            BitSet subSet = bitSet.get(i*geneLength(), (i+1)*geneLength());

            ByteBuffer buffer = ByteBuffer.wrap(Arrays.copyOf(subSet.toByteArray(), Integer.BYTES)).order(ByteOrder.LITTLE_ENDIAN);
            int selectedCity = buffer.getInt();
            if (selectedCity >= numTribunals)
                return 0;
            citiesWithTribunals.set(selectedCity);
        }

        if (citiesWithTribunals.cardinality() != numTribunals)
            return 0;

        for (int i = 0; i < data.getCities().size(); ++i) {
            City city = data.getCities().get(i);
            if (citiesWithTribunals.get(i)) {
                result += city.getPopulation() * citizenValue - Data.constructionCosts.get(city.getName());
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
}
