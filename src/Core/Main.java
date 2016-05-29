package Core;

import GUI.testUI;
import algorithms.DeadPopulationException;
import algorithms.GeneticAlgorithm;
import cityparser.City;
import cityparser.CityParser;
import cityparser.Data;
import utils.Chromosome;
import utils.DataSet;

public class Main {
    /* File to store the city info */
    private final static String fileName = "cities.ser";

    private static testUI graphicalInterface;
    private static Data data;
    private static Main instance;


    private Main() {
        graphicalInterface = new testUI();
        CityParser parser = new CityParser();
        data = parser.getData(fileName);
    }

    public static Main getInstance() {
        if(instance == null)
            instance = new Main();

        return instance;
    }

    public static void main (String args[]) {


        Main mainObject = Main.getInstance();
        mainObject.getGraphicalInterface().render();
    }


    /*
    public static void test() {
        byte[] bytes = new byte[]{0b01001111, 0b10010};
        for (byte aByte : bytes) {
            System.out.println(aByte);
        }
        BitSet set = BitSet.valueOf(bytes);
        System.out.println(set);


        BitSet bitSet = BitSet.valueOf(bytes);
        BitSet tribunals = new BitSet();

        for (int i = 0; i < 4; ++i) {
            BitSet subSet = bitSet.get(i*3, (i+1)*3);

            ByteBuffer buffer = ByteBuffer.wrap(Arrays.copyOf(subSet.toByteArray(), Integer.BYTES)).order(ByteOrder.LITTLE_ENDIAN);
            tribunals.set(buffer.getInt());
        }

        System.out.println(tribunals);
        System.out.println(tribunals.cardinality());
    }

    */

    public testUI getGraphicalInterface() {
        return graphicalInterface;
    }

    public Data getData() {
        return data;
    }

    public String getFileName() {
        return fileName;
    }
}
