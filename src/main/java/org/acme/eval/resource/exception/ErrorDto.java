package org.acme.eval.resource.exception;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(builderClassName = "Builder")
@JsonDeserialize(builder = ErrorDto.Builder.class)
public class ErrorDto {

    String message;

    @JsonPOJOBuilder(withPrefix = "")
    public static class Builder {
    }
}
