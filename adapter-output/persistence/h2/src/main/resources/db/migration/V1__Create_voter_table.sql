CREATE SCHEMA IF NOT EXISTS VOTER;

create table voter (
    id UUID PRIMARY KEY not null,
    first_name varchar(50) not null,
    last_name varchar(50) not null,
    social_security_number varchar(11) not null
);