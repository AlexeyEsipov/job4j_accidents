CREATE TABLE IF NOT EXISTS authorities (
    id serial primary key,
    authority VARCHAR NOT NULL unique
);

CREATE TABLE IF NOT EXISTS users (
    id serial primary key,
    username VARCHAR NOT NULL unique,
    password VARCHAR NOT NULL,
    enabled boolean default true,
    authority_id int not null references authorities(id)
);

insert into authorities (authority) values ('ROLE_USER');
insert into authorities (authority) values ('ROLE_ADMIN');

insert into users (username, enabled, password, authority_id)
values ('root', true, '$2a$10$CU/mFqLGvea3FY4/x36T/eFllVjqA9cFbjIBZepvRdGBRfYgVCyCq',
        (select id from authorities where authority = 'ROLE_ADMIN'));