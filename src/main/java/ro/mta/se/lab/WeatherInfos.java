package ro.mta.se.lab;

public class WeatherInfos {
    private double precipitation;
    private double humidity;
    private double wind;
    private double tempeture;

    public WeatherInfos(double precipitation, double humidity, double wind, double tempeture) {
        this.precipitation = precipitation;
        this.humidity = humidity;
        this.wind = wind;
        this.tempeture = tempeture;
    }

    public double getPrecipitation() {
        return precipitation;
    }

    public double getHumidity() {
        return humidity;
    }

    public double getWind() {
        return wind;
    }

    public double getTempeture() {
        return tempeture;
    }
}
