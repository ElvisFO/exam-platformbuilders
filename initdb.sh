#!/bin/bash
psql -v --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL

CREATE SCHEMA IF NOT EXISTS client;

CREATE TABLE IF NOT EXISTS client.tb_client (
  id BIGINT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  age INT NOT NULL,
  phone VARCHAR(15) NOT NULL,
  email VARCHAR(100) UNIQUE NOT NULL,
  gender VARCHAR(10) NOT NULL
);

INSERT INTO client.tb_client (name, age, phone, email, gender) VALUES
  ('Goku', 85, '316666666', 'goku@gmail.com', 'MASCULINO'),
  ('Vegeta', 92, '31777777', 'vegeta@gmail.com', 'MASCULINO'),
  ('Bulma', 78, '315555555', 'bulma@gmail.com', 'FEMININO');

EOSQL