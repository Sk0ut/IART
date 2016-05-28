package GUI;


public class CustomOptions {
    private String algorithm;
    private int numberTribunals;
    private int budget;

    public CustomOptions(){
        algorithm = "genetic";
        numberTribunals = 50;
        budget = 50;
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
}
