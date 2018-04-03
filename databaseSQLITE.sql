CREATE TABLE usuarios (
  IdUsers         integer NOT NULL PRIMARY KEY,
  nombreCompleto  varchar(50) NOT NULL,
  nombreUsuario   varchar(25) NOT NULL,
  email           varchar(50) NOT NULL,
  password        varchar(25) NOT NULL,
  pregunstaSeguridad varchar(25) NOT NULL,
  respuestaSeguridad varchar(25) NOT NULL
);




CREATE TABLE proyectos (
  IdProyect       integer NOT NULL PRIMARY KEY,
  IdUsers         integer,
  nombreProyecto  varchar(50) NOT NULL,
  jefe            varchar(25) NOT NULL,
  fechaInicio     date,
  fechaFin        date,
  descripcion     varchar(50),
  /* Foreign keys */
  CONSTRAINT FK_Proyect
    FOREIGN KEY (IdUsers)
    REFERENCES usuarios(IdUsers)
);




CREATE TABLE tareas (
  IdTarea         integer NOT NULL PRIMARY KEY,
  IdProyect       integer,
  nombreTarea     varchar(50),
  actividadTarea  varchar(50),
  encargado       varchar(50),
  fechaInicio     date,
  fechaFin        date,
  estado          varchar(50),
  /* Foreign keys */
  CONSTRAINT IdProyect_f
    FOREIGN KEY (IdProyect)
    REFERENCES proyectos(IdProyect)
);







CREATE TABLE encargados (
  IdEncargado  integer NOT NULL PRIMARY KEY,
  IdTarea      integer,
  encargado    varchar(50),
  /* Foreign keys */
  CONSTRAINT FK_Encargado
    FOREIGN KEY (IdTarea)
    REFERENCES tareas(IdTarea)
);



CREATE TABLE docCompartidos (
  idDocument  integer NOT NULL PRIMARY KEY,
  IdUsers     integer,
  correo      varchar(50),
  proyecto    varchar(50),
  /* Foreign keys */
  CONSTRAINT FK_DocCompartidos
    FOREIGN KEY (IdUsers)
    REFERENCES usuarios(IdUsers)
);