CREATE DATABASE teste_crud;

USE teste_crud;

CREATE TABLE cliente (
  id BIGINT NOT NULL AUTO_INCREMENT,
  nome VARCHAR(255) NOT NULL,
  tipo ENUM('PF', 'PJ') NOT NULL,
  cpf_cnpj VARCHAR(20) NOT NULL,
  rg_ie VARCHAR(20),
  data_cadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  ativo BOOLEAN NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT cpf_cnpj_unique UNIQUE (cpf_cnpj)
);

CREATE TABLE telefone (
  id BIGINT NOT NULL AUTO_INCREMENT,
  numero VARCHAR(20) NOT NULL,
  cliente_id BIGINT NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT telefone_cliente_fk FOREIGN KEY (cliente_id) REFERENCES cliente(id) ON DELETE CASCADE
);