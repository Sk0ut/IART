package cityparser;

import java.util.Vector;

/**
 * Created by Afonso on 26/05/2016.
 */
public class Data implements java.io.Serializable {
    /**
     * List with all the cities.
     */
    private Vector<City> cities = new Vector<>();
    private Vector<Vector<Double>> distances = new Vector<>();

    public void setCities(Vector<City> cities){
        this.cities = cities;
    }

    public void setDistances(Vector<Vector<Double>> distances) {
        this.distances = distances;
    }

    public Vector<City> getCities() {
        return cities;
    }

    public void addCity(City city){
        cities.add(city);
    }

    public Vector<Vector<Double>> getDistances() {
        return distances;
    }
}
