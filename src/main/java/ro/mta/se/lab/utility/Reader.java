package ro.mta.se.lab.utility;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import ro.mta.se.lab.model.CityInfo;
import ro.mta.se.lab.model.WeatherInfos;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class Reader {

    static public ArrayList<CityInfo> readCities(String filename){
        ArrayList<CityInfo> cities = new ArrayList<CityInfo>();
        try {
            File cityFile = new File(filename);
            Scanner reader = new Scanner(cityFile);
            while (reader.hasNextLine()) {
                Scanner readerWord = new Scanner(reader.nextLine());
                String id = readerWord.next();
                String name = readerWord.next();
                String lat = readerWord.next();
                String longit = readerWord.next();
                String state = readerWord.next();
                CityInfo city = new CityInfo(Integer.parseInt(id), name, Double.parseDouble(lat), Double.parseDouble(longit), state);
                cities.add(city);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not existent");
        }
        return cities;
    }

    static public WeatherInfos parseJSON(JSONObject object){
        WeatherInfos localInfos = new WeatherInfos();

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

        return localInfos;
    }

    private static float convertKelvinToCelsius(double kelvin) {
        return (float) (kelvin - 273.15);
    }

}

