package ro.mta.se.lab.utility;


import ro.mta.se.lab.model.CityInfo;
import ro.mta.se.lab.model.WeatherInfos;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * This class is used as a utility during program execution
 * and has implemented city read functionality in the input
 * file and parsing JSON files received from WeatherAPI.
 *  @author Ciobanu Cosmin-Marian
 */


public class Reader implements ReaderInterface {

    /**
     * This function is used for reading all city information present in
     * the file passed as argument.
     * @param filename: name of the file in which you can find all information about cities
     * @return ArrayList<CityInfo>: returns a list of CityInfo in which is saved everything from the input file
     */
     public ArrayList<CityInfo> readCities(String filename){
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

    /**
     * This function takes a JSON object and extracts all data which is needed
     * in a WeatherInfos object.
     * @param object: is the JSON object which is going to be parsed
     * @return a WeatherInfos object with all information needed about weather
     */
    public WeatherInfos parseJSON(JSONObject object){
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

    /**
     * Takes a temperature in Kelvin and transforms it in Celsius
     * @param kelvin: kelvin value for temperature
     * @return celsius value for temperature
     */
    private float convertKelvinToCelsius(double kelvin) {
        return (float) (kelvin - 273.15);
    }

}

