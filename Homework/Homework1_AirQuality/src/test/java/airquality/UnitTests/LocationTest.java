package airquality.UnitTests;

import airquality.model.AirPollutionForecast;
import airquality.model.CacheMemory;
import airquality.model.Location;
import airquality.service.ConsumeWaqiAPI;
import airquality.service.DataAccess;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;



import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;

class LocationTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void testSameObjects(){
        Location location = new Location( ); location.setName("Ireland"); location.setLatitude(42.108581); location.setLongitude(-72.590614); //os outros resultados variam
        assertThat(location, is(location));
    }

    @Test
    void testDifferentObjects(){
        Location location = new Location( ); location.setName("Ireland"); location.setLatitude(42.108581); location.setLongitude(-72.590614); //os outros resultados variam
        Location location1 = new Location( ); location.setName("Porto"); location.setLatitude(42.108581); location.setLongitude(-72.590614); //os outros resultados variam
        assertThat(location, not(location1));
    }

    @Test
    void testSameAtributes(){
        Location location = new Location( ); location.setName("Ireland"); location.setLatitude(42.108581); location.setLongitude(-72.590614); //os outros resultados variam
        assertEquals(location.getName(), "Ireland", "Names must be equals");
        assertEquals(location.getLatitude(), 42.108581, "Latitude must be equals");
        assertEquals(location.getLongitude(), -72.590614, "Longitude must be equals");
    }


}