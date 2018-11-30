use tutoriasunsis;

-- periodos
INSERT INTO `periodo` (`idPeriodo`,`periodo`,`fechaInicio`,`fechaFin`) VALUES (1,'oct17-feb18','2018-10-01','2019-02-28');

-- licenciaturas

INSERT INTO `licenciaturas` (`idLicenciatura`,`nombre`) VALUES (1,'LI');
INSERT INTO `licenciaturas` (`idLicenciatura`,`nombre`) VALUES (2,'LO');
INSERT INTO `licenciaturas` (`idLicenciatura`,`nombre`) VALUES (3,'LAM');
INSERT INTO `licenciaturas` (`idLicenciatura`,`nombre`) VALUES (4,'LAE');
INSERT INTO `licenciaturas` (`idLicenciatura`,`nombre`) VALUES (5,'LN');
INSERT INTO `licenciaturas` (`idLicenciatura`,`nombre`) VALUES (6,'LE');

-- grupos

INSERT INTO `grupos` (`idGrupo`,`grupo`,`idPeriodo`,`idLicenciatura`) VALUES (1,'106',1,1);
INSERT INTO `grupos` (`idGrupo`,`grupo`,`idPeriodo`,`idLicenciatura`) VALUES (2,'306',1,1);
INSERT INTO `grupos` (`idGrupo`,`grupo`,`idPeriodo`,`idLicenciatura`) VALUES (3,'506',1,1);
INSERT INTO `grupos` (`idGrupo`,`grupo`,`idPeriodo`,`idLicenciatura`) VALUES (4,'706',1,1);
INSERT INTO `grupos` (`idGrupo`,`grupo`,`idPeriodo`,`idLicenciatura`) VALUES (5,'906',1,1);
INSERT INTO `grupos` (`idGrupo`,`grupo`,`idPeriodo`,`idLicenciatura`) VALUES (6,'104-A',1,2);
INSERT INTO `grupos` (`idGrupo`,`grupo`,`idPeriodo`,`idLicenciatura`) VALUES (7,'104-B',1,2);
INSERT INTO `grupos` (`idGrupo`,`grupo`,`idPeriodo`,`idLicenciatura`) VALUES (8,'305',1,3);
INSERT INTO `grupos` (`idGrupo`,`grupo`,`idPeriodo`,`idLicenciatura`) VALUES (9,'302',1,3);
INSERT INTO `grupos` (`idGrupo`,`grupo`,`idPeriodo`,`idLicenciatura`) VALUES (10,'509',1,4);
INSERT INTO `grupos` (`idGrupo`,`grupo`,`idPeriodo`,`idLicenciatura`) VALUES (11,'508',1,4);
INSERT INTO `grupos` (`idGrupo`,`grupo`,`idPeriodo`,`idLicenciatura`) VALUES (12,'905',1,5);
INSERT INTO `grupos` (`idGrupo`,`grupo`,`idPeriodo`,`idLicenciatura`) VALUES (13,'705',1,5);
INSERT INTO `grupos` (`idGrupo`,`grupo`,`idPeriodo`,`idLicenciatura`) VALUES (14,'502',1,6);
INSERT INTO `grupos` (`idGrupo`,`grupo`,`idPeriodo`,`idLicenciatura`) VALUES (15,'302',1,6);
INSERT INTO `grupos` (`idGrupo`,`grupo`,`idPeriodo`,`idLicenciatura`) VALUES (16,'102',1,6);

-- Alumnos
INSERT INTO `alumnos` (`matricula`,`nombre`,`idGrupo`,`idLicenciatura`) VALUES ('2012060148','MARISOL GARCIA LOPEZ',2,1);
INSERT INTO `alumnos` (`matricula`,`nombre`,`idGrupo`,`idLicenciatura`) VALUES ('2012060149','MARIBEL RAMIREZ SANCHEZ',3,1);
INSERT INTO `alumnos` (`matricula`,`nombre`,`idGrupo`,`idLicenciatura`) VALUES ('2012060150','MONICA MONTERO HERNANDEZ',3,3);
INSERT INTO `alumnos` (`matricula`,`nombre`,`idGrupo`,`idLicenciatura`) VALUES ('2012060151','MATIAS LOPEZ LOPEZ',3,4);
INSERT INTO `alumnos` (`matricula`,`nombre`,`idGrupo`,`idLicenciatura`) VALUES ('2012060162','ALEXANDER MARINO LOPEZ',2,3);
INSERT INTO `alumnos` (`matricula`,`nombre`,`idGrupo`,`idLicenciatura`) VALUES ('2012060163','SALVADOR PANTOJA ROJAS',2,1);
INSERT INTO `alumnos` (`matricula`,`nombre`,`idGrupo`,`idLicenciatura`) VALUES ('2012060164','WENDY MENDEZ LOPEZ',7,6);
INSERT INTO `alumnos` (`matricula`,`nombre`,`idGrupo`,`idLicenciatura`) VALUES ('2012060165','ESMERALDA DE JESUS RAMIREZ SANCHEZ',7,6);
INSERT INTO `alumnos` (`matricula`,`nombre`,`idGrupo`,`idLicenciatura`) VALUES ('2012060166','JOSE RAMIREZ JARQUIN',7,6);
INSERT INTO `alumnos` (`matricula`,`nombre`,`idGrupo`,`idLicenciatura`) VALUES ('2012060167','ESTELA JARQUIN SALGADO',2,1);
INSERT INTO `alumnos` (`matricula`,`nombre`,`idGrupo`,`idLicenciatura`) VALUES ('2012060168','TORIBIO JOAQUIN SANTOS',3,1);
INSERT INTO `alumnos` (`matricula`,`nombre`,`idGrupo`,`idLicenciatura`) VALUES ('2012060178','ABEL RAMIREZ PACHECO',3,1);
INSERT INTO `alumnos` (`matricula`,`nombre`,`idGrupo`,`idLicenciatura`) VALUES ('2012060180','DANIELA YUSLEIVI RAMIREZ PINACHO',7,6);
INSERT INTO `alumnos` (`matricula`,`nombre`,`idGrupo`,`idLicenciatura`) VALUES ('2012060181','JOSE  ANTONIO LOPEZ',5,1);
INSERT INTO `alumnos` (`matricula`,`nombre`,`idGrupo`,`idLicenciatura`) VALUES ('2012060182','SANDRA JARQUIN RIOS',2,4);
INSERT INTO `alumnos` (`matricula`,`nombre`,`idGrupo`,`idLicenciatura`) VALUES ('2012060183','PAULA LANDETA FIGUEROA',6,2);
INSERT INTO `alumnos` (`matricula`,`nombre`,`idGrupo`,`idLicenciatura`) VALUES ('2012060184','JAVIER LUNA XARIN',5,2);
INSERT INTO `alumnos` (`matricula`,`nombre`,`idGrupo`,`idLicenciatura`) VALUES ('2012060200','JARQUIN JARQUIN MONCE',8,6);
INSERT INTO `alumnos` (`matricula`,`nombre`,`idGrupo`,`idLicenciatura`) VALUES ('2012060201','LUZ MONTERO MONTERO',8,6);
INSERT INTO `alumnos` (`matricula`,`nombre`,`idGrupo`,`idLicenciatura`) VALUES ('2012060202','NOE OLIVERA OLIVERA',7,6);
INSERT INTO `alumnos` (`matricula`,`nombre`,`idGrupo`,`idLicenciatura`) VALUES ('2222183','mine',1,1);
INSERT INTO `alumnos` (`matricula`,`nombre`,`idGrupo`,`idLicenciatura`) VALUES ('4','hj',1,1);
INSERT INTO `alumnos` (`matricula`,`nombre`,`idGrupo`,`idLicenciatura`) VALUES ('54','kj',1,1);
INSERT INTO `alumnos` (`matricula`,`nombre`,`idGrupo`,`idLicenciatura`) VALUES ('545','NBVBM',1,1);
INSERT INTO `alumnos` (`matricula`,`nombre`,`idGrupo`,`idLicenciatura`) VALUES ('7545','jg',1,1);

-- profesores


INSERT INTO `profesores` (`idProfesor`,`curp`,`nombre`,`grado`,`estatus`,`licenciatura`) VALUES (1,'RIRA651211MOCR007','M.A. MINERVA MARTINEZ RIOS','Licenciatura','Activo',2);
INSERT INTO `profesores` (`idProfesor`,`curp`,`nombre`,`grado`,`estatus`,`licenciatura`) VALUES (2,'RASA931105MOCR008','M.A. MARIBEL RAMIREZ SANCHEZ','Doctorado','Activo',2);
INSERT INTO `profesores` (`idProfesor`,`curp`,`nombre`,`grado`,`estatus`,`licenciatura`) VALUES (3,'AUAR931105MOCR118','M.F. Aurea Arellano Cruz  ','Maestria','Activo',2);
INSERT INTO `profesores` (`idProfesor`,`curp`,`nombre`,`grado`,`estatus`,`licenciatura`) VALUES (4,'ELBR931105MOCR118','M.G.P. Eleazar Brena García ','Maestria','Activo',2);
INSERT INTO `profesores` (`idProfesor`,`curp`,`nombre`,`grado`,`estatus`,`licenciatura`) VALUES (5,'JOLA931105MOCR118','M.E. Jorge Lamas Carlos ','Maestria','Activo',2);
INSERT INTO `profesores` (`idProfesor`,`curp`,`nombre`,`grado`,`estatus`,`licenciatura`) VALUES (6,'OSVI931105MOCR118','M.I.E. Óscar Vicente López Hernández','Maestria','Activo',1);
INSERT INTO `profesores` (`idProfesor`,`curp`,`nombre`,`grado`,`estatus`,`licenciatura`) VALUES (7,'ROMA931105MOCR118','M.C.S. Rosario Maya Lucas','Maestria','Activo',1);
INSERT INTO `profesores` (`idProfesor`,`curp`,`nombre`,`grado`,`estatus`,`licenciatura`) VALUES (8,'ELAP931105MOCR118','M.C. Elsa Apolonia Mendoza Cortés','Maestria','Activo',1);
INSERT INTO `profesores` (`idProfesor`,`curp`,`nombre`,`grado`,`estatus`,`licenciatura`) VALUES (9,'JOET931105MOCR118','M.C.P. Joann Etienne Olivier Picard','Maestria','Activo',1);
INSERT INTO `profesores` (`idProfesor`,`curp`,`nombre`,`grado`,`estatus`,`licenciatura`) VALUES (10,'EMLO931105MOCR118','M.P.E.M. Emanuel Lorenzo Ramírez Arellanes','Maestria','Activo',1);
INSERT INTO `profesores` (`idProfesor`,`curp`,`nombre`,`grado`,`estatus`,`licenciatura`) VALUES (11,'GLSR931105MOCR118','M.G. Gloria Stella Ramirez Ospitia','Maestria','Activo',3);
INSERT INTO `profesores` (`idProfesor`,`curp`,`nombre`,`grado`,`estatus`,`licenciatura`) VALUES (12,'OSJR931105MOCR118','M.D.I. Óscar Jesse Rojas Ortíz','Maestria','Activo',3);
INSERT INTO `profesores` (`idProfesor`,`curp`,`nombre`,`grado`,`estatus`,`licenciatura`) VALUES (13,'EPSH931105MOCR118','M.A. Epifania Sánchez Hernández','Maestria','Activo',3);
INSERT INTO `profesores` (`idProfesor`,`curp`,`nombre`,`grado`,`estatus`,`licenciatura`) VALUES (14,'VEMB931105MOCR118','M.A. Verónica Marilú Brena Ramos','Maestria','Activo',3);
INSERT INTO `profesores` (`idProfesor`,`curp`,`nombre`,`grado`,`estatus`,`licenciatura`) VALUES (15,'YOCJ931105MOCR118','L.E. Yolanda Canseco Jiméne','Licenciatura','Activo',3);
INSERT INTO `profesores` (`idProfesor`,`curp`,`nombre`,`grado`,`estatus`,`licenciatura`) VALUES (16,'VEGB931105MOCR118','L.E. Verónica García Brena','Licenciatura','Activo',4);
INSERT INTO `profesores` (`idProfesor`,`curp`,`nombre`,`grado`,`estatus`,`licenciatura`) VALUES (17,'RONA931105MOCR118','Dra. Roxana Nayeli Guerrero Sotelo','Doctorado','Activo',4);
INSERT INTO `profesores` (`idProfesor`,`curp`,`nombre`,`grado`,`estatus`,`licenciatura`) VALUES (18,'COMA931105MOCR118','M.C.E. Concepción Maritza  Jarquín Olivera','Maestria','Activo',4);
INSERT INTO `profesores` (`idProfesor`,`curp`,`nombre`,`grado`,`estatus`,`licenciatura`) VALUES (19,'LUMI931105MOCR118','M.C. Luis Miguel Márquez Váldez','Maestria','Activo',4);
INSERT INTO `profesores` (`idProfesor`,`curp`,`nombre`,`grado`,`estatus`,`licenciatura`) VALUES (20,'ERBM931105MOCR118','M.C. Ericay Berenice Martinez Ramos','Maestria','Activo',4);
INSERT INTO `profesores` (`idProfesor`,`curp`,`nombre`,`grado`,`estatus`,`licenciatura`) VALUES (21,'ABMS931105MOCR118','Dr. Abisaí Martínez Sánchez ','Doctorado','Activo',5);
INSERT INTO `profesores` (`idProfesor`,`curp`,`nombre`,`grado`,`estatus`,`licenciatura`) VALUES (22,'LUAM931105MOCR118','Méd. Ludivina Antonia Morales Quiroz','Maestria','Activo',5);
INSERT INTO `profesores` (`idProfesor`,`curp`,`nombre`,`grado`,`estatus`,`licenciatura`) VALUES (23,'EMOR931105MOCR118','Dra. Elizabeth Muñoz Ortiz','Doctorado','Activo',5);
INSERT INTO `profesores` (`idProfesor`,`curp`,`nombre`,`grado`,`estatus`,`licenciatura`) VALUES (24,'DOCR931105MOCR118','M.S.N. Douglas Crittenden Nance','Maestria','Activo',5);
INSERT INTO `profesores` (`idProfesor`,`curp`,`nombre`,`grado`,`estatus`,`licenciatura`) VALUES (25,'FRRA931105MOCR118','M.A. Francisco Ramiro Ordaz Zurita','Maestria','Activo',5);
INSERT INTO `profesores` (`idProfesor`,`curp`,`nombre`,`grado`,`estatus`,`licenciatura`) VALUES (26,'JOGR931105MOCR118','Méd. José Guadalupe Reyes Ramírez','Maestria','Activo',6);
INSERT INTO `profesores` (`idProfesor`,`curp`,`nombre`,`grado`,`estatus`,`licenciatura`) VALUES (27,'ACRS931105MOCR118','L.E. Ana Carla Reyes Soriano','Licenciatura','Activo',6);
INSERT INTO `profesores` (`idProfesor`,`curp`,`nombre`,`grado`,`estatus`,`licenciatura`) VALUES (28,'SASH931105MOCR118','M.C. Samuel Sánchez Hernández','Maestria','Activo',6);
INSERT INTO `profesores` (`idProfesor`,`curp`,`nombre`,`grado`,`estatus`,`licenciatura`) VALUES (29,'ROPS931105MOCR118','L.E.E.P. Rossell Perla Stockett Hernández ','Licenciatura','Activo',6);
INSERT INTO `profesores` (`idProfesor`,`curp`,`nombre`,`grado`,`estatus`,`licenciatura`) VALUES (30,'AZUJI31105MOCR118','L.E. Adriana Zúñiga Jiménez','Licenciatura','Activo',6);
INSERT INTO `profesores` (`idProfesor`,`curp`,`nombre`,`grado`,`estatus`,`licenciatura`) VALUES (31,'HODUI31105MOCR118','M.C. Horacio Duque Bautista','Maestria','Activo',2);

-- tutores

INSERT INTO `tutores` (`idTutorado`,`matricula`,`curp`,`idPeriodo`,`tipo`) VALUES (1,'2012060149','RIRA651211MOCR007',1,1);
INSERT INTO `tutores` (`idTutorado`,`matricula`,`curp`,`idPeriodo`,`tipo`) VALUES (2,'2012060150','RIRA651211MOCR007',1,1);
INSERT INTO `tutores` (`idTutorado`,`matricula`,`curp`,`idPeriodo`,`tipo`) VALUES (3,'2012060148','RASA931105MOCR008',1,2);
INSERT INTO `tutores` (`idTutorado`,`matricula`,`curp`,`idPeriodo`,`tipo`) VALUES (4,'2012060151','RIRA651211MOCR007',1,1);
INSERT INTO `tutores` (`idTutorado`,`matricula`,`curp`,`idPeriodo`,`tipo`) VALUES (5,'2012060162','RIRA651211MOCR007',1,1);
INSERT INTO `tutores` (`idTutorado`,`matricula`,`curp`,`idPeriodo`,`tipo`) VALUES (6,'2012060163','RIRA651211MOCR007',1,1);
INSERT INTO `tutores` (`idTutorado`,`matricula`,`curp`,`idPeriodo`,`tipo`) VALUES (7,'2012060164','RIRA651211MOCR007',1,1);
INSERT INTO `tutores` (`idTutorado`,`matricula`,`curp`,`idPeriodo`,`tipo`) VALUES (8,'2012060165','RIRA651211MOCR007',1,1);
INSERT INTO `tutores` (`idTutorado`,`matricula`,`curp`,`idPeriodo`,`tipo`) VALUES (9,'2012060166','RIRA651211MOCR007',1,1);
INSERT INTO `tutores` (`idTutorado`,`matricula`,`curp`,`idPeriodo`,`tipo`) VALUES (10,'2012060167','RIRA651211MOCR007',1,1);
INSERT INTO `tutores` (`idTutorado`,`matricula`,`curp`,`idPeriodo`,`tipo`) VALUES (11,'2012060168','RIRA651211MOCR007',1,1);
INSERT INTO `tutores` (`idTutorado`,`matricula`,`curp`,`idPeriodo`,`tipo`) VALUES (12,'2012060178','RIRA651211MOCR007',1,1);
INSERT INTO `tutores` (`idTutorado`,`matricula`,`curp`,`idPeriodo`,`tipo`) VALUES (13,'2012060180','RIRA651211MOCR007',1,1);
INSERT INTO `tutores` (`idTutorado`,`matricula`,`curp`,`idPeriodo`,`tipo`) VALUES (14,'2012060181','RASA931105MOCR008',1,2);
INSERT INTO `tutores` (`idTutorado`,`matricula`,`curp`,`idPeriodo`,`tipo`) VALUES (15,'2012060182','RASA931105MOCR008',1,2);
INSERT INTO `tutores` (`idTutorado`,`matricula`,`curp`,`idPeriodo`,`tipo`) VALUES (16,'2012060183','RASA931105MOCR008',1,2);
INSERT INTO `tutores` (`idTutorado`,`matricula`,`curp`,`idPeriodo`,`tipo`) VALUES (17,'2012060184','RASA931105MOCR008',1,2);
INSERT INTO `tutores` (`idTutorado`,`matricula`,`curp`,`idPeriodo`,`tipo`) VALUES (18,'2012060200','RASA931105MOCR008',1,2);
INSERT INTO `tutores` (`idTutorado`,`matricula`,`curp`,`idPeriodo`,`tipo`) VALUES (19,'2012060201','RASA931105MOCR008',1,2);
INSERT INTO `tutores` (`idTutorado`,`matricula`,`curp`,`idPeriodo`,`tipo`) VALUES (20,'2012060202','RASA931105MOCR008',1,2);

-- usuarios

INSERT INTO `usuarios` (`user`,`pass`,`nivel`,`curp`) VALUES ('mary','mary',1,'dd');