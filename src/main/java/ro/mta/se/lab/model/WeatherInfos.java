package ro.mta.se.lab.model;

public class WeatherInfos {
    private float humidity;
    private float wind;
    private float temperature;
    private String description;

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }

    public void setWind(float wind) {
        this.wind = wind;
    }

    public void setTemperature(float tempeture) {
        this.temperature = tempeture;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getHumidity() {
        return humidity;
    }

    public float getWind() {
        return wind;
    }

    public float getTempeture() {
        return temperature;
    }

    public String getDescription() {
        return description;
    }
}
