package cityparser;

/**
 * Holds the information of a city.
 */
public class City implements java.io.Serializable{
    /**
     * Name of the city.
     */
    private String name;
    /**
     * Population of the city.
     */
    private int population;
    /**
     * The city's latitude.
     */
    private double latitude;
    /**
     * The city's longitude.
     */
    private double longitude;

    /**
     * Cost of building a court in this city.
     */
    private int cost;

    /**
     * City constructor.
     * @param name The name of the city.
     * @param population Population of the city.
     */
    public City(String name, int population) {
        this.name = name;
        this.population = population;
        if(Data.constructionCosts.containsKey(name))
            this.cost = Data.constructionCosts.get(name);
        else
            this.cost = Data.constructionCosts.get("Outros");
    }

    /**
     * Name getter.
     * @return The name.
     */
    public String getName() {
        return name;
    }

    /**
     * Population getter.
     * @return The population.
     */
    public int getPopulation() {
        return population;
    }

    /**
     * Latitude getter.
     * @return The latitude.
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Latitude setter
     * @param latitude Value to set.
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * Longitude getter.
     * @return The longitude.
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Longitude setter.
     * @param longitude Value to set.
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return name + " - Population: " + population + " Court cost - " + cost + " Coordinates: (" + latitude + "," + longitude + ")";
    }
}
