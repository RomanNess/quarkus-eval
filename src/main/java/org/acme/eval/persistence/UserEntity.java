package org.acme.eval.persistence;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class UserEntity extends PanacheEntity {

    String username;
}
