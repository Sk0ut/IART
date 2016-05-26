package GUI;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.Hashtable;

/**
 * Created by Luís on 24/05/2016.
 */
public class OptionsUI {
    private JFrame parentFrame;
    private JDialog optionsDialog;
    private JPanel optionsPanel;

    public OptionsUI(JFrame parentFrame) {
        this.parentFrame = parentFrame;
    }

    private void createOptionsLayout() {
        optionsPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        optionsPanel.setBackground(Color.black);


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

        ButtonGroup algorithmGroup = new ButtonGroup();
        JRadioButton geneticButton = new JRadioButton("Genetic Algorithm");
        algorithmGroup.add(geneticButton);
        insets = new Insets(8, 0, 8, 4);
        constraints.insets = insets;
        constraints.gridx = 1;
        optionsPanel.add(geneticButton, constraints);

        JRadioButton simmulatedAnnealingButton = new JRadioButton("Simulated Annealing");
        insets = new Insets(8, 4, 8, 8);
        constraints.insets = insets;
        constraints.gridx = 2;
        algorithmGroup.add(simmulatedAnnealingButton);
        optionsPanel.add(simmulatedAnnealingButton, constraints);

        algorithmGroup.setSelected(geneticButton.getModel(), true);

        JLabel courtNumberLabel = new JLabel("Court Number");
        insets = new Insets(8, 8, 8, 0);
        constraints.insets = insets;
        constraints.gridx = 0;
        constraints.gridy = 1;
        optionsPanel.add(courtNumberLabel, constraints);

        JSpinner courtNumberSpinner = new JSpinner(new SpinnerNumberModel(50, 5, 200, 5));
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

        JSpinner budgetSpinner = new JSpinner(new SpinnerNumberModel(50, 5, 500, 10));
        insets = new Insets(8, 0, 8, 8);
        constraints.insets = insets;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 1;
        constraints.gridy = 2;
        optionsPanel.add(budgetSpinner, constraints);

        Dimension dialogDimension = optionsDialog.getSize();

        JButton saveButton = new JButton("Save");
        insets = new Insets(8, (int) (dialogDimension.getWidth()/8), 8, 0);
        constraints.insets = insets;
        constraints.fill = GridBagConstraints.VERTICAL;
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        optionsPanel.add(saveButton, constraints);

        JButton cancelButton = new JButton("Cancel");
        insets = new Insets(8, 0, 8, (int) (dialogDimension.getWidth()/8));
        constraints.insets = insets;
        constraints.fill = GridBagConstraints.VERTICAL;
        constraints.gridx = 2;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        optionsPanel.add(cancelButton, constraints);

        optionsDialog.add(optionsPanel);
    }

    public void render() {
        optionsDialog = new JDialog(parentFrame, "Options", true);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        optionsDialog.setSize((int)screenSize.getWidth()/4,(int) screenSize.getHeight()/4);

        createOptionsLayout();

        optionsDialog.setResizable(false);
        optionsDialog.setVisible(true);

    }
}