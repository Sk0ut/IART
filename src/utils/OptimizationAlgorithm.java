package utils;

public abstract class OptimizationAlgorithm {
    private int iterations = 0;

    protected void resetIterations(){
        iterations = 0;
    }

    protected void incrementIterations(){
        iterations += 1;
    }

    public int getIterations() {
        return iterations;
    }



    /**
     * The stop condition for the algorithm. Abstract method.
     * @return True if the stop condition has already been met, false otherwise.
     */
    protected abstract boolean stopCondition();
}
