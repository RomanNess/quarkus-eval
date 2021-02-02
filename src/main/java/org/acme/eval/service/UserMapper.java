package org.acme.eval.service;

import org.acme.eval.model.User;
import org.acme.eval.persistence.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface UserMapper {

    User fromEntity(UserEntity userEntity);

    List<User> fromEntity(List<UserEntity> userEntities);

    @Mapping(target = "id", ignore = true)
    UserEntity toEntity(User user);

    @Mapping(target = "id", ignore = true)
    void fill(@MappingTarget UserEntity target, User user);
}
