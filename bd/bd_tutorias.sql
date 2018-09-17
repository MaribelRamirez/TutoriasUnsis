
create database tutoriasUnsis;
use  tutoriasUnsis;

create table licenciaturas(
idLicenciatura int UNSIGNED AUTO_INCREMENT PRIMARY KEY,
nombre varchar(20) not null
);
create table grupos(
idGrupo int UNSIGNED AUTO_INCREMENT PRIMARY KEY,	
nombre varchar(10)
);
create table alumnos(
matricula varchar (10) primary key,
nombre varchar(100) not null,
idGrupo  int REFERENCES grupos (idGrupo) ON DELETE CASCADE ON UPDATE CASCADE,
idLicenciatura int REFERENCES licenciaturas (idLicenciatura) ON DELETE CASCADE ON UPDATE CASCADE
);


create table profesores(
idProfesor int UNSIGNED AUTO_INCREMENT PRIMARY KEY,
curp varchar(21)not null,
nombre varchar(100) not null,
grado varchar(20) not null,
estatus varchar(10) not null,
licenciatura int REFERENCES licenciaturas (idLicenciatura) ON DELETE CASCADE ON UPDATE CASCADE
);

create table usuarios(
user varchar(10) primary key,
pass varchar(50) not null,
nivel int(1)
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

create table archivos(
idArchivo int UNSIGNED AUTO_INCREMENT PRIMARY KEY,
nombre varchar (100) not null,
categoria varchar (19) not null,
archivo blob not null
);
Show tables;
