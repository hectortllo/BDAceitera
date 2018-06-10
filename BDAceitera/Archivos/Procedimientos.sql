delimiter //
DROP PROCEDURE IF EXISTS InsertarUsuario //
DROP PROCEDURE IF EXISTS InsertarProveedor //
DROP PROCEDURE IF EXISTS InsertarProducto //
DROP PROCEDURE IF EXISTS InsertarVenta //
CREATE PROCEDURE InsertarUsuario(vNombre VARCHAR(25), vApellido VARCHAR(25), vtelefono VARCHAR(12), vPassword VARCHAR(150), 
	vActivo BOOLEAN, vIdTipoUsuario INT)
    
BEGIN
	INSERT INTO usuario(nombre, apellido, noTelefono, contrasenia, activo, TipoUsuario_id)
    VALUES(vNombre, vApellido, vtelefono, vPassword, vActivo, vIdTipoUsuario);
END; //

CREATE PROCEDURE InsertarProveedor(vNombreEmpresa VARCHAR(50), vNombreDistribuidor VARCHAR(50), vTelefonoEmpresa VARCHAR(15),
	vTelefonoDistribuidor VARCHAR(15))
BEGIN
	INSERT INTO proveedor(nombreEmpresa, nombreDistribuidor, noTelEmpresa, noTelDistribuidor)
    VALUES(vNombreEmpresa, vNombreDistribuidor, vTelefonoEmpresa, vTelefonoDistribuidor);
END; //

CREATE PROCEDURE InsertarProducto(vCodigo VARCHAR(15), vCantidad INT, vPrecio FLOAT, vTipoProductoId INT, vProveedorId INT,
		vMarcaId INT, vPresentacionId INT, vDetallePresent VARCHAR(45), vCosto FLOAT)
BEGIN
	DECLARE vIdProducto INT UNSIGNED DEFAULT 0;
	DECLARE vIdCompra INT UNSIGNED DEFAULT 0;
    
    INSERT INTO producto(codigo, cantidad, precio, TipoProducto_id, Proveedor_id, Marca_id)
    VALUES(vCodigo, vCantidad, vPrecio, vTipoProductoId, vProveedorId, vMarcaId);
    
    SELECT MAX(id) INTO vIdProducto FROM producto;
    INSERT INTO detallepresentacion(Presentacion_id, Producto_id, detalle_presentacion)
    VALUES(vPresentacionId, vIdProducto, vDetallePresent);
    
    SELECT MAX(id) INTO vIdCompra FROM compras;
    INSERT INTO detallecompra(Producto_id, compras_id, cantidad, costo) 
    VALUES(vIdProducto, vIdCompra, vCantidad, vCosto);
END; //

CREATE PROCEDURE InsertarVenta(vIdProducto INT, vCantidad INT,vIdUs INT)
BEGIN
	DECLARE vIdVenta INT UNSIGNED DEFAULT 0;
    
    select max(id) into vIdVenta from ventas;
    
    insert into detalleventas(ventas_id,Producto_id,cantidad,precio)
    values(vIdVenta,vIdProducto,vCantidad,0);
    
    update producto set cantidad=cantidad-vCantidad where id=vIdProducto;
END; //

delimiter ;
