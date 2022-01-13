create database prueba;
create table prueba.items (
	id int  NOT NULL auto_increment,
	nombre varchar(30) NOT NULL,
    categoria varchar(30) NOT NULL,
    stock smallint,
    PRIMARY KEY (id)
);

insert into prueba.items (nombre, categoria, stock)
values ('Fideos', 'Harina', 20);
insert into prueba.items (nombre, categoria, stock)
values ('Leche', 'Lacteos', 30);
insert into prueba.items (nombre, categoria, stock)
values ('Crema', 'Lacteos', 15);

select * from prueba.items;

delete from prueba.items
where id = 1;

update prueba.items
set id = 45
where id = 2;

select * from prueba.items;