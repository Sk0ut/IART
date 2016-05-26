package utils;

import java.util.BitSet;

public class TribunalDistributionEvaluator {

    private final DataSet dataSet;

    public TribunalDistributionEvaluator(DataSet dataSet) {
        this.dataSet = dataSet;
    }

    public double evaluate(TribunalDistributionChromosome chromosome) {
        BitSet bitSet = new BitSet(dataSet.getData().getCities().size());

        for (int i = 0; i < chromosome.size(); ++i) {
        }


        return 0;
    }
}
