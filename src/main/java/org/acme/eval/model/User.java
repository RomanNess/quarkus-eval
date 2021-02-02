package org.acme.eval.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotEmpty;

@Value
@Builder(builderClassName = "Builder")
@JsonDeserialize(builder = User.Builder.class)
public class User {

    Long id;

    @NotEmpty
    String username;

    @JsonPOJOBuilder(withPrefix = "")
    public static class Builder {
    }
}
