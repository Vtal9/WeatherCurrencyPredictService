CREATE TABLE IF NOT EXISTS  valcurs
(
    id bigserial primary key ,
    date date ,
    valute_id integer references valute
);