package GUI;

import jdk.nashorn.internal.runtime.options.Options;

import javax.swing.*;
import javax.tools.Tool;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Luís on 24/05/2016.
 */
public class testUI {

    private JFrame mainFrame;

    private JButton generateButton;
    private JButton optionsButton;
    private JButton updateButton;
    private JButton aboutButton;
    private JButton exitButton;

    public testUI(){

        mainFrame = new JFrame("IART");

    }
    public static void main(String[] args) {
        System.out.println("Beginning test UI");

        //Graph graph = new SingleGraph("I can see dead pixels");

        testUI GUI = new testUI();
        GUI.render();
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
        //mainFrame.setLayout(new GridBagLayout());
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

        updateButton = new JButton("Update Info");
        constraints.gridy = 2;
        pane.add(updateButton, constraints);

        aboutButton = new JButton("About");
        constraints.gridy = 3;
        pane.add(aboutButton, constraints);

        exitButton = new JButton("Exit");
        constraints.gridx = 0;
        constraints.gridy = 4;
        pane.add(exitButton, constraints);


        pane.setBackground(Color.black);
        mainFrame.add(pane);
    }

    private void addActionListeners() {
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO generate results action
                System.out.println("Generating solution...");
            }
        });

        optionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO generate options window
                System.out.println("Generating options window...");
                OptionsUI options = new OptionsUI(mainFrame);
                options.render();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO update information action
                System.out.println("Updating information...");
            }
        });

        aboutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO generate about window
                JOptionPane.showMessageDialog(mainFrame, "About us: .......");
                System.out.println("Generating about window...");
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
}
