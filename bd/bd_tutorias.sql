drop database bd_tutorias;

create database bd_tutorias;
use  bd_tutorias;

create table licenciaturas(
idLicenciatura int UNSIGNED AUTO_INCREMENT PRIMARY KEY,
nombre varchar(20) not null
);

create table alumnos(
matricula varchar(10) primary key,
nombre varchar(20) not null,
apellidoP varchar(20) not null,
apellidoM varchar(20) not null,
grupo int,
idLicenciatura int REFERENCES licenciaturas (idLicenciatura) ON DELETE CASCADE ON UPDATE CASCADE
);

create table profesores(
idProfesor int UNSIGNED AUTO_INCREMENT PRIMARY KEY,
nombre varchar(20) not null,
apellidoP varchar(20) not null,
apellidoM varchar(20) not null,
licenciatura int REFERENCES licenciaturas (idLicenciatura) ON DELETE CASCADE ON UPDATE CASCADE
);

create table usuarios(
user varchar(10) primary key,
pass varchar(50) not null,
tipo int
);

create table usuarioProfesor(
idUserProfesor int UNSIGNED AUTO_INCREMENT PRIMARY KEY,
idUser varchar(10)REFERENCES usuarios (user) ON DELETE CASCADE ON UPDATE CASCADE,
idProfesor int REFERENCES profesores (profesores) ON DELETE CASCADE ON UPDATE CASCADE
);

create table usuarioAlumno(
idUserProfesor int UNSIGNED AUTO_INCREMENT PRIMARY KEY,
idUser varchar(10)REFERENCES usuarios (user) ON DELETE CASCADE ON UPDATE CASCADE,
matricula varchar(10) REFERENCES alumnos (matricula) ON DELETE CASCADE ON UPDATE CASCADE
);

create table tutores(
idTutorado int UNSIGNED AUTO_INCREMENT PRIMARY KEY,
matricula varchar(10) REFERENCES alumnos (matricula) ON DELETE CASCADE ON UPDATE CASCADE,
idProfesor int REFERENCES profesores (profesores) ON DELETE CASCADE ON UPDATE CASCADE,
preriodo varchar (10),
tipo int 
);

create table tutorias(
idTutoria int UNSIGNED AUTO_INCREMENT PRIMARY KEY,
idTutorado int REFERENCES tutores (idTutorado) ON DELETE CASCADE ON UPDATE CASCADE,
fecha date,
noTutoria int,
reporte text
);


Show tables;

drop table profesores;