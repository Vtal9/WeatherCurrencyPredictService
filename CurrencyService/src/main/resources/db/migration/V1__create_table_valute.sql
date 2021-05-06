CREATE TABLE IF NOT EXISTS  valute
(
    id bigserial primary key ,
    num_code text not null ,
    char_code text not null ,
    nominal integer not null ,
    name text not null ,
    value text not null
);