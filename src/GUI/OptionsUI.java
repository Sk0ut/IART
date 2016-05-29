package GUI;

import sun.plugin.javascript.JSClassLoader;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class OptionsUI extends JDialog{
    private JPanel optionsPanel;

    private ButtonGroup algorithmGroup;

    private JSpinner courtNumberSpinner;
    private JSpinner populationSpinner;
    private JSpinner maxDistanceSpinner;

    private JSpinner temperatureSpinner;
    private JSpinner coolingSpinner;

    private JCheckBox elitismBox;
    private JComboBox selectionBox;
    private JComboBox breedingBox;

    private JRadioButton geneticButton;
    private JRadioButton simmulatedAnnealingButton;
    private JButton saveButton;
    private JButton cancelButton;

    private CustomOptions currentOptions;

    public OptionsUI(JFrame parentFrame, String dialogTitle, Boolean modal, CustomOptions currentOptions) {
        super(parentFrame, dialogTitle, modal);
        this.currentOptions = currentOptions;
    }

    private void createOptionsLayout() {
        optionsPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        //optionsPanel.setBackground(Color.black);


        Insets insets;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1;
        constraints.weighty = 0.2;
        constraints.gridx = 0;
        constraints.gridy = 0;

        JLabel algorithmLabel = new JLabel("Algorithm: ");
        constraints.insets = new Insets(8, 8, 8, 0);
        optionsPanel.add(algorithmLabel, constraints);

        algorithmGroup = new ButtonGroup();
        geneticButton = new JRadioButton("Genetic Algorithm");
        algorithmGroup.add(geneticButton);
        constraints.insets = new Insets(8, 0, 8, 4);
        constraints.gridx = 1;
        optionsPanel.add(geneticButton, constraints);

        simmulatedAnnealingButton = new JRadioButton("Simulated Annealing");
        constraints.insets = new Insets(8, 4, 8, 8);
        constraints.gridx = 2;
        algorithmGroup.add(simmulatedAnnealingButton);
        optionsPanel.add(simmulatedAnnealingButton, constraints);

        JLabel courtNumberLabel = new JLabel("Court Number");
        constraints.insets = new Insets(8, 8, 8, 0);
        constraints.gridx = 0;
        constraints.gridy = 1;
        optionsPanel.add(courtNumberLabel, constraints);

        courtNumberSpinner = new JSpinner(new SpinnerNumberModel(50, 5, 200, 5));
        constraints.insets = new Insets(8, 0, 8, 8);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 1;
        constraints.gridy = 1;
        optionsPanel.add(courtNumberSpinner, constraints);


        JLabel populationLabel = new JLabel("Chromosome population size");
        constraints.insets = new Insets(8, 8, 8, 0);
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 0;
        constraints.gridy = 2;
        optionsPanel.add(populationLabel, constraints);

        populationSpinner = new JSpinner(new SpinnerNumberModel(1000, 50, 10000, 200));
        constraints.insets = new Insets(8, 0, 8, 8);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 1;
        constraints.gridy = 2;
        optionsPanel.add(populationSpinner, constraints);

        JLabel distanceLabel = new JLabel("Distance (in km)");
        constraints.insets = new Insets(8, 8, 8, 0);
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 0;
        constraints.gridy = 3;
        optionsPanel.add(distanceLabel, constraints);

        maxDistanceSpinner = new JSpinner(new SpinnerNumberModel(50, 5, 500, 10));
        constraints.insets = new Insets(8, 0, 8, 8);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 1;
        constraints.gridy = 3;
        optionsPanel.add(maxDistanceSpinner, constraints);

        JLabel geneticAreaLabel = new JLabel("Genetic configuration");
        constraints.insets = new Insets(8, 8, 8, 0);
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 1;
        constraints.gridwidth = 2;
        constraints.gridy = 4;
        optionsPanel.add(geneticAreaLabel, constraints);

        elitismBox = new JCheckBox("Elitism");
        constraints.insets = new Insets(8, 0, 8, 8);
        constraints.fill = GridBagConstraints.VERTICAL;
        constraints.gridwidth = 1;
        constraints.gridx = 0;
        constraints.gridy = 5;
        optionsPanel.add(elitismBox, constraints);

        selectionBox = new JComboBox<>(new String[]{"Roulette Selection", "Tournament Selection"});
        constraints.gridx = 1;
        constraints.insets = new Insets(8, 0, 8, 16);
        constraints.fill = GridBagConstraints.BOTH;
        optionsPanel.add(selectionBox, constraints);

        breedingBox = new JComboBox<>(new String[]{"Roulette Crossover", "Segment Crossover", "Uniform Crossover"});
        constraints.gridx = 2;
        constraints.insets = new Insets(8, 16, 8, 20);
        optionsPanel.add(breedingBox, constraints);

        JLabel annealingLabel = new JLabel("Sim. Annealing configuration");
        constraints.insets = new Insets(8, 8, 8, 0);
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 1;
        constraints.gridwidth = 2;
        constraints.gridy = 6;
        optionsPanel.add(annealingLabel, constraints);

        JLabel temperatureLabel = new JLabel("Initial Temperate");
        constraints.insets = new Insets(8, 8, 8, 0);
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridwidth = 1;
        constraints.gridx = 0;
        constraints.gridy = 7;
        optionsPanel.add(temperatureLabel, constraints);

        temperatureSpinner = new JSpinner(new SpinnerNumberModel(4000, 1000, 100000, 500));
        constraints.insets = new Insets(8, 0, 8, 20);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 1;
        constraints.gridy = 7;
        optionsPanel.add(temperatureSpinner, constraints);

        JLabel coolingLabel = new JLabel("Cooling Rate (0...1)");
        constraints.insets = new Insets(8, 8, 8, 0);
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 0;
        constraints.gridy = 8;
        optionsPanel.add(coolingLabel, constraints);

        coolingSpinner = new JSpinner(new SpinnerNumberModel(0.001, 0, 1, 0.001));
        constraints.insets = new Insets(8, 0, 8, 20);
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 1;
        constraints.gridy = 8;
        optionsPanel.add(coolingSpinner, constraints);



        Dimension dialogDimension = this.getSize();

        saveButton = new JButton("Save");
        insets = new Insets(8, (int) (dialogDimension.getWidth()/8), 8, 0);
        constraints.insets = insets;
        constraints.fill = GridBagConstraints.VERTICAL;
        constraints.gridx = 0;
        constraints.gridwidth = 1;
        constraints.gridy = 9;
        optionsPanel.add(saveButton, constraints);

        cancelButton = new JButton("Cancel");
        insets = new Insets(8, 0, 8, (int) (dialogDimension.getWidth()/8));
        constraints.insets = insets;
        constraints.fill = GridBagConstraints.VERTICAL;
        constraints.gridx = 2;
        constraints.gridy = 9;
        constraints.gridwidth = 2;
        optionsPanel.add(cancelButton, constraints);

        this.add(optionsPanel);

        this.loadCurrentOptions();
    }

    public void render() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize((int)(screenSize.getWidth()/2.5),(int) (screenSize.getHeight()/2.5));

        createOptionsLayout();
        addListeners();

        setResizable(false);
        setVisible(true);

    }

    public void addListeners() {
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentOptions.setAlgorithm(geneticButton.isSelected() ? "genetic" : "simulated annealing");
                currentOptions.setNumberTribunals((Integer) courtNumberSpinner.getValue());
                currentOptions.setPopulationSize((Integer) populationSpinner.getValue());
                currentOptions.setMaxDistance((Integer) maxDistanceSpinner.getValue() * 1000);
                currentOptions.setElitist(elitismBox.isSelected());
                currentOptions.setSelection((String) selectionBox.getSelectedItem());
                currentOptions.setBreeding((String)breedingBox.getSelectedItem());
                currentOptions.setInitialTemperature((Integer) temperatureSpinner.getValue());
                currentOptions.setCoolingRate((Double) coolingSpinner.getValue());
                dispose();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }


    public void loadCurrentOptions() {
        if(currentOptions.getAlgorithm() == "genetic")
            algorithmGroup.setSelected(geneticButton.getModel(), true);
        else algorithmGroup.setSelected(simmulatedAnnealingButton.getModel(), true);

        courtNumberSpinner.setValue(currentOptions.getNumberTribunals());
        populationSpinner.setValue(currentOptions.getPopulationSize());
        maxDistanceSpinner.setValue(currentOptions.getMaxDistance() / 1000);
        temperatureSpinner.setValue(currentOptions.getInitialTemperature());
        coolingSpinner.setValue(currentOptions.getCoolingRate());

        elitismBox.setSelected(currentOptions.isElitist());
        selectionBox.setSelectedItem(currentOptions.getSelection());
        breedingBox.setSelectedItem(currentOptions.getBreeding());


    }
}
