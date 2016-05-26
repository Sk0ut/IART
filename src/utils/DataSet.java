package utils;

import cityparser.City;
import cityparser.Data;

import java.util.LinkedList;
import java.util.List;
import java.util.Vector;


public class DataSet {
    private Data data;
    private double citizenValue;
    private double citizenCost;
    private double maxDistance;

    Vector<List<Integer>> nearestCities = new Vector<>();

    public DataSet(Data data, double citizenValue, double citizenCost, double maxDistance) {
        this.data = data;
        this.citizenValue = citizenValue;
        this.citizenCost = citizenCost;
        this.maxDistance = maxDistance;

        Vector<City> cities = data.getCities();
        for (int i = 0; i < cities.size(); ++i) {
            List<Integer> nearestFromCity = new LinkedList<>();
            for (int j = 0; j < cities.size(); ++j) {
                if (data.getDistances().get(i).get(j) < maxDistance)
                    nearestFromCity.add(j);
            }
            nearestCities.add(nearestFromCity);
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
}
