package utils;

import java.util.Random;

public class State {
    private int [] tribunals;
    private double value;

    public State(int numTribunals) {
        tribunals = new int[numTribunals];
    }

    public int getTribunal(int index) {
        return tribunals[index];
    }

    public void setTribunal(int index, int city) {
        tribunals[index] = city;
    }

    public int numTribunals() {
        return tribunals.length;
    }

    @Override
    public Object clone() {
        State cloneState = new State(tribunals.length);
        System.arraycopy(tribunals, 0, cloneState.tribunals, 0, tribunals.length);
        cloneState.setValue(value);
        return cloneState;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
