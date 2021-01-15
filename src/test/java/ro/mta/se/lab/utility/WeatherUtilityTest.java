package ro.mta.se.lab.utility;

import ro.mta.se.lab.model.CityInfo;
import ro.mta.se.lab.model.WeatherInfos;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;

import static junit.framework.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * This class is used for testing functionalities existent in class
 * WeatherUtility. Here, the answer for method searchForInfos in all
 * four types of calling is verified. Both connection reach and result
 * return are verified.
 *
 * @author Ciobanu Cosmin-Marian
 */

public class WeatherUtilityTest {

    WeatherUtilityInterface weatherUtility;

    @Mock
    ReaderInterface readerFile;

    @Before
    public void setUp() throws Exception {
        weatherUtility = new WeatherUtility();
    }

    /**
     * In this method is verified if class WeatherUtility can reach
     * OpenWeather API in all four cases using two city examples.
     */
    @Test
    public void verifyConnection() {

        readerFile = mock(Reader.class);
        ArrayList<CityInfo> listOfCities = new ArrayList<>();
        CityInfo cityInfo1 = new CityInfo(894701, "Bulawayo", -20.150, 28.583, "ZW");
        listOfCities.add(cityInfo1);
        CityInfo cityInfo2 = new CityInfo(2193733, "Auckland", -36.867, 174.767, "NZ");
        listOfCities.add(cityInfo2);
        when(readerFile.readCities("ListOfCities.txt")).thenReturn(listOfCities);

        for ( CityInfo it : listOfCities ) {

            WeatherInfos dataWithFirstMethod = weatherUtility.searchForInfos(it.getCityName());
            WeatherInfos dataWithSecondMethod = weatherUtility.searchForInfos(it.getCityName(), it.getCountryCode());
            WeatherInfos dataWithThirdMethod = weatherUtility.searchForInfos(it.getId());
            WeatherInfos dataWithFourthMethod = weatherUtility.searchForInfos(it.getLatitude(), it.getLongitude());
            assertNotNull(dataWithFirstMethod.getDescription());
            assertNotNull(dataWithSecondMethod.getDescription());
            assertNotNull(dataWithThirdMethod.getDescription());
            assertNotNull(dataWithFourthMethod.getDescription());

        }
    }

    /**
     * In this method are used the same two cities as in the other method
     * and for all four results is verified if the JSON file returned is
     * the same.
     */
    @Test
    public void verifyOutput() {

        readerFile = mock(Reader.class);
        ArrayList<CityInfo> listOfCities = new ArrayList<>();
        CityInfo cityInfo1 = new CityInfo(894701, "Bulawayo", -20.150, 28.583, "ZW");
        listOfCities.add(cityInfo1);
        CityInfo cityInfo2 = new CityInfo(2193733, "Auckland", -36.867, 174.767, "NZ");
        listOfCities.add(cityInfo2);
        when(readerFile.readCities("ListOfCities.txt")).thenReturn(listOfCities);

        for ( CityInfo it : listOfCities ) {

            WeatherInfos dataWithFirstMethod = weatherUtility.searchForInfos(it.getCityName());
            WeatherInfos dataWithSecondMethod = weatherUtility.searchForInfos(it.getCityName(), it.getCountryCode());
            WeatherInfos dataWithThirdMethod = weatherUtility.searchForInfos(it.getId());
            WeatherInfos dataWithFourthMethod = weatherUtility.searchForInfos(it.getLatitude(), it.getLongitude());

            assertEquals(dataWithFirstMethod, dataWithSecondMethod);
            assertEquals(dataWithFirstMethod, dataWithThirdMethod);
            assertEquals(dataWithFirstMethod, dataWithFourthMethod);
        }
    }
}