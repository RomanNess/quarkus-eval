package org.acme.eval.resource;

import org.acme.eval.model.UserList;
import org.acme.eval.service.UserService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/users")
public class UserResource {

    private final UserService userService;

    @Inject
    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public UserList getAll() {
        return UserList.builder()
                .users(userService.getAll())
                .build();
    }
}