--liquibase formatted sql

--changeset kuznetsovanaya:create_422
create table car(
    id bigserial primary key,
    model text,
    brand varchar(50),
    cost bigint
);

create table driver(
    id bigserial primary key,
    name text,
    age integer,
    license boolean,
    car_id bigint references car(id)
);


