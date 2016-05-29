package utils;

public abstract class StateTransitionFunction {
    public abstract State next(State state);

    public abstract State initialState();
}
