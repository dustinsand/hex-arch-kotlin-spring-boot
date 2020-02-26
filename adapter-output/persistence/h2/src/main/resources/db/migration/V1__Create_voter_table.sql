CREATE SCHEMA IF NOT EXISTS VOTER;

create table voter (
    id UUID PRIMARY KEY not null,
    first_name varchar(50) not null,
    last_name varchar(50) not null
);