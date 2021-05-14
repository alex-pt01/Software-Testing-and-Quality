package airquality.controller;

import airquality.model.CacheMemory;
import airquality.model.Location;
import airquality.service.DataAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.TreeMap;

@RestController
public class APIController {

    @Autowired
    CacheMemory cacheMemory;


    @Autowired
    DataAccess dataAccess;


    @GetMapping("/cacheStatistics_api")
    public ResponseEntity<Map<String, Integer>> cacheStats() {
        Map<String, Integer> graphData = new TreeMap<>();
        graphData.put("Requests", cacheMemory.getRequests());
        graphData.put("Hits",  cacheMemory.getHits());
        graphData.put("Misses", cacheMemory.getMisses());
        return new ResponseEntity<>(graphData, HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping("/location_api")
    public ResponseEntity<Location> getValidLocation(@RequestParam(value = "location") String location){
        Location locationData = cacheMemory.getLocation(location);
        if (locationData == null) {return new ResponseEntity<>(HttpStatus.NOT_FOUND);}
        return  new ResponseEntity<>(locationData,HttpStatus.OK);
    }

    @ResponseBody()
    @GetMapping(value = "/cacheJsonStats")
    public String cacheJsonStats(){
        return cacheMemory.getLocationAsJson();
    }
}
