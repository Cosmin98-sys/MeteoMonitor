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

/**
 * This class is used for testing functionalities existent in class
 * Reader. Here, the answer for methods readCities and parseJSON
 * is verified, using files existent in folder TestFiles.
 *
 * @author Ciobanu Cosmin-Marian
 */

public class ReaderTest {

    ReaderInterface readerFile;

    @Before
    public void setUp() throws Exception {

        readerFile = new Reader();

    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * This method verify if the number of cities read from file
     * city_test{number}.txt is equal with 3.
     */
    @Test()
    public void readCities() {
        for ( int i = 1; i <= 2; i++ ) {
            String testValueFile = "TestFiles/city_test" + i + ".txt";
            assertEquals(String.valueOf(readerFile.readCities(testValueFile).size()), "3");
        }
    }

    /**
     * In this method is verified if method parseJSON present in
     * class Reader throws NullPointerException when String received
     * as parameter is null.
     */
    @Test(expected = NullPointerException.class)
    public void parseJSONException() {
        try {
            File cityFile = new File("TestFiles/test6.json");
            Scanner reader = new Scanner(cityFile);
            String contain = reader.nextLine();
            JSONObject objectForTest = (JSONObject) JSONValue.parse(contain);
            readerFile.parseJSON(objectForTest);
        } catch (FileNotFoundException e) {
            System.out.println("File not existent");
        }
    }

    /**
     * In this method is verified if the JSON file with the format name
     * test{number}.json has humidity and windSpeed equal with the values
     * from the test{number}-answer.txt.
     */
    @Test
    public void parseJSON() {
        for ( int i = 1; i <= 5; i++ ) {
            try {
                File testValueFile = new File("TestFiles/test" + i + "_answer.txt");

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