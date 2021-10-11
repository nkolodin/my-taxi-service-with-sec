create sequence if not exists user_seq
    start 1;

create table if not exists user_table
(
    user_id bigint      not null default nextval('user_seq' :: regclass),
    login    varchar(30)      not null,
    password   varchar(30) not null
);
