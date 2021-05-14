package airquality.controller;

import airquality.model.AirPollutionForecast;
import airquality.model.CacheMemory;
import airquality.model.Location;
import airquality.service.DataAccess;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;



@Controller
public class LocationController {

    @Autowired
    private CacheMemory cacheMemory;

    @Autowired
    private DataAccess dataAccess;

    private static final Logger LOGGER = LoggerFactory.getLogger(LocationController.class);

    @GetMapping("/location")
    @ApiOperation(value="Get air quality conditions of specific location")
    public String getDataByLocation(@ModelAttribute("location") Location location, Model model) throws URISyntaxException, IOException {
        LOGGER.info("Search for location...");
        //METER EM LIST
        String searchLocation = location.getName();
        List content = new ArrayList<>();
        LOGGER.info("Getting data form dataAccess...");
        for (Map.Entry<Location, List<AirPollutionForecast>> entry : dataAccess.getDataByLocation(searchLocation).entrySet()) {
            content.add( entry.getKey());
            content.add(entry.getValue());
        }
        if(!content.isEmpty()) {
            LOGGER.info("Obtained date!");
            model.addAttribute("locationData", content.get(0));
            model.addAttribute("airPollutionData", content.get(1));
        }
        return "index";
    }


    @GetMapping("/locationAndDate")
    @ApiOperation(value="Get air quality conditions of specific location and date")
    public String getLocalByNameAndDate(@ModelAttribute("location") Location location, Model model) throws URISyntaxException, IOException {
        LOGGER.info("Search for location an date...");
        String searchLocation = location.getName();
        String searchDate = location.getTime();
        List content = new ArrayList<>();
        for (Map.Entry<Location, List<AirPollutionForecast>> entry : dataAccess.getDataByLocationAndDate(searchLocation,searchDate).entrySet()) {
            if (entry.getKey().getName().equals(searchLocation) && entry.getKey().getTime().equals(searchDate) ) {
                content.add(entry.getKey());
                content.add(entry.getValue());
            }
        }
        if(!content.isEmpty()){
            LOGGER.info("Obtained date!");
            model.addAttribute("message", "Getting data from cache...");
            model.addAttribute("locationData",content.get(0));
            model.addAttribute("airPollutionData",content.get(1));
        }
        return "airPollutionByDate";
    }

    @GetMapping("/cacheHistory")
    @ApiOperation(value="Get cache History")
    public <T> String getCacheHistory(Model model)  {
        LOGGER.info("Getting cache history");
        model.addAttribute("cacheHistoryList", cacheMemory.getCacheHistory());
        return "cacheHistory";
    }

    @GetMapping("/cacheStatistics")
    @ApiOperation(value="Get cache statistics")
    public String cacheInfo(Model model){
        LOGGER.info("Getting cache statistics");
        Map<String, Integer> graphData = new TreeMap<>();
        graphData.put("Requests", cacheMemory.getRequests());
        graphData.put("Hits",  cacheMemory.getHits());
        graphData.put("Misses", cacheMemory.getMisses());
        model.addAttribute("Requests", cacheMemory.getRequests());
        model.addAttribute("Hits", cacheMemory.getHits());
        model.addAttribute("Misses", cacheMemory.getMisses());
        return "cacheStatistics";
    }

}
