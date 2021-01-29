package org.acme.eval.resource;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.acme.eval.model.User;
import org.acme.eval.model.UserList;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@QuarkusTest
class UserResourceTest {

    @Test
    void getAll() {
        UserList users = given()
                .contentType(ContentType.JSON)
                .when().get("/users")
                .then()
                .statusCode(200)
                .extract().as(UserList.class);

        assertThat(users.getUsers()).containsExactlyInAnyOrder(
                User.builder().id(1L).username("foo").build(),
                User.builder().id(2L).username("bar").build()
        );
    }
}