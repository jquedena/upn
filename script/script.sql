DROP SCHEMA IF EXISTS vasco_encuesta;

CREATE SCHEMA vasco_encuesta;

USE vasco_encuesta;

CREATE TABLE inquilino (
  id_inquilino int(11) NOT NULL AUTO_INCREMENT,
  nombre varchar(500) DEFAULT NULL,
  ruc varchar(45) DEFAULT NULL,
  direccion varchar(45) DEFAULT NULL,
  logo varchar(45) DEFAULT NULL,
  tema varchar(45) DEFAULT NULL,
  PRIMARY KEY (id_inquilino),
  UNIQUE KEY ruc_UNIQUE (ruc)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE encuesta (
  id_encuesta int(11) NOT NULL AUTO_INCREMENT,
  nombre varchar(100) DEFAULT NULL,
  fecha_inicio date DEFAULT NULL,
  fecha_fin date DEFAULT NULL,
  duracion int(11) DEFAULT NULL,
  clave varchar(45) DEFAULT NULL,
  id_inquilino int(11) NOT NULL,
  PRIMARY KEY (id_encuesta),
  KEY fk_encuesta_inquilino_idx (id_inquilino),
  CONSTRAINT fk_encuesta_inquilino FOREIGN KEY (id_inquilino) REFERENCES inquilino (id_inquilino) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE tipo_control (
  id_tipo_control int(11) NOT NULL AUTO_INCREMENT,
  descripcion varchar(45) DEFAULT NULL,
  PRIMARY KEY (id_tipo_control)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE persona (
  id_persona int(11) NOT NULL AUTO_INCREMENT,
  nombre varchar(200) DEFAULT NULL,
  apellido_paterno varchar(200) DEFAULT NULL,
  apellido_materno varchar(200) DEFAULT NULL,
  direccion varchar(500) DEFAULT NULL,
  telefono varchar(20) DEFAULT NULL,
  PRIMARY KEY (id_persona)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE encuesta_respuesta (
  id_encuesta_resultado int(11) NOT NULL AUTO_INCREMENT,
  fecha_registro date DEFAULT NULL,
  id_encuesta int(11) NOT NULL,
  id_persona int(11) NOT NULL,
  PRIMARY KEY (id_encuesta_resultado),
  KEY fk_encuesta_resultado_encuesta1_idx (id_encuesta),
  KEY fk_encuesta_resultado_persona1_idx (id_persona),
  CONSTRAINT fk_encuesta_resultado_encuesta1 FOREIGN KEY (id_encuesta) REFERENCES encuesta (id_encuesta) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT fk_encuesta_resultado_persona1 FOREIGN KEY (id_persona) REFERENCES persona (id_persona) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE pregunta (
  id_pregunta int(11) NOT NULL AUTO_INCREMENT,
  descripcion varchar(2000) DEFAULT NULL,
  id_encuesta int(11) NOT NULL,
  id_tipo_control int(11) NOT NULL,
  valores blob,
  PRIMARY KEY (id_pregunta),
  KEY fk_pregunta_encuesta1_idx (id_encuesta),
  KEY fk_pregunta_tipo_control1_idx (id_tipo_control),
  CONSTRAINT fk_pregunta_encuesta1 FOREIGN KEY (id_encuesta) REFERENCES encuesta (id_encuesta) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT fk_pregunta_tipo_control1 FOREIGN KEY (id_tipo_control) REFERENCES tipo_control (id_tipo_control) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE pregunta_respuesta (
  id_pregunta_respuesta int(11) NOT NULL AUTO_INCREMENT,
  respuesta varchar(50) DEFAULT NULL,
  id_encuesta_resultado int(11) NOT NULL,
  id_pregunta int(11) NOT NULL,
  PRIMARY KEY (id_pregunta_respuesta),
  KEY fk_pregunta_respuesta_encuesta_resultado1_idx (id_encuesta_resultado),
  KEY fk_pregunta_respuesta_pregunta1_idx (id_pregunta),
  CONSTRAINT fk_pregunta_respuesta_encuesta_resultado1 FOREIGN KEY (id_encuesta_resultado) REFERENCES encuesta_respuesta (id_encuesta_resultado) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT fk_pregunta_respuesta_pregunta1 FOREIGN KEY (id_pregunta) REFERENCES pregunta (id_pregunta) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
