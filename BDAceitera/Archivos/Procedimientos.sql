delimiter //
DROP PROCEDURE IF EXISTS InsertarUsuario //
DROP PROCEDURE IF EXISTS InsertarProveedor //
DROP PROCEDURE IF EXISTS InsertarProducto //
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

CREATE PROCEDURE InsertarProducto(vCodigo VARCHAR(15), vCantidad INT, vPrecio FLOAT, vTipoProducto VARCHAR(45), 
	vProveedor VARCHAR(50), vMarca VARCHAR(75))
BEGIN
	DECLARE vIdTipoProducto INT UNSIGNED DEFAULT 0;
    DECLARE vIdProveedor INT UNSIGNED DEFAULT 0;
    DECLARE vIdMarca INT UNSIGNED DEFAULT 0;
    
    SELECT id  INTO vIdTipoProducto FROM tipoproducto WHERE tipoProducto = vTipoProducto;
    SELECT id INTO vIdProveedor FROM proveedor WHERE nombreEmpresa = vProveedor;
    SELECT id INTO vIdMarca FROM marca WHERE marca = vMarca;
    
	INSERT INTO producto(codigo, cantidad, precio, TipoProducto_id, Proveedor_id, Marca_id)
    VALUES(vCodigo, vCantidad, vPrecio, vIdTipoProducto, vIdProveedor, vIdMarca);
END; //
	
delimiter ;