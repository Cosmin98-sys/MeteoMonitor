package ro.mta.se.lab.controller;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import ro.mta.se.lab.WeatherUtility;
import ro.mta.se.lab.model.CityInfo;
import ro.mta.se.lab.model.WeatherInfos;

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
    private void countryComboBoxChange(){
        cityComboBox.getItems().clear();
        String country = countryComboBox.getSelectionModel().getSelectedItem();
        for (CityInfo it : cities){
            if(it.getCountryCode().equals(country)){
                cityComboBox.getItems().add(it.getCityName());
            }
        }
    }

    @FXML
    private void cityComboBoxChange(){
        String currentCity = cityComboBox.getSelectionModel().getSelectedItem();
        String currentCountry = countryComboBox.getSelectionModel().getSelectedItem();
        WeatherInfos infos = WeatherUtility.searchForInfos(currentCity,currentCountry);
        iconLabel.setGraphic(new ImageView("http://openweathermap.org/img/w/"+infos.getIcon()+".png"));
    }

    @FXML
    private void initialize() {

        for ( CityInfo it:cities ) {
            if(verifyCountry(countryComboBox,it.getCountryCode()))
                countryComboBox.getItems().add(it.getCountryCode());
        }

    }

    boolean verifyCountry(ComboBox<String>comboBox,String country){

        for ( String it: countryComboBox.getItems()) {
            if(it.equals(country)){
                return false;
            }
        }

        return true;
    }

    public MainWindowsController(ArrayList<CityInfo> listOfCities){
        cities = new ArrayList<>();
        cities.addAll(listOfCities);
    }
}
