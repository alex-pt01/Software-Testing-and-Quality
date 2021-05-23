package airquality;


import airquality.controller.APIController;
import airquality.controller.LocationController;
import airquality.model.AirPollutionForecast;
import airquality.model.CacheMemory;
import airquality.model.Location;
import airquality.service.ConsumeWaqiAPI;
import airquality.service.DataAccess;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.net.URISyntaxException;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.when;
import static org.hamcrest.Matchers.*;



@WebMvcTest(APIController.class)
public class IntegrationTests {

    @TestConfiguration
    static class Config{
        @Bean
        public CacheMemory cacheMemory(){ return new CacheMemory();}

        @Bean
        public DataAccess dataAccess(){ return new DataAccess();}

        @Bean
        public ConsumeWaqiAPI consumeWaqiAPI(){ return new ConsumeWaqiAPI();}
    }


    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private CacheMemory cacheMemory;

    @MockBean
    private DataAccess dataAccess;


    @BeforeEach
    void setUp() {
        RestAssuredMockMvc.mockMvc(mockMvc);
    }

    @Test
    void invalidLocation(){
        Mockito.when(cacheMemory.getLocation(Mockito.anyString())).thenReturn(null);
        when().get("/location_api?location=viseu").then().assertThat().statusCode(404);
    }


    @Test
    void cacheStats() {
        Mockito
                .when(cacheMemory.getRequests())
                .thenReturn(2);
        Mockito
                .when(cacheMemory.getHits())
                .thenReturn(1);
        Mockito
                .when(cacheMemory.getMisses())
                .thenReturn(1);

        RestAssuredMockMvc
                .given()
                .when().get("/cacheStatistics_api")
                .then()
                .log().all()
                .statusCode(200)
                .body("Requests", Matchers.equalTo(2))
                .body("Hits", Matchers.equalTo(1))
                .body("Misses", Matchers.equalTo(1))
        ;
    }


    @Test
    void cacheStatsJson(){
        Mockito.when(cacheMemory.getLocationAsJson()).thenReturn("{}");
        when().get("/cacheJsonStats").then().assertThat().statusCode(200)
                .and().body(equalTo("{}"));
    }

}
