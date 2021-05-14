package airquality.UnitTests;

import airquality.model.AirPollutionForecast;
import airquality.model.CacheMemory;
import airquality.model.Location;
import airquality.service.ConsumeWaqiAPI;
import airquality.service.DataAccess;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.is;

class CacheMemoryTest {
    private CacheMemory cachememory;
    private ConsumeWaqiAPI consumeWaqiAPI;
    private DataAccess dataAccess;

    @BeforeEach
    void setUp() {
        cachememory = new CacheMemory(2);
        dataAccess = new DataAccess();
    }

    @Test
    @DisplayName("Cache has size 2 initially and number of misses, requests, hits size must be equals to 0")
    void testCacheInitialConditions(){
        assertEquals(0, cachememory.getRequests(), "Initially cache must has 0 requests");
        assertEquals(0, cachememory.getHits(), "Initially cache must has 0 hits");
        assertEquals(0, cachememory.getMisses(), "Initially cache must has 0 misses");
        assertEquals(2, cachememory.getMaxCacheSize());
    }

    @Test
    @DisplayName("Initially the number of misses must be 0 cause the 1º request is for API")
    void testMisses() throws URISyntaxException, IOException {
        Map<Location, List<AirPollutionForecast>> data1 = cachememory.getDataByLocation("Barcelona");
        assertThat(cachememory.getMisses(), is(1));
    }

    @Test
    @DisplayName("Misses should be 0")
    void testMisses_() throws URISyntaxException, IOException {
        //insert data to cache
        HashMap<Location, List<AirPollutionForecast>> data = new LinkedHashMap<>();
        Location location = new Location( ); location.setName("Ireland"); location.setLatitude(42.108581); location.setLongitude(-72.590614);
        data.put(location, null);
        cachememory.addDataToCache(data);

        Map<Location, List<AirPollutionForecast>> data1 = cachememory.getDataByLocation("Ireland");
        assertThat(cachememory.getMisses(), is(0));
    }


    @Test
    @DisplayName("Misses should be 0 and hits 1")
    void testMissesAndHits() throws URISyntaxException, IOException {
        //insert data to cache
        HashMap<Location, List<AirPollutionForecast>> data = new LinkedHashMap<>();
        Location location = new Location( ); location.setName("Ireland"); location.setLatitude(42.108581); location.setLongitude(-72.590614);
        data.put(location, null);
        cachememory.addDataToCache(data);

        Map<Location, List<AirPollutionForecast>> data1 = cachememory.getDataByLocation("Ireland");
        assertThat(cachememory.getMisses(), is(0));
        assertThat(cachememory.getHits(), is(1));
    }

    @Test
    @DisplayName("Hits must be 2")
    void testHits() throws URISyntaxException, IOException {
        //insert data to cache
        HashMap<Location, List<AirPollutionForecast>> data = new LinkedHashMap<>();

        Location location = new Location( ); location.setName("Ireland"); location.setLatitude(42.108581); location.setLongitude(-72.590614);
        data.put(location, null);
        Location location1 = new Location( ); location1.setName("Porto"); location1.setLatitude(41.1475); location1.setLongitude(-8.6588888888889);
        data.put(location1, null);

        cachememory.addDataToCache(data);

        Map<Location, List<AirPollutionForecast>> data1 = cachememory.getDataByLocation("Ireland");
        Map<Location, List<AirPollutionForecast>> data2 = cachememory.getDataByLocation("Porto");
        assertThat(cachememory.getMisses(), is(0));
        assertThat(cachememory.getHits(), is(2));
    }

    @Test
    @DisplayName("Requests must be 2")
    void testRequests() throws URISyntaxException, IOException {
        //insert data to cache
        HashMap<Location, List<AirPollutionForecast>> data = new LinkedHashMap<>();

        Location location = new Location( ); location.setName("Ireland"); location.setLatitude(42.108581); location.setLongitude(-72.590614);
        data.put(location, null);

        cachememory.addDataToCache(data);
        Map<Location, List<AirPollutionForecast>> data0 = cachememory.getDataByLocation("Ireland");
        Map<Location, List<AirPollutionForecast>> data1 = cachememory.getDataByLocation("aaaaaa");
        Map<Location, List<AirPollutionForecast>> data2 = cachememory.getDataByLocation("bbbbbb");

        assertThat(cachememory.getRequests(), is(3));
        assertThat(cachememory.getMisses(), is(2));
        assertThat(cachememory.getHits(), is(1));
    }

    @Test
    @DisplayName("Cache size must be 2 and not 3")
    void testCacheSize() throws URISyntaxException, IOException {
        //insert data to cache
        HashMap<Location, List<AirPollutionForecast>> data = new LinkedHashMap<>();

        Location location = new Location( ); location.setName("Ireland"); location.setLatitude(42.108581); location.setLongitude(-72.590614);
        data.put(location, null);
        Location location1 = new Location( ); location1.setName("Porto"); location1.setLatitude(41.1475); location1.setLongitude(-8.6588888888889);
        data.put(location1, null);
        Location location2 = new Location( ); location2.setName("Braga"); location2.setLatitude(41.274166666667); location2.setLongitude(-8.3761111111111);
        data.put(location1, null);

        cachememory.addDataToCache(data);
        assertThat(cachememory.getMaxCacheSize(), is(2));
    }

    @Test
    @DisplayName("Cache size must be 2 and not 3")
    void testCacheRemoveOldestElement() throws URISyntaxException, IOException {
        //insert data to cache
        HashMap<Location, List<AirPollutionForecast>> data = new LinkedHashMap<>();

        Location location = new Location( ); location.setName("Ireland"); location.setLatitude(42.108581); location.setLongitude(-72.590614);
        data.put(location, null);
        Location location1 = new Location( ); location1.setName("Porto"); location1.setLatitude(41.1475); location1.setLongitude(-8.6588888888889);
        data.put(location1, null);
        Location location2 = new Location( ); location2.setName("Braga"); location2.setLatitude(41.274166666667); location2.setLongitude(-8.3761111111111);
        data.put(location1, null);

        cachememory.addDataToCache(data);
        assertThat(cachememory.getMaxCacheSize(), is(2));
        assertFalse(cachememory.localExists("Braga"), "element should not be on cache");
    }

}