package algorithms.simulated_annealing;


import java.util.Random;

public class OneRandomTribunalStateTransition extends StateTransitionFunction{
    private int maxIndex;
    private int numTribunals;
    private Random randomizer;

    public OneRandomTribunalStateTransition(int numTribunals, int maxIndex) {
        this.maxIndex = maxIndex;
        this.numTribunals = numTribunals;
        randomizer = new Random();
    }

    @Override
    public State next(State state) {
        int selectedTribunal = 0;
        int newCity = 0;
        boolean valid = false;
        while (!valid) {
            selectedTribunal = randomizer.nextInt(state.numTribunals());
            int addition = randomizer.nextInt(maxIndex);
            newCity = (state.getTribunal(selectedTribunal) + addition) % maxIndex;
            valid = true;

            for (int i = 0; i < state.numTribunals(); ++i) {
                if (state.getTribunal(i) == newCity){
                    valid = false;
                    break;
                }
            }
        }

        State newState = (State) state.clone();
        newState.setTribunal(selectedTribunal, newCity);

        return newState;
    }

    @Override
    public State initialState() {
        State state = new State(numTribunals);

        for (int i = 0; i < numTribunals; ++i) {
            state.setTribunal(i, i);
        }

        return state;
    }
}
