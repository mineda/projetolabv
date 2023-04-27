drop table if exists lnc_lancamento;
drop table if exists cmt_comentario;
drop table if exists ant_anotacao;
drop table if exists uau_usuario_autorizacao;
drop table if exists aut_autorizacao;
drop table if exists usr_usuario; 

create table usr_usuario (
  usr_id bigint not null auto_increment,
  usr_nome varchar(20) not null,
  usr_senha varchar(100) not null,
  primary key (usr_id),
  unique (usr_nome)
);

create table aut_autorizacao (
  aut_id bigint not null auto_increment,
  aut_nome varchar(20) not null,
  primary key (aut_id),
  unique (aut_nome)
);

create table uau_usuario_autorizacao (
  usr_id bigint not null,
  aut_id bigint not null,
  primary key (usr_id, aut_id),
  foreign key (usr_id) references usr_usuario (usr_id) on delete restrict on update cascade,
  foreign key (aut_id) references aut_autorizacao (aut_id) on delete restrict on update cascade
);

create table ant_anotacao (
  ant_id bigint not null auto_increment,
  ant_texto varchar(200) not null,
  ant_data_hora datetime not null default current_timestamp,
  ant_usr_id bigint not null,
  primary key(ant_id),
  foreign key (ant_usr_id) references usr_usuario (usr_id)
);

create table cmt_comentario (
  cmt_id bigint not null auto_increment,
  cmt_texto varchar(200) not null,
  cmt_data_hora datetime not null default current_timestamp,
  cmt_ant_id bigint not null,
  primary key(cmt_id),
  foreign key (cmt_ant_id) references ant_anotacao (ant_id)
);

create table lnc_lancamento (
  lnc_id bigint not null auto_increment,
  lnc_descricao varchar(200) not null,
  lnc_data_hora_inicio datetime not null,
  lnc_duracao float not null,
  lnc_usr_id bigint not null,
  primary key(lnc_id),
  foreign key (lnc_usr_id) references usr_usuario (usr_id)
);
