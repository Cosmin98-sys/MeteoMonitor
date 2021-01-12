package ro.mta.se.lab.utility;

import org.json.simple.JSONObject;
import ro.mta.se.lab.model.CityInfo;
import ro.mta.se.lab.model.WeatherInfos;
import java.util.ArrayList;

/**
 * This class is the interface of class
 * Reader.
 * @author Ciobanu Cosmin-Marian
 */

public interface ReaderInterface {
    public ArrayList<CityInfo> readCities(String filename);
    public WeatherInfos parseJSON(JSONObject object);
}
