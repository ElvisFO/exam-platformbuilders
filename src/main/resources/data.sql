DROP TABLE IF EXISTS tb_client;

CREATE TABLE tb_client (
  id BIGINT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  age INT NOT NULL,
  phone VARCHAR(15) NOT NULL,
  email VARCHAR(100) NOT NULL,
  gender VARCHAR(10) NOT NULL
);

INSERT INTO tb_client (name, age, phone, email, gender) VALUES
  ('Goku', 85, '316666666', 'goku@gmail.com', 'MASCULINO'),
  ('Vegeta', 92, '31777777', 'vegeta@gmail.com', 'MASCULINO'),
  ('Bulma', 78, '315555555', 'bulma@gmail.com', 'FEMININO');