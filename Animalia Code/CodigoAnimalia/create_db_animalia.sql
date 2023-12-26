-- Intentar eliminar la base de datos si existe
DROP DATABASE IF EXISTS animaliadb;

-- Crear la base de datos
CREATE DATABASE animaliadb;

-- Usar la base de datos recién creada
USE animaliadb;

-- Tabla Animal
CREATE TABLE Animal (
    id INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL,
    id_especie INT,
    tipo INT,
    activo BOOLEAN DEFAULT true,
    PRIMARY KEY (id)
);

-- Tabla AnimalAcuatico 
CREATE TABLE AnimalAcuatico (
    id INT NOT NULL AUTO_INCREMENT,
    tipoAgua VARCHAR(50),
    temperatura INT,
    PRIMARY KEY (id),
    FOREIGN KEY (id) REFERENCES Animal(id) ON UPDATE CASCADE ON DELETE CASCADE
);

-- Tabla AnimalNoAcuatico 
CREATE TABLE AnimalNoAcuatico (
    id INT NOT NULL AUTO_INCREMENT,
    numPatas INT,
    PRIMARY KEY (id),
    FOREIGN KEY (id) REFERENCES Animal(id) ON UPDATE CASCADE ON DELETE CASCADE
);

-- Tabla Empleado
CREATE TABLE Empleado (
    id INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL,
    dni VARCHAR(15),
    sueldoBase DOUBLE,
    telefono INT,
    activo BOOLEAN DEFAULT true,
    tipo INT,
    PRIMARY KEY (id)
);

-- Tabla EmpleadoLimpieza 
CREATE TABLE EmpleadoLimpieza (
    id INT NOT NULL AUTO_INCREMENT,
    suplemento DOUBLE,
    zona VARCHAR(50),
    PRIMARY KEY (id),
    FOREIGN KEY (id) REFERENCES Empleado(id) ON UPDATE CASCADE ON DELETE CASCADE
);

-- Tabla EmpleadoZoologico 
CREATE TABLE EmpleadoZoologico (
    id INT NOT NULL AUTO_INCREMENT,
    especialidad VARCHAR(50),
    tasa DOUBLE,
    experiencia INT,
    PRIMARY KEY (id),
    FOREIGN KEY (id) REFERENCES Empleado(id) ON UPDATE CASCADE ON DELETE CASCADE
);

-- Tabla Especie
CREATE TABLE Especie (
    id INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL,
    id_habitat INT,
    activo BOOLEAN DEFAULT true,
    PRIMARY KEY (id)
);

-- Tabla Habitat
CREATE TABLE Habitat (
    id INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL,
    tamano INT,
    activo BOOLEAN DEFAULT true,
    PRIMARY KEY (id)
);

-- Tabla Factura
CREATE TABLE Factura (
    id INT NOT NULL AUTO_INCREMENT,
    precio_total DOUBLE,
    fecha_compra DATE,
    activo BOOLEAN DEFAULT true,
    PRIMARY KEY (id)
);

-- Tabla Pase
CREATE TABLE Pase (
    id INT NOT NULL AUTO_INCREMENT,
    fecha DATE,
    hora TIME,
    precio DOUBLE,
    cantidad_disponible INT,
    id_habitat INT,
    activo BOOLEAN DEFAULT true,
    PRIMARY KEY (id)
);

-- Tabla Trabajo
CREATE TABLE Trabajo (
    id_habitat INT,
    id_empleado INT,
    PRIMARY KEY (id_habitat, id_empleado),
    FOREIGN KEY (id_habitat) REFERENCES Habitat(id) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (id_empleado) REFERENCES Empleado(id) ON UPDATE CASCADE ON DELETE CASCADE
);

-- Tabla LineaFactura
CREATE TABLE LineaFactura (
    id_factura INT,
    id_pase INT,
    cantidad INT,
    precio DOUBLE,
    PRIMARY KEY (id_factura, id_pase),
    FOREIGN KEY (id_factura) REFERENCES Factura(id) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (id_pase) REFERENCES Pase(id) ON UPDATE CASCADE ON DELETE CASCADE
);
