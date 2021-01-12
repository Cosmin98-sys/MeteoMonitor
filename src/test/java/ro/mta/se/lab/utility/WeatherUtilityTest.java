package ro.mta.se.lab.utility;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import ro.mta.se.lab.model.WeatherInfos;

import static org.mockito.Mockito.mock;

public class WeatherUtilityTest {

    @Mock
    WeatherUtility weatherUtilityTest;

    @Test
    public void searchForInfos() {

        weatherUtilityTest = mock(WeatherUtility.class);

    }
}