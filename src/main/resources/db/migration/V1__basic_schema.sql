create sequence hibernate_sequence start 100 increment 1;

create table users
(
    id       int8 not null,
    username varchar(255),
    primary key (id)
);
