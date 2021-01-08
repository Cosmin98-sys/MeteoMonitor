package ro.mta.se.lab;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class WeatherUtility {
    static final String apiToken = "f38c2313a44bec9a3dcf0ba183189e70";
    static final String baseLink = "https://api.openweathermap.org/data/2.5/weather?";


    private static WeatherInfos getInfos(String link) {
        WeatherInfos localInfos = null;
        try {
            String finalLink = baseLink + link;
            finalLink = finalLink + "&appid=" + apiToken;

            URL site = new URL(finalLink);
            HttpURLConnection connection = (HttpURLConnection) site.openConnection();
            connection.connect();
            String line = null;

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder sb = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }

            JSONObject object = (JSONObject) JSONValue.parse(sb.toString());
            

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return localInfos;
    }

    static public WeatherInfos searchForInfos(String... cityInfos) {
        String searchLink = "q=" + cityInfos[0];
        if (cityInfos.length == 2) {
            searchLink = searchLink + "," + cityInfos[1];
        }
        return getInfos(searchLink);
    }

    static public WeatherInfos searchForInfos(int id) {
        String searchLink = "id=" + id;
        return getInfos(searchLink);
    }

    static public WeatherInfos searchForInfos(double lat, double longit) {
        String searchLink = "lat=" + lat + "&lon=" + longit;
        return getInfos(searchLink);
    }
}
