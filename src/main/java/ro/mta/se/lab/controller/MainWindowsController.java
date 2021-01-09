package ro.mta.se.lab.controller;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import ro.mta.se.lab.WeatherUtility;
import ro.mta.se.lab.model.CityInfo;
import ro.mta.se.lab.model.WeatherInfos;

import java.net.MalformedURLException;
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
    private void initialize() throws MalformedURLException {

        WeatherInfos infos = WeatherUtility.searchForInfos("Bucharest");
        iconLabel.setGraphic(new ImageView("http://openweathermap.org/img/w/"+infos.getIcon()+".png"));
        System.out.println("da");
    }

    MainWindowsController(ArrayList<String> listOfCities){
        
    }
}
