package algorithms;

import utils.OptimizationAlgorithm;
import utils.State;
import utils.StateTransitionFunction;

public abstract class SimmulatedAnnealing extends OptimizationAlgorithm {
    private final StateTransitionFunction transitionFunction;
    private double initialTemperature;
    private double coolingRate;
    private State bestState;
    private double currentTemperature;

    public SimmulatedAnnealing(StateTransitionFunction transitionFunction, double initialTemperature, double coolingRate){
        this.transitionFunction = transitionFunction;
        this.initialTemperature = initialTemperature;
        this.coolingRate = coolingRate;
    }

    private State run(State initialState){
        State currentState = initialState;
        currentTemperature = initialTemperature;
        resetIterations();
        updateBestState(currentState);

        while(!stopCondition()){
            State newState = transitionFunction.next(currentState);
            if (acceptNewState(currentState, newState, getCurrentTemperature()))
                currentState = newState;
            currentTemperature *= (1-coolingRate);
            incrementIterations();
            updateBestState(currentState);
        }

        return bestState;
    }

    private void updateBestState(State newState) {
        if (newState.getValue() > bestState.getValue()){
            bestState = (State) newState.clone();
        }
    }

    private boolean acceptNewState(State currentState, State newState, double temperature) {
        double delta = newState.getValue() - currentState.getValue();
        return delta > 0 || Math.exp(delta / temperature) > Math.random();
    }

    public boolean stopCondition() {
        return getCurrentTemperature() < 0.01;
    }

    public double getCurrentTemperature() {
        return currentTemperature;
    }
}
