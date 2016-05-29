package GUI;


public class CustomOptions {
    private String algorithm;
    private int numberTribunals;
    private int budget;
    private int maxDistance;

    public CustomOptions(){
        algorithm = "genetic";
        numberTribunals = 50;
        budget = 50;
        maxDistance = 50000;
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

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public int getMaxDistance() {
        return maxDistance;
    }

    public void setMaxDistance(int maxDistance) {
        this.maxDistance = maxDistance;
    }
}
