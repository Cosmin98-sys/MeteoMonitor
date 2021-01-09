package ro.mta.se.lab;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ro.mta.se.lab.controller.MainWindowsController;
import ro.mta.se.lab.model.CityInfo;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class Main extends Application {
    public static void main(String[] args)
    {
        launch(args);
    }

    public void start(Stage primaryStage) {

        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/view/MainWindow.fxml"));
            Parent root = (Parent)loader.load();
            MainWindowsController controller = loader.<MainWindowsController>getController();
            controller.getCities(readCities());
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<CityInfo> readCities(){

        return new ArrayList<CityInfo>();
    }
}
