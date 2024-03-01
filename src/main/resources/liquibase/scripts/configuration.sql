--liquibase formatted sql

--changeset kuznetsovanaya:create_table_faculty
create table faculty (
    id bigserial primary key,
    name text,
    color varchar(255)
);

--changeset kuznetsovanaya:create_table_student
create table student (
    id bigserial primary key,
    name text,
    age integer,
    faculty_id bigint,
    foreign key (faculty_id) references faculty (id)
);

--changeset kuznetsovanaya:create_table_avatar
create table avatar (
    id bigserial primary key,
    file_path text,
    file_size bigint,
    media_type text,
    data bytea,
    student_id bigint,
    foreign key (student_id) references student (id)
);
