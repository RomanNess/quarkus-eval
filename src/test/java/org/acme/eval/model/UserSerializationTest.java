package org.acme.eval.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;

@QuarkusTest
public class UserSerializationTest {

    @Inject
    ObjectMapper objectMapper;

    @Test
    void serializeUser() throws JsonProcessingException {

        String json = objectMapper.writeValueAsString(provideUser());
        assertThatJson(json).inPath("$.id").isEqualTo(1L);
        assertThatJson(json).inPath("$.username").isEqualTo("foo");
    }

    @Test
    void deserializeUser() throws JsonProcessingException {
        String json = objectMapper.writeValueAsString(provideUser());

        User user = objectMapper.readValue(json, User.class);
        assertThat(user.getId()).isEqualTo(1L);
        assertThat(user.getUsername()).isEqualTo("foo");
    }

    private static User provideUser() {
        return User.builder().id(1L).username("foo").build();
    }
}
