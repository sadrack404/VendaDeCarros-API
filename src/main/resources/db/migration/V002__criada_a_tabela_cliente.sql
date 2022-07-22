create table cliente (
        id_cliente bigint not null auto_increment,
        cnh varchar(7) not null,
        cidade varchar(30),
        quadra varchar(30),
        rua varchar(30),
        nome varchar(30) not null,
        celular varchar(9),
        ddd varchar(99),
        telefone varchar(255),
primary key (id_cliente)
) engine=InnoDB;