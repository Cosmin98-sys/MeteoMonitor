package ro.mta.se.lab;

import org.json.simple.*;
import org.json.simple.parser.*;
import ro.mta.se.lab.model.WeatherInfos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class WeatherUtility {
    static final String apiToken = "f38c2313a44bec9a3dcf0ba183189e70";
    static final String baseLink = "https://api.openweathermap.org/data/2.5/weather?";

    private static WeatherInfos getInfos(String link) {
        WeatherInfos localInfos = new WeatherInfos();
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
            Map main = (Map)object.get("main");
            localInfos.setTemperature(convertKelvinToCelsius((double)main.get("temp")));
            String value = main.get("humidity").toString();
            localInfos.setHumidity(Float.parseFloat(value));

            Map wind = (Map)object.get("wind");
            String windSpeed = wind.get("speed").toString();
            localInfos.setWind(Float.parseFloat(windSpeed));

            JSONArray jsonArray = (JSONArray) object.get("weather");
            Map weather = (Map) jsonArray.iterator().next();
            localInfos.setDescription(((String) weather.get("main")));
            localInfos.setIcon((String) weather.get("icon"));

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

    private static float convertKelvinToCelsius(double kelvin) {
        return (float) (kelvin - 273.15);
    }
}
