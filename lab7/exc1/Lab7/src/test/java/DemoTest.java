import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static io.restassured.matcher.RestAssuredMatchers.then;

import static org.hamcrest.Matchers.*;

class DemoTest {


    @Test
    void checkClick() {
        String result = "laboriosam mollitia et enim quasi adipisci quia provident illum";
        given().when().get("https://jsonplaceholder.typicode.com/todos/5").then()
                .statusCode(200)
                .and().body("title", equalTo(result))
                .and().body("id", equalTo(5));
    }
    @Test
    void checkAll() {
        given().when().get("https://jsonplaceholder.typicode.com/todos").then()
                    .statusCode(200).then().assertThat().statusCode(200);
        }




    }

