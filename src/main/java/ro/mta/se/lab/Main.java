package ro.mta.se.lab;

import ro.mta.se.lab.controller.MainWindowsController;
import ro.mta.se.lab.utility.Reader;
import ro.mta.se.lab.utility.ReaderInterface;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/**
 *
 * This class is the Main class of this project.
 * It starts JavaFX application.
 *  @author Ciobanu Cosmin-Marian
 */

public class Main extends Application {
    static ReaderInterface reader;
    public static void main(String[] args) {
        reader = new Reader();
        launch(args);
    }

    public void start(Stage primaryStage) {

        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/view/MainWindow.fxml"));
            loader.setControllerFactory(c -> new MainWindowsController(reader.readCities("ListOfCities")));
            primaryStage.setScene(new Scene(loader.load()));
            primaryStage.setTitle("Weather Monitor");
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
