package org.acme.eval.resource;

import org.acme.eval.model.User;
import org.acme.eval.model.UserList;
import org.acme.eval.service.UserService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    private final UserService userService;
    private final AgroalDataSource agroalDataSource;

    @Inject
    public UserResource(UserService userService, AgroalDataSource agroalDataSource) {
        this.userService = userService;
        this.agroalDataSource = agroalDataSource;
    }

    @GET
    public UserList getAll() {

//        agroalDataSource.flush(AgroalDataSource.FlushMode.INVALID);

        return UserList.builder()
                .users(userService.getAll())
                .build();
    }

    @GET
    @Path("/{id}")
    public User get(@PathParam("id") Long id) {

//        agroalDataSource.flush(AgroalDataSource.FlushMode.INVALID);

        return userService.get(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void post(@Valid User user) {
        userService.post(user);
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void put(@PathParam("id") Long id, @Valid User user) {
        userService.put(id, user);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Long id) {
        userService.delete(id);
    }
}