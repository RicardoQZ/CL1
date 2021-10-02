DROP DATABASE IF EXISTS cl1_dawi;

CREATE DATABASE cl1_dawi;

USE cl1_dawi;

CREATE TABLE autor(
cod_autor int not null primary key,
nom_autor varchar(50),
edad_autor int
);

create table libro(
id_libro int not null primary key,
descri_libro varchar(100),
cod_autor int,
genero_libro varchar(50),
stock_libro int,
precio decimal(8,2),
constraint ft_autor foreign key (cod_autor) references autor(cod_autor) 
);

-- inserts
insert into autor values (1, 'GOETHE',50);
insert into autor values (2, 'VICTOR HUGO',60);
insert into autor values (3, 'HERMAN MELVILLE',45);

insert into libro values (1, 'Transbordo en Moscú',1,'dramatico',5,2.3);
insert into libro values (2, 'El santuario de los elefantes. Nativel Preciado',2,'romantico',8,3.5);
insert into libro values (3, 'Castellano. Lorenzo Silva',3,'terror',4,4.5);

-- consultas
SELECT * FROM autor;
SELECT * FROM libro;