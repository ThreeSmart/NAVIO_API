drop table if exists coordinates;
drop table if exists messages;
drop table if exists users;
drop table if exists roles;
drop table if exists departments;

create table if not exists departments
(
    id   serial primary key,
    name text unique
);

create table if not exists roles
(
    id   serial primary key,
    type text unique
);

create table if not exists users
(
    id             bigserial primary key,
    name           text    not null,
    surname        text    not null,
    username       text    not null unique,
    password       text    not null,
    email          text    not null unique,
    image_url      text,
    department_id  bigint  not null,
    role_id        int     not null,
    working_status boolean not null,
    status         boolean not null,
    parent_id      bigserial,
    foreign key (department_id) references departments (id),
    foreign key (role_id) references roles (id),
    foreign key (parent_id) references users (id)
);

create table if not exists coordinates
(
    id      bigserial primary key,
    x       double precision not null,
    y       double precision not null,
    z       double precision not null,
    user_id bigint           not null,
    foreign key (user_id) references users (id)
);

create table if not exists messages
(
    id           bigserial primary key,
    content      text   not null,
    from_user_id bigint not null,
    to_user_id   bigint not null,
    sent_time    text   not null,
    seen         boolean default false,
    foreign key (from_user_id) references users (id),
    foreign key (to_user_id) references users (id)
);

insert into roles(type)
values ('EMPLOYEE');
insert into roles(type)
values ('MANAGER');
insert into roles(type)
values ('ADMIN');