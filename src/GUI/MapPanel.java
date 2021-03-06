package GUI;

import cityparser.City;

import javax.imageio.ImageIO;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.util.List;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.InputStream;

public class MapPanel extends JPanel {


    public static final int DIAMETER = 10;
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

    public void addCityMarkers(List<City> cityList, boolean finished) {
        currentMap = deepCopy(unchangedMap);


        Graphics mapGraphics = currentMap.getGraphics();
        if(finished)
            mapGraphics.setColor(Color.green);
        else mapGraphics.setColor(new Color(128, 0, 128));


        double latitude;
        double longitude;

        double povoaX = 672;
        double povoaY = 163;

        double povoaLat = 41.3803685;
        double povoaLong = -8.7609294;

        double xScale = createXScale();
        double yScale = createYScale();

        double pixelX;
        double pixelY;
        for (City city : cityList) {
            latitude = city.getLatitude();
            longitude = city.getLongitude();

            pixelX = povoaX + (longitude - povoaLong) * xScale;
            pixelY = povoaY + (latitude - povoaLat) * yScale;
            mapGraphics.fillOval((int) pixelX - DIAMETER /2, (int) pixelY- DIAMETER /2, DIAMETER, DIAMETER);
        }

        mapLabel.setIcon(new ImageIcon(currentMap));
    }

    private double createXScale(){
        //Povoa
        double long1 = -8.7609294;
        double x1 = 672;

        //Faro
        double long2 = -7.9304397;
        double x2 = 871;


        double pixelDiff = x2 - x1;
        double coordDiff = long2 - long1;

        double xScale = pixelDiff / coordDiff;

        return xScale;
    }

    private double createYScale(){
        double y1 = 163;
        double y2 = 1047;
        double lat1 = 41.3803685;
        double lat2 = 37.0193548;
        double pixelDiff = y2 - y1;
        double coordDiff = lat2 - lat1;
        double yScale = pixelDiff / coordDiff;
        return yScale;
    }

    static BufferedImage deepCopy(BufferedImage bi) {
        ColorModel cm = bi.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = bi.copyData(null);
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }
}
