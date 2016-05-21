package utils;

/**
 * Created by Afonso on 21/05/2016.
 */
public interface Command {

    /**
     * The evaluate function used to assign a value to a chromosome. Abstract function.
     * @param chromosome Chromosome to assign a value.
     * @return The value of the chromosome.
     */
    double evaluate(Chromosome chromosome);

    /**
     * The stop condition for the algorithm. Abstract method.
     * @return True if the stop condition has already been met, false otherwise.
     */
    boolean stopCondition();
}
