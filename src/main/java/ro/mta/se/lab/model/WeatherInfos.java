package ro.mta.se.lab.model;

public class WeatherInfos {
    private float humidity;
    private float wind;
    private float temperature;
    private String description;
    private String icon;

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setWind(float wind) {
        this.wind = wind;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
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

    public float getTemperature() {
        return temperature;
    }

    public String getIcon() {
        return icon;
    }

    public String getDescription() {
        return description;
    }
}
