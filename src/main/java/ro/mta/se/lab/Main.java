package ro.mta.se.lab;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ro.mta.se.lab.controller.MainWindowsController;
import ro.mta.se.lab.model.CityInfo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class Main extends Application {
    public static void main(String[] args)
    {
        launch(args);
    }

    public void start(Stage primaryStage) {

        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/view/MainWindow.fxml"));
            loader.setControllerFactory(c -> {
                return new MainWindowsController(readCities());
            });
            primaryStage.setScene(new Scene(loader.load()));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<CityInfo> readCities() {
        ArrayList<CityInfo> cities = new ArrayList<CityInfo>();
        try {
            File cityFile = new File("ListOfCities");
            Scanner reader = new Scanner(cityFile);
            while (reader.hasNextLine()){
                Scanner readerWord = new Scanner(reader.nextLine());
                String id=readerWord.next();
                String name = readerWord.next();
                String lat=readerWord.next();
                String longit = readerWord.next();
                String state = readerWord.next();
                CityInfo city = new CityInfo(Integer.parseInt(id),name,Double.parseDouble(lat),Double.parseDouble(longit),state);
                cities.add(city);
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
        }
        return cities;
    }
}
