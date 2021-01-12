package ro.mta.se.lab.utility;

import ro.mta.se.lab.model.WeatherInfos;

public interface WeatherUtilityInterface {
    public WeatherInfos searchForInfos(String... cityInfos);
    public WeatherInfos searchForInfos(int id);
    public WeatherInfos searchForInfos(double lat, double longit);
}
