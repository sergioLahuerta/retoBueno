<<<<<<< HEAD
DROP SCHEMA IF EXISTS esquema_reto;
CREATE SCHEMA esquema_reto;
USE esquema_reto;

/*DROP TABLES*/
DROP TABLE IF EXISTS 
    Historial_Puntos,
    Puntos,
    Detalles_Facturas,
    Pagos,
    Facturas,
    Detalles_Pedidos,
    Pedidos,
    Resenas,
    Empleados,
    Proveedores_Ingredientes,
    Productos_Ingredientes,
    Productos,
    Ofertas,
    Usuarios,
    Ingredientes,
    Restaurante,
    Proveedores,
    Almacen,
    Categorias,
    Alertas_Stock;

/*CREATE TABLES*/
CREATE TABLE Categorias (
    ID_Categoria INT AUTO_INCREMENT PRIMARY KEY,
    Nombre VARCHAR(100) NOT NULL
);

CREATE TABLE Ofertas (
    ID_Oferta INT AUTO_INCREMENT PRIMARY KEY,
    Nombre VARCHAR(100) NOT NULL,
    Precio DECIMAL(10,2) NOT NULL,
    Descripcion TEXT,
    columnaImagen VARCHAR(255),
    fechaExpiracion DATE NOT NULL
);

CREATE TABLE Productos (
    ID_Producto INT AUTO_INCREMENT PRIMARY KEY,
    ID_Oferta INT,
    ID_Categoria INT NOT NULL,
    Nombre VARCHAR(100) NOT NULL,
    Precio DECIMAL(10,2) NOT NULL,
    Descripcion TEXT,
    FOREIGN KEY (ID_Oferta) REFERENCES Ofertas(ID_Oferta),
    FOREIGN KEY (ID_Categoria) REFERENCES Categorias(ID_Categoria)
);

CREATE TABLE Ingredientes (
    ID_Ingrediente INT AUTO_INCREMENT PRIMARY KEY,
    Nombre VARCHAR(100) NOT NULL,
    UnidadMedida VARCHAR(50),
    StockDisponible DECIMAL(10,2) NOT NULL,
    TipoAlmacenamiento VARCHAR(50),
    EstaActivo BOOLEAN NOT NULL
);

CREATE TABLE Productos_Ingredientes (
    ID_ProductoIngrediente INT AUTO_INCREMENT PRIMARY KEY,
    ID_Ingrediente INT NOT NULL,
    ID_Producto INT NOT NULL,
    Cantidad DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (ID_Ingrediente) REFERENCES Ingredientes(ID_Ingrediente),
    FOREIGN KEY (ID_Producto) REFERENCES Productos(ID_Producto)
);

CREATE TABLE Proveedores (
    ID_Proveedor INT AUTO_INCREMENT PRIMARY KEY,
    nombreEmpresa VARCHAR(100) NOT NULL,
    Telefono VARCHAR(100) NOT NULL,
    Email VARCHAR(100)
);

CREATE TABLE Almacen (
    ID_Almacen INT AUTO_INCREMENT PRIMARY KEY,
    Nombre VARCHAR(100) NOT NULL,
    Capacidad INT NOT NULL,
    Ubicacion VARCHAR(100)
);

CREATE TABLE Proveedores_Ingredientes (
    ID_ProveedorIngrediente INT AUTO_INCREMENT PRIMARY KEY,
    ID_Proveedor INT NOT NULL,
    ID_Ingrediente INT NOT NULL,
    precioUnitario DECIMAL(10,2) NOT NULL,
    tiempoEntregaDias INT,
    FOREIGN KEY (ID_Proveedor) REFERENCES Proveedores(ID_Proveedor),
    FOREIGN KEY (ID_Ingrediente) REFERENCES Ingredientes(ID_Ingrediente)
);

CREATE TABLE Restaurante (
    ID_Restaurante INT AUTO_INCREMENT PRIMARY KEY,
    Nombre VARCHAR(100) NOT NULL,
    Direccion VARCHAR(200),
    Telefono VARCHAR(20),
    Email VARCHAR(100),
    Aforo INT,
    imagenRestaurante VARCHAR(250)
);

CREATE TABLE Empleados (
    ID_Empleado INT AUTO_INCREMENT PRIMARY KEY,
    ID_Restaurante INT,
    Nombre VARCHAR(100) NOT NULL,
    Apellidos VARCHAR(100) NOT NULL,
    DNI VARCHAR(20) NOT NULL,
    Telefono VARCHAR(20),
    Sueldo DECIMAL(10,2) NOT NULL,
    FechaContratacion DATE NOT NULL,
    FOREIGN KEY (ID_Restaurante) REFERENCES Restaurante(ID_Restaurante)
);

CREATE TABLE Usuarios (
    ID_Usuario INT AUTO_INCREMENT PRIMARY KEY,
    Nombre VARCHAR(100),
    Email VARCHAR(100) NOT NULL UNIQUE,
    Contrasena VARCHAR(255) NOT NULL,
    DNI VARCHAR(20),
    Telefono VARCHAR(20),
    Direccion VARCHAR(200)
);

CREATE TABLE Resenas (
    ID_Resena INT AUTO_INCREMENT PRIMARY KEY,
    ID_Usuario INT NOT NULL,
    ID_Restaurante INT NOT NULL,
    Valoracion INT,
    Fecha DATE,
    FOREIGN KEY (ID_Usuario) REFERENCES Usuarios(ID_Usuario),
    FOREIGN KEY (ID_Restaurante) REFERENCES Restaurante(ID_Restaurante)
);

CREATE TABLE Facturas (
    ID_Factura INT AUTO_INCREMENT PRIMARY KEY,
    FechaFactura DATE NOT NULL,
    ImporteTotal DECIMAL(10,2) NOT NULL
);

CREATE TABLE Pedidos (
    ID_Pedido INT AUTO_INCREMENT PRIMARY KEY,
    ID_Factura INT NOT NULL,
    ID_Restaurante INT NOT NULL,
    ID_Usuario INT NOT NULL,
    Numero INT NOT NULL,
    FOREIGN KEY (ID_Factura) REFERENCES Facturas(ID_Factura),
    FOREIGN KEY (ID_Usuario) REFERENCES Usuarios(ID_Usuario),
    FOREIGN KEY (ID_Restaurante) REFERENCES Restaurante(ID_Restaurante)
);

CREATE TABLE Detalles_Pedidos (
    ID_DetallePedido INT AUTO_INCREMENT PRIMARY KEY,
    ID_Pedido INT NOT NULL,
    ID_Producto INT NOT NULL,
    Cantidad INT NOT NULL,
    Observaciones TEXT,
    FOREIGN KEY (ID_Pedido) REFERENCES Pedidos(ID_Pedido),
    FOREIGN KEY (ID_Producto) REFERENCES Productos(ID_Producto)
);

CREATE TABLE Detalles_Facturas (
    ID_DetalleFactura INT AUTO_INCREMENT PRIMARY KEY,
    ID_Detalle_Pedido INT NOT NULL,
    ID_Factura INT NOT NULL,
    PrecioUnitario DECIMAL(10,2) NOT NULL,
    TotalLinea DECIMAL(10,2),
    Descuento DECIMAL(10,2),
    FOREIGN KEY (ID_Detalle_Pedido) REFERENCES Detalles_Pedidos(ID_DetallePedido),
    FOREIGN KEY (ID_Factura) REFERENCES Facturas(ID_Factura)
);

CREATE TABLE Puntos (
    ID_Puntos INT AUTO_INCREMENT PRIMARY KEY,
    ID_Usuario INT NOT NULL,
    PuntosActuales INT NOT NULL,
    FOREIGN KEY (ID_Usuario) REFERENCES Usuarios(ID_Usuario)
);

CREATE TABLE Historial_Puntos (
    ID_HistorialPuntos INT AUTO_INCREMENT PRIMARY KEY,
    ID_Factura INT NOT NULL,
    Fecha DATE NOT NULL,
    Puntos INT NOT NULL,
    TipoMovimiento ENUM('GANADO', 'CANJEADO') NOT NULL,
    Descripcion TEXT,
    FOREIGN KEY (ID_Factura) REFERENCES Facturas(ID_Factura)
);

CREATE TABLE Pagos (
    ID_Pago INT AUTO_INCREMENT PRIMARY KEY,
    ID_Factura INT NOT NULL,
    metodoPago VARCHAR(50) NOT NULL,
    fechaPago DATE NOT NULL,
    estadoPago VARCHAR(20) NOT NULL,
    FOREIGN KEY (ID_Factura) REFERENCES Facturas(ID_Factura)
);

CREATE TABLE IF NOT EXISTS Alertas_Stock (
    ID_Alerta INT AUTO_INCREMENT PRIMARY KEY,
    ID_Ingrediente INT NOT NULL,
    FechaAlerta DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    StockDisponible DECIMAL(10,2),
    Mensaje TEXT,
    FOREIGN KEY (ID_Ingrediente) REFERENCES Ingredientes(ID_Ingrediente)
);

/* TABLA PARA ALERTAS DE STOCK BAJO */
DELIMITER $$

DROP PROCEDURE IF EXISTS RevisarStockBajo$$
CREATE PROCEDURE RevisarStockBajo()
BEGIN
    INSERT INTO Alertas_Stock (ID_Ingrediente, StockDisponible, Mensaje)
    SELECT 
        ID_Ingrediente,
        StockDisponible,
        CONCAT('Stock bajo: ', StockDisponible, ' unidades disponibles para ingrediente ID ', Nombre, '""')
    FROM Ingredientes
    WHERE StockDisponible < 10;
END$$

-- Restaurar delimitador normal
DELIMITER ;

/* LLAMADA AL PROCEDIMIENTO */
CALL RevisarStockBajo();
=======
DROP SCHEMA IF EXISTS esquema_reto;
CREATE SCHEMA esquema_reto;
USE esquema_reto;

/*DROP TABLES*/
DROP TABLE IF EXISTS 
    Historial_Puntos,
    Puntos,
    Detalles_Facturas,
    Pagos,
    Facturas,
    Detalles_Pedidos,
    Pedidos,
    Resenas,
    Empleados,
    Proveedores_Ingredientes,
    Productos_Ingredientes,
    Productos,
    Ofertas,
    Usuarios,
    Ingredientes,
    Restaurante,
    Proveedores,
    Almacen,
    Categorias,
    Alertas_Stock;

/*CREATE TABLES*/
CREATE TABLE Categorias (
    ID_Categoria INT AUTO_INCREMENT PRIMARY KEY,
    Nombre VARCHAR(100) NOT NULL
);

CREATE TABLE Ofertas (
    ID_Oferta INT AUTO_INCREMENT PRIMARY KEY,
    Nombre VARCHAR(100) NOT NULL,
    Precio DECIMAL(10,2) NOT NULL,
    Descripcion TEXT,
    columnaImagen VARCHAR(255),
    fechaExpiracion DATE NOT NULL
);

CREATE TABLE Productos (
    ID_Producto INT AUTO_INCREMENT PRIMARY KEY,
    ID_Oferta INT,
    ID_Categoria INT NOT NULL,
    Nombre VARCHAR(100) NOT NULL,
    Precio DECIMAL(10,2) NOT NULL,
    Descripcion TEXT,
    FOREIGN KEY (ID_Oferta) REFERENCES Ofertas(ID_Oferta),
    FOREIGN KEY (ID_Categoria) REFERENCES Categorias(ID_Categoria)
);

CREATE TABLE Ingredientes (
    ID_Ingrediente INT AUTO_INCREMENT PRIMARY KEY,
    Nombre VARCHAR(100) NOT NULL,
    UnidadMedida VARCHAR(50),
    StockDisponible DECIMAL(10,2) NOT NULL,
    TipoAlmacenamiento VARCHAR(50),
    EstaActivo BOOLEAN NOT NULL
);

CREATE TABLE Productos_Ingredientes (
    ID_ProductoIngrediente INT AUTO_INCREMENT PRIMARY KEY,
    ID_Ingrediente INT NOT NULL,
    ID_Producto INT NOT NULL,
    Cantidad DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (ID_Ingrediente) REFERENCES Ingredientes(ID_Ingrediente),
    FOREIGN KEY (ID_Producto) REFERENCES Productos(ID_Producto)
);

CREATE TABLE Proveedores (
    ID_Proveedor INT AUTO_INCREMENT PRIMARY KEY,
    nombreEmpresa VARCHAR(100) NOT NULL,
    Telefono VARCHAR(100) NOT NULL,
    Email VARCHAR(100)
);

CREATE TABLE Almacen (
    ID_Almacen INT AUTO_INCREMENT PRIMARY KEY,
    Nombre VARCHAR(100) NOT NULL,
    Capacidad INT NOT NULL,
    Ubicacion VARCHAR(100)
);

CREATE TABLE Proveedores_Ingredientes (
    ID_ProveedorIngrediente INT AUTO_INCREMENT PRIMARY KEY,
    ID_Proveedor INT NOT NULL,
    ID_Ingrediente INT NOT NULL,
    precioUnitario DECIMAL(10,2) NOT NULL,
    tiempoEntregaDias INT,
    FOREIGN KEY (ID_Proveedor) REFERENCES Proveedores(ID_Proveedor),
    FOREIGN KEY (ID_Ingrediente) REFERENCES Ingredientes(ID_Ingrediente)
);

CREATE TABLE Restaurante (
    ID_Restaurante INT AUTO_INCREMENT PRIMARY KEY,
    Nombre VARCHAR(100) NOT NULL,
    Direccion VARCHAR(200),
    Telefono VARCHAR(20),
    Email VARCHAR(100),
    Aforo INT,
    imagenRestaurante VARCHAR(250)
);

CREATE TABLE Empleados (
    ID_Empleado INT AUTO_INCREMENT PRIMARY KEY,
    ID_Restaurante INT,
    Nombre VARCHAR(100) NOT NULL,
    Apellidos VARCHAR(100) NOT NULL,
    DNI VARCHAR(20) NOT NULL,
    Telefono VARCHAR(20),
    Sueldo DECIMAL(10,2) NOT NULL,
    FechaContratacion DATE NOT NULL,
    FOREIGN KEY (ID_Restaurante) REFERENCES Restaurante(ID_Restaurante)
);

CREATE TABLE Usuarios (
    ID_Usuario INT AUTO_INCREMENT PRIMARY KEY,
    Nombre VARCHAR(100),
    Email VARCHAR(100) NOT NULL UNIQUE,
    Contrasena VARCHAR(255) NOT NULL,
    DNI VARCHAR(20),
    Telefono VARCHAR(20),
    Direccion VARCHAR(200)
);

CREATE TABLE Resenas (
    ID_Resena INT AUTO_INCREMENT PRIMARY KEY,
    ID_Usuario INT NOT NULL,
    ID_Restaurante INT NOT NULL,
    Valoracion INT,
    Fecha DATE,
    FOREIGN KEY (ID_Usuario) REFERENCES Usuarios(ID_Usuario),
    FOREIGN KEY (ID_Restaurante) REFERENCES Restaurante(ID_Restaurante)
);

CREATE TABLE Facturas (
    ID_Factura INT AUTO_INCREMENT PRIMARY KEY,
    FechaFactura DATE NOT NULL,
    ImporteTotal DECIMAL(10,2) NOT NULL
);

CREATE TABLE Pedidos (
    ID_Pedido INT AUTO_INCREMENT PRIMARY KEY,
    ID_Factura INT NOT NULL,
    ID_Restaurante INT NOT NULL,
    ID_Usuario INT NOT NULL,
    Numero INT NOT NULL,
    FOREIGN KEY (ID_Factura) REFERENCES Facturas(ID_Factura),
    FOREIGN KEY (ID_Usuario) REFERENCES Usuarios(ID_Usuario),
    FOREIGN KEY (ID_Restaurante) REFERENCES Restaurante(ID_Restaurante)
);

CREATE TABLE Detalles_Pedidos (
    ID_DetallePedido INT AUTO_INCREMENT PRIMARY KEY,
    ID_Pedido INT NOT NULL,
    ID_Producto INT NOT NULL,
    Cantidad INT NOT NULL,
    Observaciones TEXT,
    FOREIGN KEY (ID_Pedido) REFERENCES Pedidos(ID_Pedido),
    FOREIGN KEY (ID_Producto) REFERENCES Productos(ID_Producto)
);

CREATE TABLE Detalles_Facturas (
    ID_DetalleFactura INT AUTO_INCREMENT PRIMARY KEY,
    ID_Detalle_Pedido INT NOT NULL,
    ID_Factura INT NOT NULL,
    PrecioUnitario DECIMAL(10,2) NOT NULL,
    TotalLinea DECIMAL(10,2),
    Descuento DECIMAL(10,2),
    FOREIGN KEY (ID_Detalle_Pedido) REFERENCES Detalles_Pedidos(ID_DetallePedido),
    FOREIGN KEY (ID_Factura) REFERENCES Facturas(ID_Factura)
);

CREATE TABLE Puntos (
    ID_Puntos INT AUTO_INCREMENT PRIMARY KEY,
    ID_Usuario INT NOT NULL,
    PuntosActuales INT NOT NULL,
    FOREIGN KEY (ID_Usuario) REFERENCES Usuarios(ID_Usuario)
);

CREATE TABLE Historial_Puntos (
    ID_HistorialPuntos INT AUTO_INCREMENT PRIMARY KEY,
    ID_Factura INT NOT NULL,
    Fecha DATE NOT NULL,
    Puntos INT NOT NULL,
    TipoMovimiento ENUM('GANADO', 'CANJEADO') NOT NULL,
    Descripcion TEXT,
    FOREIGN KEY (ID_Factura) REFERENCES Facturas(ID_Factura)
);

CREATE TABLE Pagos (
    ID_Pago INT AUTO_INCREMENT PRIMARY KEY,
    ID_Factura INT NOT NULL,
    metodoPago VARCHAR(50) NOT NULL,
    fechaPago DATE NOT NULL,
    estadoPago VARCHAR(20) NOT NULL,
    FOREIGN KEY (ID_Factura) REFERENCES Facturas(ID_Factura)
);

CREATE TABLE IF NOT EXISTS Alertas_Stock (
    ID_Alerta INT AUTO_INCREMENT PRIMARY KEY,
    ID_Ingrediente INT NOT NULL,
    FechaAlerta DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    StockDisponible DECIMAL(10,2),
    Mensaje TEXT,
    FOREIGN KEY (ID_Ingrediente) REFERENCES Ingredientes(ID_Ingrediente)
);

/* TABLA PARA ALERTAS DE STOCK BAJO */
DELIMITER $$

DROP PROCEDURE IF EXISTS RevisarStockBajo$$
CREATE PROCEDURE RevisarStockBajo()
BEGIN
    INSERT INTO Alertas_Stock (ID_Ingrediente, StockDisponible, Mensaje)
    SELECT 
        ID_Ingrediente,
        StockDisponible,
        CONCAT('Stock bajo: ', StockDisponible, ' unidades disponibles para ingrediente ', Nombre, '"')
    FROM Ingredientes
    WHERE StockDisponible < 10;
END$$

-- Restaurar delimitador normal
DELIMITER ;

/* LLAMADA AL PROCEDIMIENTO */
CALL RevisarStockBajo();
>>>>>>> af3274bb771a8f087d2e1c337210faf1c4ec886c
