package ro.mta.se.lab.model;

/**
 *
 *  This class is used to save the five specific
 *  features of each city (id name latitude longitude country code).
 *  All of those values are visible in the input file.
 *  @author Ciobanu Cosmin-Marian
 */

public class CityInfo {
    private final int id;
    private final String cityName;
    private final double latitude;
    private final double longitude;
    private final String countryCode;

    /**
     * <b>CityInfo</b> class constructor
     * @param id: id of the city which can pe found on WeatherAPI website
     * @param cityName: name of the city
     * @param latitude: latitude of the city
     * @param longitude: longitude of the city
     * @param countryCode: country code specific to country in which is this City
     */
    public CityInfo(int id, String cityName, double latitude, double longitude, String countryCode) {
        this.id = id;
        this.cityName = cityName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.countryCode = countryCode;
    }

    public int getId() {
        return id;
    }

    public String getCityName() {
        return cityName;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getCountryCode() {
        return countryCode;
    }


}
