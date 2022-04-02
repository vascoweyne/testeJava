--Cliente
create table "tb_cliente"
(
  id integer constraint tb_cliente_pk primary key not null
, inclusao timestamp not null
, alteracao timestamp
, status integer
, nome varchar(255)
, cpf varchar(14)
, cnpj varchar(18)
, telefone varchar
);

create sequence "seq_tb_cliente"  minvalue 1 maxvalue 999999999999999 increment by 1 start with 2;

--Funcionario
create table "tb_funcionario"
(
  id integer constraint tb_funcionario_pk primary key not null
, inclusao timestamp not null
, alteracao timestamp
, status integer not null
, nome varchar(255)
, cpf varchar(14)
, telefone varchar
, data_pagamento date
);

create sequence "seq_tb_funcionario"  minvalue 1 maxvalue 999999999999999 increment by 1 start with 2;


--Produto
create table "tb_produto"
(
  id integer constraint tb_produto_pk primary key not null
, inclusao timestamp not null
, alteracao timestamp
, status integer not null
, nome varchar(255)
, codigo varchar(100)
, valor numeric
);

create sequence "seq_tb_produto"  minvalue 1 maxvalue 999999999999999 increment by 1 start with 2;

--Estoque
create table "tb_estoque"
(
  id integer constraint tb_estoque_pk primary key not null
, inclusao timestamp not null
, alteracao timestamp
, status integer
, quatidade float
, id_produto integer
);

create sequence "seq_tb_estoque"  minvalue 1 maxvalue 999999999999999 increment by 1 start with 2;
alter table "tb_estoque" add foreign key (id_produto) references tb_produto(id);

--Loja
create table "tb_loja"
(
  id integer constraint tb_loja_pk primary key not null
, inclusao timestamp not null
, alteracao timestamp
, status integer
, nome varchar
, telefone varchar
, endereco varchar
);

create sequence "seq_tb_loja"  minvalue 1 maxvalue 999999999999999 increment by 1 start with 2;


--Pedido
create table "tb_pedido"
(
  id integer constraint tb_pedido_pk primary key not null
, inclusao timestamp not null
, alteracao timestamp
, id_cliente integer not null
, valor numeric not null
, status varchar
, metodo varchar
, parcelas integer
, id_loja integer
, id_funcionario integer
);

create sequence "seq_tb_pedido"  minvalue 1 maxvalue 999999999999999 increment by 1 start with 2;
alter table "tb_pedido" add foreign key (id_cliente) references tb_cliente(id);
alter table "tb_pedido" add foreign key (id_loja) references tb_loja(id);
alter table "tb_pedido" add foreign key (id_funcionario) references tb_funcionario(id);

--Pedido Produto
create table "tb_pedido_produto"
(
  id integer constraint tb_pedido_produto_pk primary key not null
, inclusao timestamp not null
, alteracao timestamp
, id_pedido integer not null
, id_produto integer not null
, quantidade float not null
, valor numeric not null
);

create sequence "seq_tb_pedido_produto"  minvalue 1 maxvalue 999999999999999 increment by 1 start with 2;
alter table "tb_pedido_produto" add foreign key (id_pedido) references tb_pedido(id);
alter table "tb_pedido_produto" add foreign key (id_produto) references tb_produto(id);


--Cobrança
create table "tb_cobranca"
(
  id integer constraint tb_cobranca_pk primary key not null
, inclusao timestamp not null
, alteracao timestamp
, data_cobranca date
, status varchar not null
, id_pedido integer not null
);

create sequence "seq_tb_cobranca"  minvalue 1 maxvalue 999999999999999 increment by 1 start with 2;
alter table "tb_cobranca" add foreign key (id_pedido) references tb_pedido(id);

--Retirada
create table "tb_retirada"
(
  id integer constraint tb_retirada_pk primary key not null
, inclusao timestamp not null
, alteracao timestamp
, valor numeric not null
, motivo varchar(255) not null
, responsavel varchar(255) not null
);

create sequence "seq_tb_retirada"  minvalue 1 maxvalue 999999999999999 increment by 1 start with 2;

