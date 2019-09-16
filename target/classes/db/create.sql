SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS heroes (
    id int PRIMARY KEY auto_increment,
    hero_name VARCHAR,
    hero_age INTEGER,
    hero_power VARCHAR,
    hero_weakness VARCHAR,
    squad_id INTEGER,
);

CREATE TABLE IF NOT EXISTS squads(
   id int PRIMARY KEY auto_increment,
   squad_name VARCHAR,
   squad_size INTEGER,
   squad_cause VARCHAR,
);