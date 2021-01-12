package ro.mta.se.lab.utility;

import org.json.simple.JSONObject;
import ro.mta.se.lab.model.CityInfo;
import ro.mta.se.lab.model.WeatherInfos;

import java.util.ArrayList;

public interface ReaderInterface {
    public ArrayList<CityInfo> readCities(String filename);
    public WeatherInfos parseJSON(JSONObject object);
}
