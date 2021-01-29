package org.acme.eval.resource;

import org.acme.eval.model.User;
import org.acme.eval.service.UserService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/users")
public class UserResource {

    private final UserService userService;

    @Inject
    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public List<User> getAll() {
        return userService.getAll();
    }
}