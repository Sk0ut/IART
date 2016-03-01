import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by up201304205 on 01-03-2016.
 */
public class CityParser {
    private final static String urlStr = "https://pt.wikipedia.org/wiki/Lista_de_municípios_de_Portugal_por_população";

    private List<City> cities = new ArrayList<>();
    private String html;

    public CityParser() {
        extractHtml();
        parseCities();
        parseCoords();
    }

    private void parseCoords() {
        
    }

    private void parseCities() {
        Document doc = Jsoup.parse(html);
        Elements citiesHtml = doc.getElementsByAttributeValue("style", "text-align: center;");

        for (Element aCitiesHtml : citiesHtml) {
            String name = aCitiesHtml.children().get(1).text();
            String population = aCitiesHtml.children().get(2).text();
            cities.add(new City(name, parsePopulation(population)));
        }
    }

    private int parsePopulation(String population){
        String ret = "";

        for(int i = 0; i < population.length(); ++i)
            if(Character.isDigit(population.charAt(i)))
                ret+=population.charAt(i);

        return Integer.parseInt(ret);
    }

    private void extractHtml() {
        try {
            URL url = new URL(urlStr);
            InputStream is = (InputStream)url.getContent();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuilder sb = new StringBuilder();
            while((line = br.readLine()) != null)
                sb.append(line);
            html = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
