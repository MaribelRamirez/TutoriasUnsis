drop database tutoriasunsis;
create database tutoriasUnsis;
use  tutoriasUnsis;

create table licenciaturas(
idLicenciatura int UNSIGNED AUTO_INCREMENT PRIMARY KEY,
nombre varchar(20) not null
);

drop table grupos;
-- agregue periodo y licenciatura en el grupo y se cambio en id incremental por el grupo en si
create table grupos(	
idGrupo int UNSIGNED AUTO_INCREMENT PRIMARY KEY,
grupo varchar(10),	
idPeriodo int  REFERENCES periodo (idPeriodo) ON DELETE CASCADE ON UPDATE CASCADE,
idLicenciatura int REFERENCES licenciaturas (idLicenciatura) ON DELETE CASCADE ON UPDATE CASCADE
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
nivel int(1),
curp varchar(20)  REFERENCES profesores (profesores) ON DELETE CASCADE ON UPDATE CASCADE
);



create table tutores(
idTutorado int UNSIGNED AUTO_INCREMENT PRIMARY KEY,
matricula varchar(10) REFERENCES alumnos (matricula) ON DELETE CASCADE ON UPDATE CASCADE,
curp  varchar(20) REFERENCES profesores (profesores) ON DELETE CASCADE ON UPDATE CASCADE,
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
-- agregue la tablas periodo
create table periodo(
idPeriodo int UNSIGNED AUTO_INCREMENT PRIMARY KEY,
periodo varchar (7) not null,
fechaInicio date,
fechaFin Date
);


Show tables;
