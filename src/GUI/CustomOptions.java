package GUI;


public class CustomOptions {
    private String algorithm;
    private int numberTribunals;
    private int maxDistance;
    private int populationSize;
    private boolean isElitist;
    private String selection;
    private String breeding;
    private double coolingRate;
    private int initialTemperature;

    public CustomOptions(){
        algorithm = "genetic";
        numberTribunals = 20;
        maxDistance = 75000;
        populationSize = 1000;
        isElitist = true;
        selection = "Roulette Selection";
        breeding = "Roulette Crossover";
        coolingRate = 0.001;
        initialTemperature = 4000;
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

    public boolean isElitist() {
        return isElitist;
    }

    public void setElitist(boolean elitist) {
        isElitist = elitist;
    }

    public String getSelection() {
        return selection;
    }

    public void setSelection(String selection) {
        this.selection = selection;
    }

    public String getBreeding() {
        return breeding;
    }

    public void setBreeding(String breeding) {
        this.breeding = breeding;
    }


    public int getSelectionInteger(){
        switch (selection){
            case "Roulette Selection":
                return 0;
            case "Tournament Selection":
                return 1;
        }

        return -1;
    }

    public int getBreedingInteger() {
        switch (breeding) {
            case "Roulette Crossover":
                return 4;
            case "Uniform Crossover":
                return 0;
            case "Segment Crossover":
                return 2;
        }
        return -1;
    }

    public int getElitismInteger() {
        return isElitist ? 1 : 0;
    }

    public double getCoolingRate() {
        return coolingRate;
    }

    public void setCoolingRate(double coolingRate) {
        this.coolingRate = coolingRate;
    }

    public int getInitialTemperature() {
        return initialTemperature;
    }

    public void setInitialTemperature(int initialTemperature) {
        this.initialTemperature = initialTemperature;
    }
}
