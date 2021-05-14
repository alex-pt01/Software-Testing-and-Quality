package airquality;

import airquality.model.AirPollutionForecast;
import airquality.model.CacheMemory;
import airquality.model.Location;
import airquality.service.ConsumeWaqiAPI;
import airquality.service.DataAccess;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class ServiceLayerTest {
    @Mock
    CacheMemory cacheMemory;

    @Mock
    ConsumeWaqiAPI consumeWaqiAPI;

    @InjectMocks
    DataAccess dataAccess;

    @Test
    void whenGoodLocation_All() throws URISyntaxException, IOException {
        String json = "{\"status\":\"ok\",\"data\":{\"aqi\":\"-\",\"idx\":3573,\"attributions\":[{\"url\":\"http://www.mass.gov/eea/agencies/massdep/air/\",\"name\":\"Massachusett Office of Energy and Environmental Affair\",\"logo\":\"US-Massachusetts.png\"},{\"url\":\"http://www.airnow.gov/\",\"name\":\"Air Now - US EPA\"},{\"url\":\"https://waqi.info/\",\"name\":\"World Air Quality Index Project\"}],\"city\":{\"geo\":[42.108581,-72.590614],\"name\":\"Springfield Liberty St\",\"url\":\"https://aqicn.org/city/usa/massachusett/springfield/liberty-st\"},\"dominentpol\":\"\",\"iaqi\":{\"co\":{\"v\":2},\"h\":{\"v\":35},\"no2\":{\"v\":8.4},\"o3\":{\"v\":3},\"p\":{\"v\":1029.5},\"so2\":{\"v\":5.8},\"t\":{\"v\":0.8},\"w\":{\"v\":0.3},\"wg\":{\"v\":1.3}},\"time\":{\"s\":\"2016-08-09 07:00:00\",\"tz\":\"-05:00\",\"v\":1470726000,\"iso\":\"2016-08-09T07:00:00-05:00\"},\"forecast\":{},\"debug\":{\"sync\":\"2020-09-16T01:05:36+09:00\"}}}";

        Map<Location, List<AirPollutionForecast>> resultadoEsperado = new LinkedHashMap<>();
        Location location = new Location( ); location.setName("Ireland"); location.setLatitude(42.108581); location.setLongitude(-72.590614); //os outros resultados variam
        resultadoEsperado.put(location,null);

        lenient().when( consumeWaqiAPI.getJsonBody( contains("Ireland")) ).thenReturn( json );

        Map<Location, List<AirPollutionForecast>> resultado = dataAccess.getDataByLocation("Ireland");

        assertEquals(resultadoEsperado.entrySet().iterator().next().getKey().getName(), resultado.entrySet().iterator().next().getKey().getName());
        assertEquals(resultadoEsperado.entrySet().iterator().next().getKey().getLatitude(), resultado.entrySet().iterator().next().getKey().getLatitude());
        assertEquals(resultadoEsperado.entrySet().iterator().next().getKey().getLongitude(), resultado.entrySet().iterator().next().getKey().getLongitude());

    }

    @Test
    void whenGoodLocationFromCache()  {
        String json = "{\"status\":\"ok\",\"data\":{\"aqi\":\"-\",\"idx\":3573,\"attributions\":[{\"url\":\"http://www.mass.gov/eea/agencies/massdep/air/\",\"name\":\"Massachusett Office of Energy and Environmental Affair\",\"logo\":\"US-Massachusetts.png\"},{\"url\":\"http://www.airnow.gov/\",\"name\":\"Air Now - US EPA\"},{\"url\":\"https://waqi.info/\",\"name\":\"World Air Quality Index Project\"}],\"city\":{\"geo\":[42.108581,-72.590614],\"name\":\"Springfield Liberty St\",\"url\":\"https://aqicn.org/city/usa/massachusett/springfield/liberty-st\"},\"dominentpol\":\"\",\"iaqi\":{\"co\":{\"v\":2},\"h\":{\"v\":35},\"no2\":{\"v\":8.4},\"o3\":{\"v\":3},\"p\":{\"v\":1029.5},\"so2\":{\"v\":5.8},\"t\":{\"v\":0.8},\"w\":{\"v\":0.3},\"wg\":{\"v\":1.3}},\"time\":{\"s\":\"2016-08-09 07:00:00\",\"tz\":\"-05:00\",\"v\":1470726000,\"iso\":\"2016-08-09T07:00:00-05:00\"},\"forecast\":{},\"debug\":{\"sync\":\"2020-09-16T01:05:36+09:00\"}}}";
        lenient().when( consumeWaqiAPI.getJsonBody( contains("Ireland")) ).thenReturn( json );
        assertThat(cacheMemory.getDataByLocation("Ireland"),equalTo(Collections.EMPTY_MAP));
    }

    @Test
    void whenBadRequest() {
        String json = "{\"status\":\"error\",\"data\":\"Unknown station\"}";
        lenient().when( consumeWaqiAPI.getJsonBody( contains("Bad Request")) ).thenReturn( json );
        assertNull(consumeWaqiAPI.getJsonBody( contains("Bad Request")));
    }

    @Test
    void whenGoodLocation_AllDetailed() throws URISyntaxException, IOException {
        String json = "{\"status\":\"ok\",\"data\":{\"aqi\":\"-\",\"idx\":3573,\"attributions\":[{\"url\":\"http://www.mass.gov/eea/agencies/massdep/air/\",\"name\":\"Massachusett Office of Energy and Environmental Affair\",\"logo\":\"US-Massachusetts.png\"},{\"url\":\"http://www.airnow.gov/\",\"name\":\"Air Now - US EPA\"},{\"url\":\"https://waqi.info/\",\"name\":\"World Air Quality Index Project\"}],\"city\":{\"geo\":[42.108581,-72.590614],\"name\":\"Springfield Liberty St\",\"url\":\"https://aqicn.org/city/usa/massachusett/springfield/liberty-st\"},\"dominentpol\":\"\",\"iaqi\":{\"co\":{\"v\":2},\"h\":{\"v\":35},\"no2\":{\"v\":8.4},\"o3\":{\"v\":3},\"p\":{\"v\":1029.5},\"so2\":{\"v\":5.8},\"t\":{\"v\":0.8},\"w\":{\"v\":0.3},\"wg\":{\"v\":1.3}},\"time\":{\"s\":\"2016-08-09 07:00:00\",\"tz\":\"-05:00\",\"v\":1470726000,\"iso\":\"2016-08-09T07:00:00-05:00\"},\"forecast\":{},\"debug\":{\"sync\":\"2020-09-16T01:05:36+09:00\"}}}";

        Map<Location, List<AirPollutionForecast>> resultadoEsperado = new LinkedHashMap<>();
        Location location = new Location( ); location.setName("Ireland"); location.setLatitude(42.108581); location.setLongitude(-72.590614); //os outros resultados variam
        location.setTime("2016-08-09 07:00:00"); location.setTimezone("-05:00");
        resultadoEsperado.put(location,null);


        lenient().when( consumeWaqiAPI.getJsonBody( contains("Ireland")) ).thenReturn( json );

        Map<Location, List<AirPollutionForecast>> resultado = dataAccess.getDataByLocation("Ireland");
        assertThat(resultadoEsperado.keySet(), equalTo(resultado.keySet()));

    }







}
