package ro.mta.se.lab.controller;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import ro.mta.se.lab.WeatherUtility;
import ro.mta.se.lab.model.WeatherInfos;

import java.net.MalformedURLException;

public class MainWindowsController {

    @FXML
    private Label Label1;

    @FXML
    private void initialize() throws MalformedURLException {

        WeatherInfos infos = WeatherUtility.searchForInfos("London");
        System.out.println("da");
    }
}
