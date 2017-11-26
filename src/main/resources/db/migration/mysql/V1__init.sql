create table `empresa` (
	`id` bigint(20) not null,
	`cnpj` varchar(255) not null,
	`data_atualizacao` datetime not null,
	`data_criacao` datetime not null,
	`razao_social` varchar(255) not null
) engine=innodb default charset=utf8;

create table `funcionario` (
	`id` bigint(20) not null,
	`cpf` varchar(255) not null,
	`data_atualizacao` datetime not null,
	`data_criacao` datetime not null,
	`email` varchar(255) not null,
	`nome` varchar(255) not null,
	`perfil` varchar(255) not null,
	`qtd_horas_almoco` float default null,
	`qtd_horas_trabalho_dia` float default null,
	`senha` varchar(255) not null,
	`valor_hora` decimal(19,2) default null,
	`empresa_id` bigint(20) default null
) engine=innodb default charset=utf8;

create table `lancamento` (
	`id` bigint(20) not null,
	`data` datetime not null,
	`data_atualizacao` datetime not null,
	`data_criacao` datetime not null,
	`descricao` varchar(255) default null,
	`localizacao` varchar(255) default null,
	`tipo` varchar(255) not null,
	`funcionario_id` bigint(20) default null
) engine=innodb default charset=utf8;

--indexes

alter table `funcionario`
	add primary key (`id`);

alter table `lancamento`
	add primary key (`id`);
	
--auto increment

alter table `empresa`
	modify `id` bigint(20) not null auto_increment;

alter table `funcionario`
	modify `id` bigint(20) not null auto_increment;
	
alter table `lancamento`
	modify `id` bigint(20) not null auto_increment;	

--constraint

alter table `funcionario`
add constraint foreign key (`empresa_id`) references `empresa`(`id`);

alter table `lancamento`
add constraint foreign key (`funcionario_id`) references `funcionario`(`id`);
