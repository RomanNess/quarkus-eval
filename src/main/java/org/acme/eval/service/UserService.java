package org.acme.eval.service;

import org.acme.eval.model.User;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class UserService {

    public List<User> getAll() {
        return List.of(
                User.builder().id(1L).username("foo").build(),
                User.builder().id(2L).username("bar").build()
        );
    }
}
