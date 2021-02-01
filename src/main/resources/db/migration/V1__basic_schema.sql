create sequence hibernate_sequence start 1 increment 1;

create table UserEntity
(
    id       int8 not null,
    username varchar(255),
    primary key (id)
);
