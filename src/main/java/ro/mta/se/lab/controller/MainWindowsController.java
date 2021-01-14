package ro.mta.se.lab.controller;

import ro.mta.se.lab.utility.WeatherUtility;
import ro.mta.se.lab.model.CityInfo;
import ro.mta.se.lab.model.WeatherInfos;
import ro.mta.se.lab.utility.WeatherUtilityInterface;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * This class represents the controller for the MainWindow class,
 * and in it are present all the functionalities and all the methods
 * of processing the inputs received from the user.
 *
 * @author Ciobanu Cosmin-Marian
 */

public class MainWindowsController {
    /**
     * Members of class </b>MainWindowsController</>
     * <i>cities :</i> all cities that have been read and initialized from the input file
     * <i>weatherUtility :</i> instance of class WeatherUtility used for retrieving weather status
     * <p>
     * {word}Label are all graphic elements from our user view interface
     * the last 2 comboBoxes are graphic elements too
     */

    private ArrayList<CityInfo> cities;
    private WeatherUtilityInterface weatherUtility;

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

    /**
     * This method is used for the comboBox in which can be
     * selected the Country and which manipulates the input.
     * After one country is selected it adds every city specific
     * to that country.
     */
    @FXML
    private void countryComboBoxChange() {
        cityComboBox.getItems().clear();
        String country = countryComboBox.getSelectionModel().getSelectedItem(); //get the selected Country Code
        for ( CityInfo it : cities ) {
            if (it.getCountryCode().equals(country)) { // if Country Code is the same
                cityComboBox.getItems().add(it.getCityName()); // show this city in the other comboBox
            }
        }
    }

    /**
     * This method is used for the city-specific comboBox and has the role of
     * displaying all the data received from the utility that deals with
     * receiving weather information, but also of requesting it from it.
     */
    @FXML
    private void cityComboBoxChange() {

        /**
         * <i>currentCity :</i> current city selected in comboBox
         * <i>currentCountry :</i> current country code selected in comboBox
         */

        String currentCity = cityComboBox.getSelectionModel().getSelectedItem();
        String currentCountry = countryComboBox.getSelectionModel().getSelectedItem();
        if (currentCity != null && currentCountry != null) { //if both of them exists
            WeatherInfos infos = weatherUtility.searchForInfos(currentCity, currentCountry); //search for weather information
            ImageView img = new ImageView("http://openweathermap.org/img/w/" + infos.getIcon() + ".png"); //show weather's icon
            img.setFitHeight(150);
            img.setFitWidth(150);

            //set all labels from MainWindow
            iconLabel.setGraphic(img);
            windLabel.setText(infos.getWind() + " km/h");
            humidityLabel.setText(infos.getHumidity() + " %");
            cityNameLabel.setText(currentCity);
            LocalDateTime now = LocalDateTime.now();
            String day = now.getDayOfWeek().toString().toLowerCase();
            day = day.substring(0, 1).toUpperCase() + day.substring(1);
            currentDateLabel.setText(day + ",  " + now.getHour() + ":" + now.getMinute()); // show date in with format DAY HOUR:SECONDS
            descriptionWeatherLabel.setText(infos.getDescription());
            temperatureLabel.setText((int) infos.getTemperature() + "");
        }
    }

    /**
     * Add all country codes to the specific comboBox
     * and verify that everyone is unique in it.
     */
    @FXML
    private void initialize() {

        for ( CityInfo it : cities ) {
            if (verifyCountry(it.getCountryCode()))
                countryComboBox.getItems().add(it.getCountryCode());
        }

    }

    /**
     * @param country: the current country code to be added to the comboBox
     * @return true if it is not displayed already or false if it is
     */
    boolean verifyCountry(String country) {

        for ( String it : countryComboBox.getItems() ) {
            if (it.equals(country)) {
                return false;
            }
        }
        return true;
    }

    /**
     * <b>MainWindowsController</b> class constructor
     *
     * @param listOfCities: all cities that were read from the file
     */
    public MainWindowsController(ArrayList<CityInfo> listOfCities) {
        cities = new ArrayList<>();
        cities.addAll(listOfCities);
        weatherUtility = new WeatherUtility();
    }
}
