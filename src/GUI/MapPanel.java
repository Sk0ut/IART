package GUI;

import cityparser.City;

import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.InputStream;

/**
 * Created by Luís on 28/05/2016.
 */
public class MapPanel extends JPanel {


    private BufferedImage unchangedMap;
    private BufferedImage currentMap;

    private JLabel mapLabel;

    public MapPanel(LayoutManager layout) {
        super(layout);

        try {
            InputStream input = getClass().getResourceAsStream("/resources/MapaPortugal.jpg");
            unchangedMap = ImageIO.read(input);
        } catch (Exception e) {
            e.printStackTrace();
        }

        renderMap();
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

    public void renderMap() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        unchangedMap = createResizedCopy(unchangedMap, (int) (screenSize.getWidth()/1.5), (int) screenSize.getHeight(), true);

        mapLabel = new JLabel(new ImageIcon(unchangedMap));

        add(mapLabel);
    }

    public void addCityMarkers(List<City> cityList) {
        int mapWidth = unchangedMap.getWidth();
        int mapHeight = unchangedMap.getHeight();
        System.out.println("Wid: " + mapWidth);
        System.out.println("height: " + mapHeight);

        currentMap = unchangedMap.getSubimage(0, 0, unchangedMap.getWidth(), unchangedMap.getHeight());


        Graphics mapGraphics = currentMap.getGraphics();
        mapGraphics.setColor(new Color(128, 0, 128));


        mapGraphics.fillOval(100, 100, 50, 50);
        for (City city : cityList) {
            //TODO draw thing thing

            continue;
        }

        mapLabel.setIcon(new ImageIcon(currentMap));
    }


}
