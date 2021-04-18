package exc2.demo;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class CarControllerTest {

    @Autowired
    MockMvc testServlet;

    @MockBean
    CarManagerService carService;

    @Test
    public void whenCarCreated_returnCreatedCar() throws Exception{
        Car fiat = new Car("fiat","panda");
        fiat.setId(1L);
        when(carService.save(Mockito.any())).thenReturn(fiat);
        testServlet.perform(post("/api/cars")).contentType(MediaType.APPLICATION_JSON)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.marker", is("fiat")));
        
        verify(carService, times(1)).save("fiat");

    }

    @Test
    public void whenCarValidIdReturnCar() throws Exception{
        Car car = new Car();
        car.setId(2L);
        car.setMaker("vw");
        car.setModel("passat");
        when(carService.getCarDetails(car.getId())).thenReturn(Optional.of(car));
        testServlet.perform(MockMvcRequestBuilders.get("/api/cars/2L"));
        



    }


}