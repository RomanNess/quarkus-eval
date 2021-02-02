package org.acme.eval.resource;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.acme.eval.model.User;
import org.acme.eval.model.UserList;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@QuarkusTest
class UserResourceTest {

    @Inject
    Flyway flyway;

    // fixme: poor man's db lifecycle
    @BeforeEach
    void init() {
        flyway.clean();
        flyway.migrate();
    }

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

    @Test
    void get() {
        User user = given()
                .contentType(ContentType.JSON)
                .when().get("/users/1")
                .then()
                .statusCode(200)
                .extract().as(User.class);

        assertThat(user.getId()).isEqualTo(1L);
        assertThat(user.getUsername()).isEqualTo("foo");
    }

    @Test
    void get_404() {
        given()
                .contentType(ContentType.JSON)
                .when().get("/users/666")
                .then()
                .statusCode(404);
    }

    @Test
    void post() {
        given()
                .contentType(ContentType.JSON)
                .body(loadResource("json/createUser.json"))
                .when().post("/users")
                .then()
                .statusCode(204);

        User user = given()
                .contentType(ContentType.JSON)
                .when().get("/users/100")
                .then()
                .statusCode(200)
                .extract().as(User.class);

        assertThat(user.getId()).isEqualTo(100L);
        assertThat(user.getUsername()).isEqualTo("newman");
    }

    @Test
    void put() {
        given()
                .contentType(ContentType.JSON)
                .body(loadResource("json/createUser.json"))
                .when().put("/users/1")
                .then()
                .statusCode(204);

        User user = given()
                .contentType(ContentType.JSON)
                .when().get("/users/1")
                .then()
                .statusCode(200)
                .extract().as(User.class);

        assertThat(user.getId()).isEqualTo(1L);
        assertThat(user.getUsername()).isEqualTo("newman");
    }

    @Test
    void put_404() {
        given()
                .contentType(ContentType.JSON)
                .body(loadResource("json/createUser.json"))
                .when().put("/users/666")
                .then()
                .statusCode(404);
    }

    @Test
    void delete() {
        given()
                .when().delete("/users/1")
                .then()
                .statusCode(204);

        given()
                .contentType(ContentType.JSON)
                .when().get("/users/1")
                .then()
                .statusCode(404);
    }

    @Test
    void delete_404() {
        given()
                .when().delete("/users/666")
                .then()
                .statusCode(404);
    }

    private String loadResource(String filename) {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        try (InputStream is = classloader.getResourceAsStream(filename)) {
            return new Scanner(is, StandardCharsets.UTF_8).useDelimiter("\\Z").next();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}