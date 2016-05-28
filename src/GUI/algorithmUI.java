package GUI;

import cityparser.CityParser;
import cityparser.Data;
import com.sun.javafx.tk.Toolkit;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by Luís on 27/05/2016.
 */
public class AlgorithmUI{

    private Data cityData;

    private JFrame algorithmFrame;
    private JPanel algorithmPanel;

    private MapPanel mapPanel;

    private JPanel infoPanel;
    private JLabel bestFitnessLabel;
    private JLabel averageFitnessLabel;
    private JScrollPane solutionPane;

    public AlgorithmUI(){
        algorithmFrame = new JFrame("IART");
        CityParser parser = new CityParser();
        cityData = parser.getData("cities.ser");
    }

    public void render() {
        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        algorithmFrame.setSize((int)(screenSize.getWidth()),(int) (screenSize.getHeight()));
        algorithmFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        createFrameLayout();


        algorithmFrame.setResizable(false);
        algorithmFrame.setVisible(true);
    }

    private void createFrameLayout(){
        algorithmPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        mapPanel = new MapPanel(new GridBagLayout());
        infoPanel = new JPanel(new GridBagLayout());


        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 5;
        algorithmPanel.add(mapPanel, constraints);

        bestFitnessLabel = new JLabel("Best Chromosome Fitness: " + getBestChromosomeValue());
        constraints.weighty = 0.05;
        constraints.gridwidth = 1;
        constraints.gridx = 0;
        constraints.gridy = 0;
        infoPanel.add(bestFitnessLabel, constraints);

        averageFitnessLabel = new JLabel("Average Chromosome Fitness: " + getAverageChromosomeValue());
        constraints.gridy = 1;
        infoPanel.add(averageFitnessLabel, constraints);

        JLabel tribunalLocationLabel = new JLabel("Tribunal Locations updating every second");
        constraints.gridy = 2;
        infoPanel.add(tribunalLocationLabel, constraints);

        //TODO create proper list
        JList infoList = new JList(cityData.getCities());
        infoList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        solutionPane = new JScrollPane(infoList);
        solutionPane.setSize(java.awt.Toolkit.getDefaultToolkit().getScreenSize());
        constraints.weighty = 1;
        constraints.gridy = 3;
        constraints.gridheight = 100;
        constraints.insets = new Insets(0, 0, 25, 25);
        infoPanel.add(solutionPane, constraints);

        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.gridy = 0;
        constraints.gridx = 6;
        constraints.insets = new Insets(0,0,0,0);
        algorithmPanel.add(infoPanel, constraints);
        algorithmFrame.add(algorithmPanel);
    }

    //TODO change to actual method
    private int getBestChromosomeValue() {
        return 10;
    }

    //TODO change to actual method
    private int getAverageChromosomeValue() {
        return 4;
    }
}
