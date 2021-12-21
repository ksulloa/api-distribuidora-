CREATE TABLE dueno(
  id serial,
  nombre VARCHAR(45) NOT NULL,
  apellido VARCHAR(45) NULL,
  cedula VARCHAR (45) NULL,
  telefono VARCHAR (45) NULL,
  PRIMARY KEY (id)
  );


CREATE TABLE distribuidora(
  id serial,
  nombre VARCHAR(45) NOT NULL,
  direccion VARCHAR(45) NULL,
  categoria VARCHAR (45) NULL,
  dueno_id INT NOT NULL,
  FOREIGN KEY (dueno_id) REFERENCES dueno(id),
  PRIMARY KEY (id)
  );

CREATE TABLE producto(
    id serial,
    nombre VARCHAR(45) NOT NULL,
    cantidad VARCHAR(45) NULL,
    precio VARCHAR(45) NULL,
    categoria VARCHAR (45) NULL,
    distribuidora_id INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (distribuidora_id) REFERENCES distribuidora(id)
    );