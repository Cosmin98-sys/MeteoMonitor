package ro.mta.se.lab.controller;
import javafx.beans.property.IntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.Buffer;

public class MainWindowsController {

    @FXML
    private Label Label1;

    @FXML
    private void initialize() throws MalformedURLException {
        URL site=new URL("https://api.openweathermap.org/data/2.5/weather?q=London&appid=f38c2313a44bec9a3dcf0ba183189e70");
        try {
            HttpURLConnection connection = (HttpURLConnection) site.openConnection();
            connection.connect();
            String line = null;

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder sb = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            JSONObject object = (JSONObject) JSONValue.parse(sb.toString());
        }catch (IOException ex){
        }
    }
}
