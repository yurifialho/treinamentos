create table usuarios (
id serial primary key,
nome varchar not null
);

create table contas (
id serial primary key,
descricao varchar not null,
usuario_id integer not null
);

alter table contas add constraint fk_contas_usuario_id foreign key (usuario_id)  references usuarios (id) match simple on update no action on delete no action;

create table lancamentos (
id serial primary key,
valor numeric(5,2) not null,
data timestamp not null default now(),
conta_id integer not null
);

alter table lancamentos add constraint fk_lancamentos_contas_id foreign key (conta_id)  references contas (id) match simple on update no action on delete no action;