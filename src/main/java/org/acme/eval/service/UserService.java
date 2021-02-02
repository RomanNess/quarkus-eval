package org.acme.eval.service;

import org.acme.eval.model.User;
import org.acme.eval.persistence.UserEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;
import java.util.List;

@ApplicationScoped
public class UserService {

    private final UserMapper userMapper;

    @Inject
    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public List<User> getAll() {
        List<UserEntity> userEntities = UserEntity.findAll().list();
        return userMapper.fromEntity(userEntities);
    }

    public User get(Long id) {
        UserEntity userEntity = UserEntity.<UserEntity>findByIdOptional(id).orElseThrow(NotFoundException::new);
        return userMapper.fromEntity(userEntity);
    }

    @Transactional
    public void post(User user) {
        UserEntity userEntity = userMapper.toEntity(user);
        userEntity.persist();
    }

    @Transactional
    public void put(Long id, User user) {
        UserEntity persistedUser = UserEntity.<UserEntity>findByIdOptional(id).orElseThrow(NotFoundException::new);
        userMapper.fill(persistedUser, user);
    }

    @Transactional
    public void delete(Long id) {
        if (!UserEntity.deleteById(id)) {
            throw new NotFoundException();
        }
    }
}
