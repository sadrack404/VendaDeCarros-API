create table carro (
        id_carro bigint not null auto_increment,
        ano date not null,
        diaria double precision not null,
        modelo varchar(30) not null,
        nome varchar(30) not null,
        placa varchar(7) not null,
        primary key (id_carro)
) engine=InnoDB;
