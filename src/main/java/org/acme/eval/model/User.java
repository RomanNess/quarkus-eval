package org.acme.eval.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class User {

    Long id;
    String username;
}
