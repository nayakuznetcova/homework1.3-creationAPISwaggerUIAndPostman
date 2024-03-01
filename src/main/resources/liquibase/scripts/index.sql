--liquibase formatted sql

--changeset kuznetsovanaya:create_index_by_name_student
create index student_by_name_index on student(name);

--changeset kuznetsovanaya:create_index_by_name_color_faculty
create index faculty_by_name_color_index on faculty(name,color);