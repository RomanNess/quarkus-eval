package org.acme.eval.model;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.NotEmpty;

@Value
@Builder
@Jacksonized
public class User {

    Long id;

    @NotEmpty
    String username;
}
