package ro.mta.se.lab.utility;

import ro.mta.se.lab.model.WeatherInfos;
import org.json.simple.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * This class is used to communicate with OpenWeather API
 * and to receive all information about weather for the city
 * for which is needed.
 *  @author Ciobanu Cosmin-Marian
 */

public class WeatherUtility implements WeatherUtilityInterface {
    /**
     * Members of class <b>WeatherUtility</b>
     * <i>apiToken:</i> is the token specific for OpenWeather account
     * <i>baseLink:</i> is the link for OpenWeather search
     * <i>readerFile:</i> Reader object which will be used for JSON parse
     */
    static final String apiToken = "f38c2313a44bec9a3dcf0ba183189e70";
    static final String baseLink = "https://api.openweathermap.org/data/2.5/weather?";
    ReaderInterface readerFile;

    /**
     * This function makes a connection with OpenWeather API
     * and reads information provided by this API.
     * After this, it transforms it in JSON file and send it
     * to parser.
     * @param link: link from which we need to download weather information
     * @return WeatherInfos object with weather information
     */
    private WeatherInfos getInfos(String link) {
        readerFile = new Reader();
        try {
            String finalLink = baseLink + link;
            finalLink = finalLink + "&appid=" + apiToken; //add token to link

            URL site = new URL(finalLink);
            HttpURLConnection connection = (HttpURLConnection) site.openConnection(); //establish connection to link
            connection.connect();
            String line;
            //start reading the response from site
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder sb = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
            //transform response in JSON object and calls the parser present in class Reader
            JSONObject object = (JSONObject) JSONValue.parse(sb.toString());
            return readerFile.parseJSON(object);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return new WeatherInfos();
    }

    /**
     * Function used for searching on OpenWeather API using
     * city name alone or with country code
     * @param cityInfos: you can pass to this function city name alone or with country code
     * @return WeatherInfos object with weather information
     */
    public WeatherInfos searchForInfos(String... cityInfos) {
        String searchLink = "q=" + cityInfos[0];
        if (cityInfos.length == 2) { //if we have country code too
            searchLink = searchLink + "," + cityInfos[1];
        }
        return getInfos(searchLink);
    }
    /**
     * Function used for searching on OpenWeather API using
     * id of the city.
     * @param id: id of the city specific to OpenWeather
     * @return WeatherInfos object with weather information
     */
    public WeatherInfos searchForInfos(int id) {
        String searchLink = "id=" + id;
        return getInfos(searchLink);
    }

    /**
     * Function used for searching on OpenWeather API using
     * latitude and longitude of the city.
     * @param lat: latitude of the city
     * @param longit: longitude of the city
     * @return WeatherInfos object with weather information
     */
    public WeatherInfos searchForInfos(double lat, double longit) {
        String searchLink = "lat=" + lat + "&lon=" + longit;
        return getInfos(searchLink);
    }

}
