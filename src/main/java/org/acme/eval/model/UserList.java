package org.acme.eval.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder(builderClassName = "Builder")
@JsonDeserialize(builder = UserList.Builder.class)
public class UserList {

    List<User> users;

    @JsonPOJOBuilder(withPrefix = "")
    public static class Builder {
    }
}
