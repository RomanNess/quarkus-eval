package org.acme.eval.service;

import org.acme.eval.model.User;
import org.acme.eval.persistence.UserEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface UserMapper {

    User fromEntity(UserEntity userEntity);

    List<User> fromEntity(List<UserEntity> userEntities);
}
