package airquality.UnitTests;

import airquality.model.AirPollutionForecast;
import airquality.model.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.*;

class AirPollutionForecastTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void testSameObjects(){
        AirPollutionForecast airPollutionForecast = new AirPollutionForecast("CO", 20, 10,30 );
        assertThat(airPollutionForecast, is(airPollutionForecast));
    }

    @Test
    void testDifferentObjects(){
        AirPollutionForecast airPollutionForecast = new AirPollutionForecast("CO", 20, 10,30 );
        AirPollutionForecast airPollutionForecast1 = new AirPollutionForecast("pm10", 10, 10,10 );
        assertThat(airPollutionForecast, not(airPollutionForecast1));
    }

    @Test
    void testSameAtributes(){
        AirPollutionForecast airPollutionForecast = new AirPollutionForecast("CO", 20, 10,30 );
        assertEquals(airPollutionForecast.getPollutionType(), "CO", "Air pollution name must be equals");
        assertEquals(airPollutionForecast.getAvg(), 20, "Avg must be equals");
        assertEquals(airPollutionForecast.getMin(), 10, "Min value must be equals");
        assertEquals(airPollutionForecast.getMax(), 30, "Max value be equals");

    }

}