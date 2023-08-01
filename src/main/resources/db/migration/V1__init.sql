create table transactions (
id          bigserial primary key not null,
code        varchar(255) not null,
type        varchar(20) not null,
total       numeric(15,4) not null,
discount    numeric(15,4) not null,
balance     numeric(15,4) not null,
status      varchar(20) not null,
token       varchar(50) not null,
date_token  timestamp    not null,
customer    varchar(255) not null
unique(code)
);
