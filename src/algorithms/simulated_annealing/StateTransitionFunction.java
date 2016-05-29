package algorithms.simulated_annealing;

public abstract class StateTransitionFunction {
    public abstract State next(State state);

    public abstract State initialState();
}
