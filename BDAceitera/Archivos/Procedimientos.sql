delimiter //
DROP PROCEDURE IF EXISTS InsertarUsuario //
DROP PROCEDURE IF EXISTS InsertarProveedor //
DROP PROCEDURE IF EXISTS InsertarProducto //
CREATE PROCEDURE InsertarUsuario(vNombre VARCHAR(25), vApellido VARCHAR(25), vUsuario VARCHAR(12), vPassword VARCHAR(150), 
	vActivo BOOLEAN, vIdTipoUsuario INT)
    
BEGIN
	INSERT INTO usuario(nombre, apellido, usuario, contrasenia, activo, TipoUsuario_id)
    VALUES(vNombre, vApellido, vUsuario, vPassword, vActivo, vIdTipoUsuario);
END; //

CREATE PROCEDURE InsertarProveedor(vNombreProveedor VARCHAR(35), vNit VARCHAR(10))
BEGIN
	INSERT INTO proveedor(nombreProveedor, nit) VALUES(vNombreProveedor, vNit);
END; //

CREATE PROCEDURE InsertarProducto(vNombreProducto VARCHAR(45), vCantidad INT, vCosto FLOAT, vPrecio FLOAT, 
	vDescripcion VARCHAR(150), vTipoProducto_id VARCHAR(45), vProveedor VARCHAR(35))
BEGIN
	DECLARE vTipoProducto INT UNSIGNED DEFAULT 0;
    DECLARE vIdProveedor INT UNSIGNED DEFAULT 0;
    
    SELECT id  INTO vTipoProducto FROM tipoproducto WHERE tipoProducto = vTipoProducto_id;
    SELECT id INTO vIdProveedor FROM proveedor WHERE nombreProveedor = vProveedor;
	INSERT INTO producto(nombreProducto, cantidad, costo, precio, Descripcion, TipoProducto_id, Proveedor_id)
    VALUES(vNombreProducto, vCantidad, vCosto, vPrecio, vDescripcion, vTipoProducto, vProveedor_id);
END; //
	
delimiter ;