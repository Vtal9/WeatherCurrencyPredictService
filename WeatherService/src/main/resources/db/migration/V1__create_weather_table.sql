CREATE TABLE IF NOT EXISTS  weather
(
    id bigserial primary key ,
    date DATE ,
    max_temperature numeric(2) NOT NULL,
    min_temperature numeric(2) NOT NULL,
    avg_temperature numeric(2) NOT NULL,
    avg_humidity numeric(2) NOT NULL,
    max_wind numeric(2) NOT NULL,
    city text NOT NULL
);