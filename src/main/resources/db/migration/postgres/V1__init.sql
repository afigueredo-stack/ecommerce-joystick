CREATE TABLE empresa (
	cod_filial int4 NOT NULL,
	razao_social varchar(265) NOT NULL,
	nome_fantasia varchar(265) NULL,
	cnpj varchar(18) NOT NULL
);

-- DROP SEQUENCE joystick.empresa_seq;

CREATE SEQUENCE empresa_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;

	-- joystick.funcionario definition

-- Drop table

-- DROP TABLE joystick.funcionario;

CREATE TABLE funcionario (
	id int4 NOT NULL,
	cpf varchar(255) NOT NULL,
	data_atualizacao timestamp NOT NULL,
	data_criacao timestamp NOT NULL,
	email varchar(255) NOT NULL,
	nome varchar(255) NOT NULL,
	perfil varchar(255) NOT NULL,
	qtd_horas_almoco float8 NULL,
	qtd_horas_trabalho_dia float8 NULL,
	senha varchar(255) NOT NULL,
	valor_hora numeric(19,2) NULL DEFAULT NULL::numeric,
	empresa_cod_filial int4 NULL
);
CREATE INDEX funcionario_id_idx ON funcionario USING btree (id);

--
-- Indexes for table `funcionario`
--
ALTER TABLE joystick.funcionario
ADD CONSTRAINT funcionario_fk FOREIGN KEY (empresa_id) REFERENCES joystick.empresa(cod_filial);
