package utils;

import cityparser.City;

import java.util.Vector;


public class TribunalDistributionChromosome extends Chromosome {
    private Vector<City> cities;
    private int numTribunals;

    /**
     * The chromosome constructor.
     *
     */
    public TribunalDistributionChromosome(Vector<City> cities, int numTribunals) {
        super(neededLength(cities.size(), numTribunals));
        setCities(cities);
        setNumTribunals(numTribunals);
    }

    private static int neededLength(int numCities, int numTribunals) {
        return (int) Math.ceil(Math.log(numCities)/Math.log(2)) * numTribunals;
    }

    public Vector<City> getCities() {
        return cities;
    }

    public void setCities(Vector<City> cities) {
        this.cities = cities;
    }

    public int getNumTribunals() {
        return numTribunals;
    }

    public void setNumTribunals(int numTribunals) {
        this.numTribunals = numTribunals;
    }
}
