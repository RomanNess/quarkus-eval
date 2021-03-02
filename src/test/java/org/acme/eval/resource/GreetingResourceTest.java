package org.acme.eval.resource;

import org.acme.eval.MyQuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@MyQuarkusTest
public class GreetingResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
                .when().get("/greet")
                .then()
                .statusCode(200)
                .body(is("Hello RESTEasy"));
    }

}