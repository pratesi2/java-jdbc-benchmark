create database sampledb;
CREATE TABLE employees (
                           id serial PRIMARY KEY,
                           name varchar(255) default NULL,
                           phone varchar(100) default NULL,
                           email varchar(255) default NULL,
                           address varchar(255) default NULL,
                           country varchar(100) default NULL
);

create sequence seq_employee start 1;