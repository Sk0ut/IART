package utils;

import java.util.Random;

public class State {
    private int [] tribunals;

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
        for (int i = 0; i < tribunals.length; ++i) {
            cloneState.tribunals[i] = tribunals[i];
        }
        return cloneState;
    }
}
