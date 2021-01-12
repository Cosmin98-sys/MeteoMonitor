package ro.mta.se.lab.utility;

import org.json.simple.*;
import ro.mta.se.lab.model.WeatherInfos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherUtility implements WeatherUtilityInterface {
    static final String apiToken = "f38c2313a44bec9a3dcf0ba183189e70";
    static final String baseLink = "https://api.openweathermap.org/data/2.5/weather?";
    ReaderInterface readerFile;

    private WeatherInfos getInfos(String link) {
        readerFile = new Reader();
        try {
            String finalLink = baseLink + link;
            finalLink = finalLink + "&appid=" + apiToken;

            URL site = new URL(finalLink);
            HttpURLConnection connection = (HttpURLConnection) site.openConnection();
            connection.connect();
            String line;

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder sb = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }

            JSONObject object = (JSONObject) JSONValue.parse(sb.toString());
            return readerFile.parseJSON(object);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return new WeatherInfos();
    }

    public WeatherInfos searchForInfos(String... cityInfos) {
        String searchLink = "q=" + cityInfos[0];
        if (cityInfos.length == 2) {
            searchLink = searchLink + "," + cityInfos[1];
        }
        return getInfos(searchLink);
    }

    public WeatherInfos searchForInfos(int id) {
        String searchLink = "id=" + id;
        return getInfos(searchLink);
    }

    public WeatherInfos searchForInfos(double lat, double longit) {
        String searchLink = "lat=" + lat + "&lon=" + longit;
        return getInfos(searchLink);
    }

}
