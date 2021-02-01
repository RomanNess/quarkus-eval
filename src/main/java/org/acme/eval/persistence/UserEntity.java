package org.acme.eval.persistence;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.Getter;

import javax.persistence.Entity;

@Getter
@Entity
public class UserEntity extends PanacheEntity {

    String username;
}
