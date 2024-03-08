CREATE TABLE IF NOT EXISTS pessoa(
    id                  BIGSERIAL      NOT NULL,
    identificador       VARCHAR(50)    NOT NULL,
    nome       			VARCHAR(50)    NOT NULL,
	data_nascimento     TIMESTAMP      NOT NULL,
    tipo_identificador  VARCHAR(50)    NOT NULL,
    valor_minimo_mensal numeric(18, 4) NOT NULL,
	valor_maximo_emprestimo numeric(18, 4) NOT NULL,
	constraint pessoa_pkey primary key(id),
    constraint pessoa_akey_01 unique(identificador)
);

CREATE TABLE IF NOT EXISTS emprestimo(
    id                  BIGSERIAL       NOT NULL,
    id_pessoa 			bigint 			NOT NULL,
	valor_emprestimo 	numeric(18, 4)  NOT NULL,
	numero_parcelas 	int4 NOT NULL,
	status_pagamento   	VARCHAR(50)    NOT NULL,
	data_criacao     	TIMESTAMP      NOT NULL, 
	constraint emprestimo_pkey primary key(id)
);

-- relacionamentos
alter table emprestimo add constraint fk_emprestimo_pessoa foreign key (id_pessoa) references pessoa(id);