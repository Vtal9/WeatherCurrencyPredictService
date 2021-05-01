DROP TABLE IF EXISTS  valcurs;
DROP TABLE IF EXISTS  valute;
CREATE TABLE IF NOT EXISTS  valute
(
    id bigserial primary key ,
    num_code text not null ,
    char_code text not null ,
    nominal integer not null ,
    name text not null ,
    value text not null
);

CREATE TABLE IF NOT EXISTS  valcurs
(
    id bigserial primary key ,
    date date ,
    valute_id integer references valute
--     constraint id foreign key (id) references valute (id)
);