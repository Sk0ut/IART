import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by up201304205 on 01-03-2016.
 */
public class CityParser {
    private final static String citiesUrl = "https://pt.wikipedia.org/wiki/Lista_de_municípios_de_Portugal_por_população";
    /* This is my Google Maps Geocoding API Key. YOU ARE PROHIBITED TO USE THIS IN ANY WAY OTHER THAN IN THE CONTEXT
     * OF THIS APPLICATION. */
    private final static String googleApiKey = "AIzaSyDz40hA-iPh957WG8FXY1G6jMOxgKHTAzI";

    private List<City> cities = new ArrayList<>();
    private String html;

    public CityParser() {
        extractHtml();
        parseCities();
        parseCoords();
    }

    public List<City> getCities(){
        return cities;
    }

    private void parseCoords() {
        String json;
        try {
            for (City city : cities) {
                Thread.sleep(50); // We need this in order not to overload the Google Maps API with queries
                json = extractCityJson(city);
                Coord latlong = getLatLong(json);
                city.setLatitude(latlong.latitude);
                city.setLongitude(latlong.longitude);
            }
        } catch(IOException | InterruptedException e){
            e.printStackTrace();
        }
    }

    private Coord getLatLong(String json) {
        JSONObject obj = new JSONObject(json);
        JSONObject results = (JSONObject) obj.getJSONArray("results").get(0);
        JSONObject geometry = results.getJSONObject("geometry");
        JSONObject location = geometry.getJSONObject("location");
        return new Coord(location.getDouble("lat"), location.getDouble("lng"));
    }

    private String extractCityJson(City city) throws IOException {
        String cityName = URLEncoder.encode(city.getName(), "utf-8");
        URL url = new URL("https://maps.googleapis.com/maps/api/geocode/json?address=" + cityName +
                ",Portugal&key=" + googleApiKey);
        InputStream is = (InputStream)url.getContent();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line;
        StringBuilder sb = new StringBuilder();
        while((line = br.readLine()) != null)
            sb.append(line);
        return sb.toString();
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
            URL url = new URL(citiesUrl);
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

    public class Coord {
        private double latitude;
        private double longitude;

        public Coord(double latitude, double longitude) {
            this.latitude = latitude;
            this.longitude = longitude;
        }
    }
}
