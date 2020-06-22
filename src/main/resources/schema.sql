create table req
(
    id       bigint not null
        constraint req_pkey
            primary key,
    request  varchar(255),
    response text
);