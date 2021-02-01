package org.acme.eval.service;

import org.acme.eval.model.User;
import org.acme.eval.persistence.UserEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class UserService {

    public List<User> getAll() {
        List<UserEntity> userEntities = UserEntity.findAll().list();
        return userEntities.stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    public User get(Long id) {
        UserEntity userEntity = UserEntity.<UserEntity>findByIdOptional(id).orElseThrow(NotFoundException::new);
        return map(userEntity);
    }

    private User map(UserEntity userEntity) {
        return User.builder()
                .id(userEntity.id)
                .username(userEntity.getUsername())
                .build();
    }
}
