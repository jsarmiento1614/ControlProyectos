/*CREATE DATABASE bdproyectos*/
/*use bdproyectos;*/

use AdventureWorks;

GO

CREATE TABLE usuarios (
  IdUsers         smallInt NOT NULL,
  nombreCompleto  varchar(50) NOT NULL,
  nombreUsuario   varchar(25) NOT NULL,
  email           varchar(50) NOT NULL,
  passwords        varchar(25) NOT NULL,
  preguntaSeguridad varchar(25) NOT NULL,
  respuestaSeguridad varchar(25) NOT NULL,
  CONSTRAINT PK_Users PRIMARY KEY (IdUsers)
);


GO


CREATE TABLE proyectos (
  IdProyect       smallInt NOT NULL,
  IdUsers         smallInt NOT NULL,
  nombreProyecto  varchar(50) NOT NULL,
  jefe            varchar(25) NOT NULL,
  fechaInicio     date,
  fechaFin        date,
  descripcion     varchar(50),
  PRIMARY KEY (IdProyect),
  /* Foreign keys */
  CONSTRAINT FK_Proyect
    FOREIGN KEY (IdUsers)
    REFERENCES usuarios(IdUsers)
);

GO

CREATE TABLE tareas (
  IdTarea         smallInt NOT NULL,
  IdProyect       smallInt NOT NULL,
  nombreTarea     varchar(50) NOT NULL,
  actividadTarea  varchar(50) NOT NULL,
  encargado       varchar(50) NOT NULL,
  fechaInicio     date NOT NULL,
  fechaFin        date NOT NULL,
  estado          varchar(50) NOT NULL,
  PRIMARY KEY (IdTarea),
  /* Foreign keys */
  CONSTRAINT FK_IdProyect
    FOREIGN KEY (IdProyect)
    REFERENCES proyectos(IdProyect)
);

GO

CREATE TABLE encargados (
  IdEncargado  smallInt NOT NULL,
  IdTarea      smallInt NOT NULL,
  encargado    varchar(50),
  PRIMARY KEY (IdEncargado),
  /* Foreign keys */
  CONSTRAINT FK_Encargado
    FOREIGN KEY (IdTarea)
    REFERENCES tareas(IdTarea)
);

GO

CREATE TABLE docCompartidos (
  idDocument  smallInt NOT NULL,
  IdUsers     smallInt NOT NULL,
  correo      varchar(50),
  proyecto    varchar(50),
  PRIMARY KEY (idDocument),
  /* Foreign keys */
  CONSTRAINT FK_DocCompartidos
    FOREIGN KEY (IdUsers)
    REFERENCES usuarios(IdUsers)
);

GO

INSERT INTO usuarios (IdUsers, nombreCompleto, nombreUsuario, email, passwords, preguntaSeguridad, respuestaSeguridad)
VALUES (1, 'Administrador', 'Admin', 'admin@gmail.com', '1010', '¿Cual es tu color favorito', 'verde');

GO

INSERT INTO usuarios (IdUsers, nombreCompleto, nombreUsuario, email, passwords, preguntaSeguridad, respuestaSeguridad)
VALUES (2, 'Jesus', 'jsarmiento', 'jas@gmail.com', '1614', '¿Cual es tu color favorito', 'anaranjado');