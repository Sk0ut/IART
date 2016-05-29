package GUI;


public class CustomOptions {
    private String algorithm;
    private int numberTribunals;
    private int maxDistance;
    private int populationSize;

    public CustomOptions(){
        algorithm = "genetic";
        numberTribunals = 50;
        maxDistance = 50000;
        populationSize = 1000;
    }

    public int getNumberTribunals() {
        return numberTribunals;
    }

    public void setNumberTribunals(int numberTribunals) {
        this.numberTribunals = numberTribunals;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public int getMaxDistance() {
        return maxDistance;
    }

    public void setMaxDistance(int maxDistance) {
        this.maxDistance = maxDistance;
    }

    public int getPopulationSize() {
        return populationSize;
    }

    public void setPopulationSize(int populationSize) {
        this.populationSize = populationSize;
    }
}
