package Core;

import cityparser.City;
import cityparser.Data;

import java.util.List;

/**
 * Created by Luís on 29/05/2016.
 */
public abstract class AlgorithmRunner implements Runnable{
    private Data data;
    private int numTribunals;

    public AlgorithmRunner(Data data, int numTribunals){
        this.data = data;
        this.numTribunals = numTribunals;
    }
    public abstract List<City> getCurrentSolution();
    public abstract int getCurrentIteration();

    public Data getData() {
        return data;
    }

    public int getNumTribunals() {
        return numTribunals;
    }

    public void setNumTribunals(int numTribunals) {
        this.numTribunals = numTribunals;
    }

    public abstract boolean isFinished();

    public abstract double getCurrentFitnessValue();
}
