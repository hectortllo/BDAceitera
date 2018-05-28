delimiter //
DROP PROCEDURE IF EXISTS InsertarUsuario //
DROP PROCEDURE IF EXISTS InsertarProveedor //
DROP PROCEDURE IF EXISTS InsertarProducto //
CREATE PROCEDURE InsertarUsuario(vNombre VARCHAR(25), vApellido VARCHAR(25), vUsuario VARCHAR(12), vPassword VARCHAR(15), 
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
	vDescripcion VARCHAR(150), vTipoProducto_id INT, vProveedor_id INT)
BEGIN
	INSERT INTO producto(nombreProducto, cantidad, costo, precio, Descripcion, TipoProducto_id, Proveedor_id)
    VALUES(vNombreProducto, vCantidad, vCosto, vPrecio, vDescripcion, vTipoProducto_id, vProveedor_id);
END; //
	
delimiter ;