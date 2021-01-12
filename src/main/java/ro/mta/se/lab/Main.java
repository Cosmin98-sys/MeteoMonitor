package ro.mta.se.lab;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ro.mta.se.lab.controller.MainWindowsController;
import ro.mta.se.lab.utility.Reader;

import java.io.IOException;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {

        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/view/MainWindow.fxml"));
            loader.setControllerFactory(c -> new MainWindowsController(Reader.readCities("ListOfCities")));
            primaryStage.setScene(new Scene(loader.load()));
            primaryStage.setTitle("Weather Monitor");
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
