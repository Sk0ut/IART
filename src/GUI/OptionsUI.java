package GUI;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

/**
 * Created by Luís on 24/05/2016.
 */
public class OptionsUI extends JDialog{
    private JPanel optionsPanel;

    private ButtonGroup algorithmGroup;

    private JSpinner courtNumberSpinner;
    private JSpinner budgetSpinner;

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
        insets = new Insets(8, 8, 8, 0);
        constraints.insets = insets;
        optionsPanel.add(algorithmLabel, constraints);

        algorithmGroup = new ButtonGroup();
        geneticButton = new JRadioButton("Genetic Algorithm");
        algorithmGroup.add(geneticButton);
        insets = new Insets(8, 0, 8, 4);
        constraints.insets = insets;
        constraints.gridx = 1;
        optionsPanel.add(geneticButton, constraints);

        simmulatedAnnealingButton = new JRadioButton("Simulated Annealing");
        insets = new Insets(8, 4, 8, 8);
        constraints.insets = insets;
        constraints.gridx = 2;
        algorithmGroup.add(simmulatedAnnealingButton);
        optionsPanel.add(simmulatedAnnealingButton, constraints);

        JLabel courtNumberLabel = new JLabel("Court Number");
        insets = new Insets(8, 8, 8, 0);
        constraints.insets = insets;
        constraints.gridx = 0;
        constraints.gridy = 1;
        optionsPanel.add(courtNumberLabel, constraints);

        courtNumberSpinner = new JSpinner(new SpinnerNumberModel(50, 5, 200, 5));
        insets = new Insets(8, 0, 8, 8);
        constraints.insets = insets;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 1;
        constraints.gridy = 1;
        optionsPanel.add(courtNumberSpinner, constraints);


        JLabel budgetLabel = new JLabel("Budget (in million $)");
        insets = new Insets(8, 8, 8, 0);
        constraints.insets = insets;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 0;
        constraints.gridy = 2;
        optionsPanel.add(budgetLabel, constraints);

        budgetSpinner = new JSpinner(new SpinnerNumberModel(50, 5, 500, 10));
        insets = new Insets(8, 0, 8, 8);
        constraints.insets = insets;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 1;
        constraints.gridy = 2;
        optionsPanel.add(budgetSpinner, constraints);

        Dimension dialogDimension = this.getSize();

        saveButton = new JButton("Save");
        insets = new Insets(8, (int) (dialogDimension.getWidth()/8), 8, 0);
        constraints.insets = insets;
        constraints.fill = GridBagConstraints.VERTICAL;
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        optionsPanel.add(saveButton, constraints);

        cancelButton = new JButton("Cancel");
        insets = new Insets(8, 0, 8, (int) (dialogDimension.getWidth()/8));
        constraints.insets = insets;
        constraints.fill = GridBagConstraints.VERTICAL;
        constraints.gridx = 2;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        optionsPanel.add(cancelButton, constraints);

        this.add(optionsPanel);

        this.loadCurrentOptions();
    }

    public void render() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize((int)screenSize.getWidth()/4,(int) screenSize.getHeight()/4);

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
                currentOptions.setBudget((Integer) budgetSpinner.getValue());
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
        budgetSpinner.setValue(currentOptions.getBudget());

    }
}
