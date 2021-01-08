package ro.mta.se.lab.model;

public class CityInfo {
    private int id;
    private String cityName;
    private double latitude;
    private double longitude;
    private String countryCode;

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
