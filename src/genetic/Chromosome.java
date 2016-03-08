package genetic;

import java.util.BitSet;
import java.util.Random;

/**
 * Created by up201304205 on 08-03-2016.
 */
public class Chromosome {
    private BitSet chromossome;

    public Chromosome(int length){
        chromossome = new BitSet(length);
        for(int i = 0; i < length; ++i){
            Random r = new Random();
            if(r.nextBoolean())
                chromossome.flip(i);
        }
    }
}
