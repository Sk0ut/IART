package GUI;

import Core.AlgorithmRunner;
import Core.GeneticAlgorithmRunner;
import Core.Main;
import Core.SimmulatedAnnealingAlgorithmRunner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class testUI {

    private JFrame mainFrame;

    private JButton generateButton;
    private JButton optionsButton;
    private JButton aboutButton;
    private JButton exitButton;

    public CustomOptions currentOptions;

    public testUI(){

        mainFrame = new JFrame("IART");
        currentOptions = new CustomOptions();

    }

    public void render() {
        Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
        mainFrame.setSize((int) screenDimension.getWidth()/6, (int) screenDimension.getHeight()/ 2);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        createFrameLayout();
        addActionListeners();

        mainFrame.setResizable(false);
        mainFrame.setVisible(true);

    }
    public void createFrameLayout() {
        JPanel pane = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        Insets buttonInset = new Insets(10, 10, 10, 10);
        constraints.insets = buttonInset;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1;
        constraints.weighty = 0.2;
        constraints.gridx = 0;


        generateButton = new JButton("Generate");
        constraints.gridy = 0;
        pane.add(generateButton, constraints);

        optionsButton = new JButton("Options");
        constraints.gridy = 1;
        pane.add(optionsButton, constraints);

        aboutButton = new JButton("About");
        constraints.gridy = 2;
        pane.add(aboutButton, constraints);

        exitButton = new JButton("Exit");
        constraints.gridx = 0;
        constraints.gridy = 3;
        pane.add(exitButton, constraints);


        pane.setBackground(Color.black);
        mainFrame.add(pane);
    }

    private void addActionListeners() {
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AlgorithmRunner runner;
                Main main = Main.getInstance();
                if(currentOptions.getAlgorithm() == "genetic") {
                    int settings = currentOptions.getBreedingInteger() | currentOptions.getSelectionInteger() | currentOptions.getElitismInteger();
                    runner = new GeneticAlgorithmRunner(main.getData(), currentOptions.getNumberTribunals(),
                            currentOptions.getMaxDistance(), currentOptions.getPopulationSize(), settings);
                } else
                    runner = new SimmulatedAnnealingAlgorithmRunner(main.getData(), currentOptions.getNumberTribunals(),
                            currentOptions.getMaxDistance(), currentOptions.getInitialTemperature(), currentOptions.getCoolingRate());

                new Thread(runner).start();

                AlgorithUI algorithmUI = new AlgorithUI(runner);
                algorithmUI.render();
            }
        });

        optionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO generate options window
                OptionsUI options = new OptionsUI(mainFrame, "Options", true, currentOptions);
                options.render();

            }
        });

        aboutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO generate about window
                JOptionPane.showMessageDialog(mainFrame, "Consisting of eager college students, team 'Os fixezinhos da FEUP' started with the assignment of a college project in the field of Artificial Intelligence.\n" +
                        "Wanting to excel at the project, they united the coolest, smartest and most fashionable, manly students available. All of these qualities were of \n" +
                        "utmost importance for the perfection of the task, as the jury was blown away by the fashion sense of the students and the coolness of the presentation\n" +
                        " way before he inspected the code behind the project, which was of such brilliance that he could only gasp at the mark he would have to grant them: 20", "About us", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }


    public CustomOptions getCurrentOptions() {
        return currentOptions;
    }

    public void setCurrentOptions(CustomOptions options) {
        currentOptions = options;
    }
}
