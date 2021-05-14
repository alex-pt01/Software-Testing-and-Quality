package airquality.service;


import airquality.model.AirPollutionForecast;
import airquality.model.Location;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

@Service
public class ConsumeWaqiAPI {
    private static  String TOKEN = "/?token=ca6eb021ed0c00bf410366f8f2a1f8bbc6ae01c1";
    private static  String API_URL = "https://api.waqi.info/feed/";
    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumeWaqiAPI.class);
    private static String NODATA = "no_data";
    private static String UTF8  = "UTF-8";
    private static String ACPT_CHARSET  = "Accept-Charset";

    //consume apenas location SEM date
    public static Map<Location, List<AirPollutionForecast>> getDataByLocation(String location)  throws  IOException {
        LOGGER.info("Consume API by location searched");
        URLConnection connection = new URL(API_URL + location + TOKEN).openConnection();
        connection.setRequestProperty(ACPT_CHARSET, UTF8);
        InputStream response = connection.getInputStream();
        HashMap<Location, List<AirPollutionForecast>> data = new HashMap<>();
        List<AirPollutionForecast> airPollutionForecastList = new ArrayList<>();
        Location locationObj = new Location();
        locationObj.setName(location);

        try (Scanner scanner = new Scanner(response)) {
            JsonParser parser = new JsonParser();
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            JsonElement el = parser.parse(scanner.useDelimiter("\\A").next());
            String responseBody = gson.toJson(el);
            try {
                JSONObject jsonObject = new JSONObject(responseBody);

                //Geo
                try{
                    JSONArray geo = jsonObject.getJSONObject("data").getJSONObject("city").getJSONArray("geo");
                    locationObj.setLatitude(Double.parseDouble(geo.get(0) + ""));
                    locationObj.setLongitude(Double.parseDouble(geo.get(1) + ""));
                    LOGGER.info("Latitude: " + geo.get(0) + "Longitude: " + geo.get(1));
                }catch (JSONException e){
                    LOGGER.info("Error: " + e);
                    locationObj.setLatitude(0.0);
                    locationObj.setLongitude(0.0);
                }
                //Time
                try{
                    String t = jsonObject.getJSONObject("data").getJSONObject("time").getString("s");
                    LOGGER.info("Time: " + t);
                    locationObj.setTime(t);

                    String tz = jsonObject.getJSONObject("data").getJSONObject("time").getString("tz");
                    LOGGER.info("Timezone: " + tz);
                    locationObj.setTimezone(tz);

                } catch(JSONException e) {
                    locationObj.setTime(NODATA);
                    locationObj.setTimezone(NODATA);
                }

                List<String> namePolluentsOthers = new ArrayList<>();
                namePolluentsOthers.add("h");
                namePolluentsOthers.add("o3");
                namePolluentsOthers.add("p");
                namePolluentsOthers.add("pm25");
                for(String nPO : namePolluentsOthers){
                    try{
                        Double value = jsonObject.getJSONObject("data").getJSONObject("iaqi").getJSONObject(nPO).getDouble("v");
                        AirPollutionForecast airPollutionForecast = new AirPollutionForecast();
                        airPollutionForecast.setPollutionType(nPO);
                        airPollutionForecast.setAvg(value);
                        airPollutionForecastList.add(airPollutionForecast);

                    } catch(JSONException e) {
                        AirPollutionForecast airPollutionForecast = new AirPollutionForecast();
                        airPollutionForecast.setPollutionType(nPO);
                        airPollutionForecast.setAvg(0.0);
                        airPollutionForecastList.add(airPollutionForecast);
                    }
                }
            } catch (JSONException e) {
                LOGGER.info("Json exception");

            }
        }
        data.put(locationObj,airPollutionForecastList);
        return data;
    }

    public static Map<Location, List<AirPollutionForecast>> getDataByLocationAndDate(String location, String time)  throws IOException {
        LOGGER.info("Consume API by location and date searched");
        URLConnection connection = new URL(API_URL + location + TOKEN).openConnection();
        connection.setRequestProperty(ACPT_CHARSET, UTF8);
        InputStream response = connection.getInputStream();
        HashMap<Location, List<AirPollutionForecast>> data = new HashMap<>();
        List<AirPollutionForecast> airPollutionForecastList = new ArrayList<>();
        Location locationObj = new Location();
        locationObj.setName(location);

        try (Scanner scanner = new Scanner(response)) {
            JsonParser parser = new JsonParser();
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            JsonElement el = parser.parse(scanner.useDelimiter("\\A").next());
            String responseBody = gson.toJson(el);

            try {
                JSONObject jsonObject = new JSONObject(responseBody);

                //Geo
                try {
                    JSONArray geo = jsonObject.getJSONObject("data").getJSONObject("city").getJSONArray("geo");
                    locationObj.setLatitude(Double.parseDouble(geo.get(0) + ""));
                    locationObj.setLongitude(Double.parseDouble(geo.get(1) + ""));
                    LOGGER.info("Latitude: " + geo.get(0) + "Longitude: " + geo.get(1));
                } catch (JSONException e) {
                    LOGGER.info("Error: " + e);
                    locationObj.setLatitude(0.0);
                    locationObj.setLongitude(0.0);
                }

                //Time
                try {
                    locationObj.setTime(time);
                    String tz = jsonObject.getJSONObject("data").getJSONObject("time").getString("tz");
                    locationObj.setTimezone(tz);

                } catch (JSONException e) {
                    locationObj.setTime(NODATA);
                    locationObj.setTimezone(NODATA);
                }


                List<String> namePolluents = new ArrayList<String>();
                namePolluents.add("co");
                namePolluents.add("h");
                namePolluents.add("no2");
                namePolluents.add("p");
                namePolluents.add("so2");
                String t = jsonObject.getJSONObject("data").getJSONObject("time").getString("s");

                if (time.equals(t.substring(0, 10))) {
                    for (String nP : namePolluents) {
                        try {
                            Double value = jsonObject.getJSONObject("data").getJSONObject("iaqi").getJSONObject(nP).getDouble("v");
                            AirPollutionForecast airPollutionForecast = new AirPollutionForecast();
                            airPollutionForecast.setPollutionType(nP);
                            airPollutionForecast.setAvg(value);
                            airPollutionForecastList.add(airPollutionForecast);
                        } catch (JSONException e) {
                            AirPollutionForecast airPollutionForecast = new AirPollutionForecast();
                            airPollutionForecast.setPollutionType(nP);
                            airPollutionForecast.setAvg(0.0);
                            airPollutionForecastList.add(airPollutionForecast);
                        }
                    }
                }

                //VÊ OS OUTROS
                List<String> namePolluentsOthers = new ArrayList<String>();
                namePolluentsOthers.add("o3");
                namePolluentsOthers.add("pm10");
                namePolluentsOthers.add("pm25");

                for (String nPO : namePolluentsOthers) {
                    JSONArray getDataArray = jsonObject.getJSONObject("data").getJSONObject("forecast").getJSONObject("daily").getJSONArray(nPO);

                    for (int i = 0; i < getDataArray.length(); i++) {
                        String day = getDataArray.getJSONObject(i).getString("day");
                        if (day.equals(time.substring(0, 10))) {
                            int avg = getDataArray.getJSONObject(i).getInt("avg");
                            int max = getDataArray.getJSONObject(i).getInt("max");
                            int min = getDataArray.getJSONObject(i).getInt("avg");

                            AirPollutionForecast airPollutionForecast = new AirPollutionForecast();
                            airPollutionForecast.setPollutionType(nPO);
                            airPollutionForecast.setAvg(avg);
                            airPollutionForecast.setMax(max);
                            airPollutionForecast.setMin(min);
                            airPollutionForecastList.add(airPollutionForecast);
                        }
                    }
                }
            } catch (JSONException e) {
                LOGGER.info("Json exception");
            }
        }
        if(airPollutionForecastList.size() != 0) {
            LOGGER.info("Getting data!");
            data.put(locationObj, airPollutionForecastList);
        }
        return data;
    }


    public static Boolean checkIfLocationExists_API(String location)  throws URISyntaxException, IOException {
        LOGGER.info("Checking if location exists on API");

        URLConnection connection = new URL(API_URL + location + TOKEN).openConnection();
        connection.setRequestProperty(ACPT_CHARSET, UTF8);
        InputStream response = connection.getInputStream();
        try (Scanner scanner = new Scanner(response)) {
            String responseBody = scanner.useDelimiter("\\A").next();
            JsonParser parser = new JsonParser();
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            JsonElement el = parser.parse(responseBody);
            responseBody = gson.toJson(el);
            try {
                JSONObject jsonObject = new JSONObject(responseBody);
                String status = jsonObject.getString("status");
                if (!status.equals("ok")) {
                    return false;
                }
            } catch (JSONException e) {
                LOGGER.info("Json exception");
            }
        }
        return true;
    }

    public String getJsonBody(String location) {
        URL url;

        try {
            url = new URL(API_URL + location + TOKEN);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
        } catch (Exception e) {
            return null;
        }

        try (Scanner sc = new Scanner(url.openStream())) {
            StringBuilder sb = new StringBuilder();
            while (sc.hasNextLine()) {
                sb.append(sc.nextLine());
            }
            return sb.toString();
        } catch (Exception e) {
            return null;
        }
    }
}
