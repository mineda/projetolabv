drop user if exists 'user'@'localhost';

drop schema if exists anotacao;

create schema anotacao;

use anotacao;

create user 'user'@'localhost' identified by 'pass123';

grant select, insert, delete, update on anotacao.* to user@'localhost';

create table usr_usuario (
  usr_id bigint unsigned not null auto_increment,
  usr_nome varchar(20) not null,
  usr_senha varchar(100) not null,
  primary key (usr_id),
  unique key uni_usuario_nome (usr_nome)
);

create table aut_autorizacao (
  aut_id bigint unsigned not null auto_increment,
  aut_nome varchar(20) not null,
  primary key (aut_id),
  unique key uni_aut_nome (aut_nome)
);

create table uau_usuario_autorizacao (
  usr_id bigint unsigned not null,
  aut_id bigint unsigned not null,
  primary key (usr_id, aut_id),
  foreign key aut_usuario_fk (usr_id) references usr_usuario (usr_id) on delete restrict on update cascade,
  foreign key aut_autorizacao_fk (aut_id) references aut_autorizacao (aut_id) on delete restrict on update cascade
);

create table ant_anotacao (
  ant_id bigint unsigned not null auto_increment,
  ant_texto varchar(200) not null,
  ant_data_hora datetime not null default current_timestamp,
  ant_usr_id bigint unsigned not null,
  primary key(ant_id),
  foreign key ant_usuario_fk (ant_usr_id) references usr_usuario (usr_id)
);

create table cmt_comentario (
  cmt_id bigint unsigned not null auto_increment,
  cmt_texto varchar(200) not null,
  cmt_data_hora datetime not null default current_timestamp,
  cmt_ant_id bigint unsigned not null,
  primary key(cmt_id),
  foreign key cmt_ant_fk (cmt_ant_id) references ant_anotacao (ant_id)
);

create table lnc_lancamento (
  lnc_id bigint unsigned not null auto_increment,
  lnc_descricao varchar(200) not null,
  lnc_data_hora_inicio datetime not null,
  lnc_duracao float not null,
  lnc_usr_id bigint unsigned not null,
  primary key(lnc_id),
  foreign key lnc_usr_fk (lnc_usr_id) references usr_usuario (usr_id)
);

create table dcm_documento (
  dcm_id bigint unsigned not null auto_increment,
  dcm_tipo varchar(10) not null,
  dcm_numero bigint not null,
  dcm_digito int,
  dcm_data_emissao date not null,
  dcm_data_expiracao date,
  dcm_usr_id bigint unsigned not null,
  primary key(dcm_id),
  unique(dcm_tipo, dcm_numero),
  foreign key dcm_usr_fk (dcm_usr_id) references usr_usuario (usr_id)
);

insert into usr_usuario (usr_nome, usr_senha)
    values ('admin', '$2a$10$i3.Z8Yv1Fwl0I5SNjdCGkOTRGQjGvHjh/gMZhdc3e7LIovAklqM6C');
insert into aut_autorizacao (aut_nome)
    values ('ROLE_ADMIN');
insert into uau_usuario_autorizacao (usr_id, aut_id) 
    values (1, 1);
insert into ant_anotacao (ant_texto, ant_usr_id) 
    values ('Esta é uma anotação de teste!', 1);
insert into cmt_comentario (cmt_texto, cmt_ant_id)
    values ('Essa anotação me ajudou muito na prova', 1);
insert into lnc_lancamento (lnc_descricao, lnc_data_hora_inicio, lnc_duracao, lnc_usr_id)
    values('Prova', '2023-04-27 19:10:00', 2.1, 1);
insert into dcm_documento (dcm_tipo, dcm_numero, dcm_digito, dcm_data_emissao, dcm_usr_id)
    values('rg', 11222333, 5, '2020-03-12', 1);
