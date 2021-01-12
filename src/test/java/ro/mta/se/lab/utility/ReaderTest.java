package ro.mta.se.lab.utility;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.junit.Assert.*;

public class ReaderTest {

    ReaderInterface readerFile;

    @Before
    public void setUp() throws Exception {

        readerFile = new Reader();

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test()
    public void readCities() {
        for ( int i = 1; i <= 2; i++ ) {
            String testValueFile = "TestFiles/city_test" + i + ".txt";
            assertEquals(String.valueOf(readerFile.readCities(testValueFile).size()), "3");
        }
    }

    @Test(expected = NullPointerException.class)
    public void parseJSONException() {
        try {
            File cityFile = new File("TestFiles/test6.json");
            Scanner reader = new Scanner(cityFile);
            String contain = reader.nextLine();
            JSONObject objectForTest = (JSONObject) JSONValue.parse(contain);
            readerFile.parseJSON(objectForTest);
        }catch (FileNotFoundException e) {
            System.out.println("File not existent");
        }
    }

    @Test
    public void parseJSON() {
        for ( int i = 1; i <= 5; i++ ) {
            try {
                File testValueFile = new File("TestFiles/test" + i + "_answear.txt");

                Scanner readerAnswear = new Scanner(testValueFile);
                float humidityTest = readerAnswear.nextFloat();
                float windSpeedTest = readerAnswear.nextFloat();

                File cityFile = new File("TestFiles/test" + i + ".json");
                Scanner reader = new Scanner(cityFile);
                String contain = reader.nextLine();
                JSONObject objectForTest = (JSONObject) JSONValue.parse(contain);

                assertEquals(String.valueOf(readerFile.parseJSON(objectForTest).getHumidity()), String.valueOf(humidityTest));
                assertEquals(String.valueOf(readerFile.parseJSON(objectForTest).getWind()), String.valueOf(windSpeedTest));

            } catch (FileNotFoundException e) {
                System.out.println("File not existent");
            }
        }
    }
}