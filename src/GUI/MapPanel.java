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


    private BufferedImage map;
    private JLabel mapLabel;

    public MapPanel(LayoutManager layout) {
        super(layout);

        try {
            InputStream input = getClass().getResourceAsStream("/resources/MapaPortugal.jpg");
            map = ImageIO.read(input);
        } catch (Exception e) {
            e.printStackTrace();
        }

        renderMap(null);
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

    public void renderMap(List<City> cityList) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        map = createResizedCopy(map, (int) (screenSize.getWidth()/1.5), (int) screenSize.getHeight(), true);

        addCityMarkers(map.getGraphics(), cityList);

        mapLabel = new JLabel(new ImageIcon(map));
        add(mapLabel);
    }

    private void addCityMarkers(Graphics mapGraphics, List<City> cityList) {
        mapGraphics.setColor(new Color(128, 0, 128));


        for (City city : cityList) {

        }
    }


}
