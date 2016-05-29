package Core;

import GUI.testUI;
import cityparser.CityParser;
import cityparser.Data;

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
