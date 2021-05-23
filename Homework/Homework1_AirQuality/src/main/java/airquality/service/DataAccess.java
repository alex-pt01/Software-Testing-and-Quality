package airquality.service;


import airquality.model.AirPollutionForecast;
import airquality.model.CacheMemory;
import airquality.model.Location;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

@Service
public class DataAccess {

    @Autowired
    private ConsumeWaqiAPI consumeWaqiAPI;

    @Autowired
    private CacheMemory cacheMemory;

    private static final Logger LOGGER = LoggerFactory.getLogger(DataAccess.class);

    public Map<Location, List<AirPollutionForecast>> getDataByLocation(String location) throws URISyntaxException, IOException {
        LOGGER.info("Getting data by location from data access class");

        Map<Location, List<AirPollutionForecast>> data = cacheMemory.getDataByLocation(location);
        if (!data.isEmpty()) {
            return data;
            //vai à API
        } else {
            //verifica se existe a location na API
            if (!consumeWaqiAPI.checkIfLocationExists_API(location)) {
                return null;
            } else {
                //consume API and put in cache
                Map<Location, List<AirPollutionForecast>> dataFromAPI = consumeWaqiAPI.getDataByLocation(location);
                cacheMemory.addDataToCache(dataFromAPI);
                return dataFromAPI;
            }
        }
    }




    public Map<Location, List<AirPollutionForecast>> getDataByLocationAndDate(String location, String time) throws URISyntaxException, IOException {
        LOGGER.info("Getting data by location and date from data access class");

        //vai 1º à cache
        Map<Location, List<AirPollutionForecast>> data = cacheMemory.getDataByLocationAndDate(location, time);
        if (!data.isEmpty()) {
            return data;
            //vai à API
        } else {
            //verifica se existe a location na API
            if (!consumeWaqiAPI.checkIfLocationExists_API(location)) {
                return null;
            } else {
                //consume API and put in cache
                Map<Location, List<AirPollutionForecast>> dataFromAPI = consumeWaqiAPI.getDataByLocationAndDate(location, time);
                cacheMemory.addDataToCache(dataFromAPI);
                return dataFromAPI;
            }

        }
    }
}
