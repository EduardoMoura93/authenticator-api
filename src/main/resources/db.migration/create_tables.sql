CREATE TABLE usuario (
  id INT NOT NULL,
  nome varchar(50) NOT NULL,
  usuario varchar(50) NOT NULL,
  senha varchar(255) NOT NULL,
CONSTRAINT usuario_pkey PRIMARY KEY (id)
);

INSERT INTO usuario(
nome, senha, usuario)
VALUES ('Eduardo Moura', '$2a$10$no9M0Ndc64wJ33548o.yWu5YLoVohDb1n43xY7cavxNNsJY0Xqs.C', 'authenticator');