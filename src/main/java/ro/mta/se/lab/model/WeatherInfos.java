package ro.mta.se.lab.model;

/**
 * This class is used to save all the information
 * that we want to save about the weather in a city
 * and that we want to use for display in the graphical interface.
 *
 * @author Ciobanu Cosmin-Marian
 */

public class WeatherInfos {

    private float humidity;
    private float wind;
    private float temperature;
    private String description;
    private String icon;

    public WeatherInfos() {
    }

    /**
     * <b>WeatherInfos</b> class constructor
     *
     * @param humidity:    humidity specific for chosen city
     * @param wind:        wind specific for chosen city
     * @param temperature: temperature specific for chosen city in Kelvin
     * @param description: weather description specific for chosen city
     * @param icon:        icon specific to the weather for chosen city
     */
    public WeatherInfos(float humidity, float wind, float temperature, String description, String icon) {
        this.humidity = humidity;
        this.wind = wind;
        this.temperature = temperature;
        this.description = description;
        this.icon = icon;
    }

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

    public float getTemperature() {
        return temperature;
    }

    public String getIcon() {
        return icon;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Here is the Override of function equals because
     * we need to compare in our program two classes of
     * this type.
     */
    @Override
    public boolean equals(Object obj) {
        WeatherInfos object = (WeatherInfos) obj;
        if (object.getIcon().equals(this.icon) && object.getDescription().equals(this.description)
                && object.getHumidity() == this.humidity && (int) object.getTemperature() == (int) this.temperature
                && object.getWind() == this.wind) {
            return true;
        }
        return false;
    }
}
