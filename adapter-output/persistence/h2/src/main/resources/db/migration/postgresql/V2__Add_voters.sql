CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

insert into voter (id, first_name, last_name) values (uuid_generate_v4(), 'Dustin', 'Shimono');
insert into voter (id, first_name, last_name) values (uuid_generate_v4(), 'Sandy', 'Shimono');