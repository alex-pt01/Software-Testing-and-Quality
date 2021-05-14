package airquality.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class CacheMemory {
    private static final Logger LOGGER = LoggerFactory.getLogger(CacheMemory.class);

    //remove o elemento + velho se o size da cache for ultrapassado
    private Map<Location, List<AirPollutionForecast>> data = new LinkedHashMap<Location, List<AirPollutionForecast>>(){
        @Override
        protected boolean removeEldestEntry(Map.Entry<Location, List<AirPollutionForecast>> eldest)
        {
            return size() > maxCacheSize;
        }
    };

    private List<CacheHistory> cacheHistories = new ArrayList<>();
    private int requests = 0;
    private int hits = 0;
    private int misses = 0;
    private int maxCacheSize = 4;

    public CacheMemory() { }

    public List<CacheHistory> getCacheHistory(){
        LOGGER.info("Getting cache history from cache");

        return this.cacheHistories;
    }

    //manipulado
    public CacheMemory(int maxCacheSize) {
        this.maxCacheSize = maxCacheSize;
    }

    public void addDataToCache(Map<Location, List<AirPollutionForecast>> locationData){
        LOGGER.info("Added data to cache");
        this.data.putAll(locationData);

    }

    public Boolean localExists(String location) {
        for (Map.Entry<Location, List<AirPollutionForecast>> entry : data.entrySet()) {
            if (entry.getKey().getName().equals(location)) {
                return true;
            }
        }
        return false;
    }

    public Location getLocation(String location) {
        LOGGER.info("Getting location from cache");
        for (Map.Entry<Location, List<AirPollutionForecast>> entry : data.entrySet()) {
            if (entry.getKey().getName().equals(location)) {
                return entry.getKey();
            }
        }
        return null;
    }
    public String getLocationAsJson() {
        LOGGER.info("Getting cache history from cache as json body");

        return "{\"name\":"+data.entrySet().iterator().next().getKey().getName()+
                ", \"longitude\":"+data.entrySet().iterator().next().getKey().getLongitude()
                +", \"latitude\":"+data.entrySet().iterator().next().getKey().getLatitude()
                +", \"time\":"+data.entrySet().iterator().next().getKey().getTime()+
                "}";


    }

    public Map<Location, List<AirPollutionForecast>> getDataByLocation(String location) {
        Map<Location, List<AirPollutionForecast>> dataSearched = new LinkedHashMap<>();
        this.requests++;
        for (Map.Entry<Location, List<AirPollutionForecast>> entry : data.entrySet()) {
            if (entry.getKey().getName().equals(location)) {
                dataSearched.put(entry.getKey(), entry.getValue());
            }
        }
        if (dataSearched.isEmpty()){
            this.misses++;
        }else{
            this.hits++;
        }

        //cache history
        Date date1 = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        CacheHistory cacheHistory = new CacheHistory(this.hits, this.misses, this.requests, formatter.format(date1));
        this.cacheHistories.add(cacheHistory);
        return dataSearched;
    }


    public Map<Location, List<AirPollutionForecast>> getDataByLocationAndDate(String location, String date) {
        Map<Location, List<AirPollutionForecast>> dataSearched1 = new LinkedHashMap<>();
        this.requests++;
        for (Map.Entry<Location, List<AirPollutionForecast>> entry : data.entrySet()) {
            if (entry.getKey().getName().equals(location) && entry.getKey().getTime().equals(date) ) {
                dataSearched1.put(entry.getKey(), entry.getValue());
            }
        }
        if (dataSearched1.isEmpty()){
            this.misses++;
        }else{
            this.hits++;
        }

        Date date1 = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        CacheHistory cacheHistory = new CacheHistory(this.hits, this.misses, this.requests, formatter.format(date1));
        this.cacheHistories.add(cacheHistory);

        return dataSearched1;
    }


    public Map<Location, List<AirPollutionForecast>> getData() {
        return data;
    }

    public int getRequests() {
        return requests;
    }

    public int getHits() {
        return hits;
    }

    public int getMisses() {
        return misses;
    }

    public int getMaxCacheSize() {
        return maxCacheSize;
    }
}
