package com.ua.car_TQS;

import io.restassured.RestAssured;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Matches;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import io.restassured.*;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class CarControllerTest {

    @Autowired
    MockMvc testServlet;

    @MockBean
    CarManagerService carService;

    @BeforeEach
    void setUp(){
        RestAssuredMockMvc.mockMvc(testServlet);
    }



    @Test
    void whenPostCar_thenReturnCreatedCar() throws Exception{

        Car kia1 = new Car("kia", "stinger");
        kia1.setCarId(111L);
        when(carService.save(Mockito.any())).thenReturn(kia1);

        RestAssuredMockMvc
                .given()
                .auth().none()
                .when().post("api/cars")
                .then()
                .log().all()
                .statusCode(200)
                .body(".model", Matchers.containsStringIgnoringCase("stinger"))
                .body(".maker", Matchers.containsStringIgnoringCase("kia"));
    }



    @Test
    public void givenCars_whenGetCars_thenReturnJsonArray() throws Exception {
        Car toyota = new Car("toyota", "corolla");
        Car peugeot = new Car("peugeot", "308");
        Car mini = new Car("mini", "cooper");

        List<Car> allCars = Arrays.asList(toyota,peugeot,mini);

        given(carService.getAllCars()).willReturn(allCars);


        RestAssuredMockMvc
                .given()
                .auth().none()
                .when().get("/api/cars")
                .then()
                .log()
                .all()
                .statusCode(200)
                .body("$.size()",Matchers.equalTo(3));

    }
}