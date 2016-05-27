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

    private JPanel mapPanel;
    private JLabel mapLabel;
    private BufferedImage map;

    private JPanel infoPanel;
    private JButton infoButton;

    public AlgorithmUI(){
        try {
            InputStream input = getClass().getResourceAsStream("/resources/MapaPortugal.jpg");
            map = ImageIO.read(input);
        } catch (Exception e) {
            e.printStackTrace();
        }

        algorithmFrame = new JFrame("IART");
        CityParser parser = new CityParser();
        cityData = parser.getData("cities.ser");
    }

    public void render() {
        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        algorithmFrame.setSize((int)(screenSize.getWidth()),(int) (screenSize.getHeight()));
        algorithmFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        map = createResizedCopy(map, (int) (screenSize.getWidth()/1.5), (int) screenSize.getHeight(), true);
        createFrameLayout();


        algorithmFrame.setResizable(false);
        algorithmFrame.setVisible(true);
    }

    private void createFrameLayout(){
        algorithmPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        mapPanel = new JPanel(new GridBagLayout());
        infoPanel = new JPanel(new GridBagLayout());

        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.gridx = 0;
        constraints.gridy = 0;

        mapLabel = new JLabel(new ImageIcon(map));

        mapPanel.add(mapLabel);

        constraints.gridwidth = 4;
        algorithmPanel.add(mapPanel, constraints);

        infoButton = new JButton("Information");
        infoButton.setBackground(Color.black);
        infoButton.setSize(java.awt.Toolkit.getDefaultToolkit().getScreenSize());
        constraints.gridx = 0;
        constraints.gridwidth = 1;
        infoPanel.add(infoButton, constraints);

        constraints.gridx = 4;
        algorithmPanel.add(infoPanel, constraints);
        algorithmFrame.add(algorithmPanel);
    }

    private BufferedImage createResizedCopy(Image originalImage,
                                    int scaledWidth, int scaledHeight,
                                    boolean preserveAlpha)
    {
        int imageType = preserveAlpha ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
        BufferedImage scaledBI = new BufferedImage(scaledWidth, scaledHeight, imageType);
        Graphics2D g = scaledBI.createGraphics();
        if (preserveAlpha) {
            g.setComposite(AlphaComposite.Src);
        }
        g.drawImage(originalImage, 0, 0, scaledWidth, scaledHeight, null);
        g.dispose();
        return scaledBI;
    }
}
