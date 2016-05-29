package GUI;

import Core.AlgorithmRunner;
import cityparser.City;
import com.oracle.deploy.update.Updater;
import com.sun.org.apache.xml.internal.security.algorithms.JCEMapper;

import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.JFrame;

import java.awt.*;
import java.util.concurrent.*;

public class AlgorithmUI{

    private JFrame algorithmFrame;
    private JPanel algorithmPanel;

    private MapPanel mapPanel;

    private JPanel infoPanel;
    private JLabel bestFitnessLabel;
    private JLabel totalCostLabel;
    private JList infoList;
    private JScrollPane solutionPane;

    private AlgorithmRunner runner;
    private ScheduledFuture<?> updateTask;

    public AlgorithmUI(AlgorithmRunner runner){
        algorithmFrame = new JFrame("IART");
        this.runner = runner;
    }

    public void render() {
        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        algorithmFrame.setSize((int)(screenSize.getWidth()),(int) (screenSize.getHeight()));
        algorithmFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        createFrameLayout();


        algorithmFrame.setResizable(false);

        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        updateTask =  executor.scheduleAtFixedRate((Runnable) () -> {
            boolean stop = AlgorithmUI.this.runner.isFinished();
            List<City> cityList = AlgorithmUI.this.runner.getCurrentSolution();
            double fitnessValue = AlgorithmUI.this.runner.getCurrentFitnessValue();
            AlgorithmUI.this.updateSolution(cityList, fitnessValue);

            if (stop) {
                AlgorithmUI.this.updateTask.cancel(true);
            }
        }, 1000, 1000, TimeUnit.MILLISECONDS);

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

        bestFitnessLabel = new JLabel("Best Chromosome Fitness: ");
        constraints.weighty = 0.05;
        constraints.gridwidth = 1;
        constraints.gridx = 0;
        constraints.gridy = 0;
        infoPanel.add(bestFitnessLabel, constraints);

        totalCostLabel = new JLabel("Total Cost: ");
        constraints.gridy = 1;
        infoPanel.add(totalCostLabel, constraints);

        JLabel tribunalLocationLabel = new JLabel("Tribunal Locations");
        constraints.gridy = 2;
        infoPanel.add(tribunalLocationLabel, constraints);

        //TODO create proper list
        infoList = new JList();
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

    public void updateSolution(List<City> cityList, double fitnessValue) {
        bestFitnessLabel.setText("Best Chromosome Fitness: " + fitnessValue);

        ArrayList<String> arrayList = new ArrayList<>();
        int totalCost = 0;

        for(City city : cityList){
            arrayList.add("" + city.getName() + " | " + (double) city.getCost()/1000000 + " M€");
            totalCost += city.getCost();
        }

        infoList.setListData(arrayList.toArray());
        totalCostLabel.setText("Total Cost: " + totalCost/1000000 + " M€");

        mapPanel.addCityMarkers(cityList);

    }
}
