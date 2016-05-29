package Core;

import cityparser.City;

import java.util.List;

/**
 * Created by Luís on 29/05/2016.
 */
public abstract class AlgorithmRunner implements Runnable{
    public abstract List<City> getCurrentSolution();
    public abstract int getCurrentIteration();
}
