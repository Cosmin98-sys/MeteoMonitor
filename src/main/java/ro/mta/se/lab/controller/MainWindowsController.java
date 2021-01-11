package ro.mta.se.lab.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import ro.mta.se.lab.utility.WeatherUtility;
import ro.mta.se.lab.model.CityInfo;
import ro.mta.se.lab.model.WeatherInfos;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class MainWindowsController {

    private ArrayList<CityInfo> cities;

    @FXML
    private Label iconLabel;

    @FXML
    private Label windLabel;

    @FXML
    private Label humidityLabel;

    @FXML
    private Label cityNameLabel;

    @FXML
    private Label currentDateLabel;

    @FXML
    private Label descriptionWeatherLabel;

    @FXML
    private Label temperatureLabel;

    @FXML
    private ComboBox<String> countryComboBox;

    @FXML
    private ComboBox<String> cityComboBox;

    @FXML
    private void countryComboBoxChange() {
        cityComboBox.getItems().clear();
        String country = countryComboBox.getSelectionModel().getSelectedItem();
        for ( CityInfo it : cities ) {
            if (it.getCountryCode().equals(country)) {
                cityComboBox.getItems().add(it.getCityName());
            }
        }
    }

    @FXML
    private void cityComboBoxChange() {
        String currentCity = cityComboBox.getSelectionModel().getSelectedItem();
        String currentCountry = countryComboBox.getSelectionModel().getSelectedItem();
        if (currentCity != null && currentCountry != null) {
            WeatherInfos infos = WeatherUtility.searchForInfos(currentCity, currentCountry);
            ImageView img = new ImageView("http://openweathermap.org/img/w/" + infos.getIcon() + ".png");
            img.setFitHeight(150);
            img.setFitWidth(150);
            iconLabel.setGraphic(img);
            windLabel.setText(infos.getWind() + " km/h");
            humidityLabel.setText(infos.getHumidity() + " %");
            cityNameLabel.setText(currentCity);
            LocalDateTime now = LocalDateTime.now();
            String day = now.getDayOfWeek().toString().toLowerCase();
            day = day.substring(0, 1).toUpperCase() + day.substring(1);
            currentDateLabel.setText(day + ",  " + now.getHour() + ":" + now.getMinute());
            descriptionWeatherLabel.setText(infos.getDescription());
            temperatureLabel.setText((int) infos.getTemperature() + "");
        }
    }

    @FXML
    private void initialize() {

        for ( CityInfo it : cities ) {
            if (verifyCountry(it.getCountryCode()))
                countryComboBox.getItems().add(it.getCountryCode());
        }

    }

    boolean verifyCountry(String country) {

        for ( String it : countryComboBox.getItems() ) {
            if (it.equals(country)) {
                return false;
            }
        }
        return true;
    }

    public MainWindowsController(ArrayList<CityInfo> listOfCities) {
        cities = new ArrayList<>();
        cities.addAll(listOfCities);
    }
}
