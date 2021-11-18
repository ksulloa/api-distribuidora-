CREATE TABLE distribuidora(
  id serial,
  nombre VARCHAR(45) NOT NULL,
  direccion VARCHAR(45) NULL,
  PRIMARY KEY (id)
  );

CREATE TABLE dueño(
  id serial,
  nombre VARCHAR(45) NOT NULL,
  apellido VARCHAR(45) NULL,
  distribuidora_id INT NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (distribuidora_id) REFERENCES distribuidora(id)
  );

CREATE TABLE producto(
    id serial,
    nombre VARCHAR(45) NOT NULL,
    cantidad VARCHAR(45) NULL,
    precio VARCHAR(45) NULL,
    dueño_id INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (dueño_id) REFERENCES dueño(id)
    );