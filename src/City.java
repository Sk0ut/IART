/**
 * Created by up201304205 on 01-03-2016.
 */
public class City implements java.io.Serializable{
    private String name;
    private int population;
    private double latitude;
    private double longitude;

    public City(String name, int population) {
        this.name = name;
        this.population = population;
    }

    public String getName() {
        return name;
    }

    public int getPopulation() {
        return population;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return name + " - Population: " + population + " Coordinates: (" + latitude + "," + longitude + ")";
    }
}
