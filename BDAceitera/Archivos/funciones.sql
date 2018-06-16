delimiter //
DROP FUNCTION IF EXISTS recuperarUsuario //
DROP FUNCTION IF EXISTS verificarContrasenia //
CREATE FUNCTION recuperarUsuario(tel varchar(12), Ncontrasenia varchar(150)) RETURNS BOOLEAN
BEGIN
    DECLARE conteo INT UNSIGNED DEFAULT 0;
    select count(id) into conteo from usuario where noTelefono=tel;
    if(conteo=1)then
		select id into conteo from usuario where noTelefono=tel;
        update usuario set contrasenia=Ncontrasenia where id=conteo;
        select count(id) into conteo from usuario where Ncontrasenia=contrasenia;
        return conteo=1;
	else
        return 0;
    end if;
END; //

CREATE FUNCTION verificarContrasenia(vNombre VARCHAR(25), vApellido VARCHAR(25), vNoTelefono VARCHAR(12), 
		vNcontrasenia VARCHAR(150), vActivo BOOLEAN, vTipoUsuarioId INT, idUsuario INT) RETURNS BOOLEAN
BEGIN
	IF(vNcontrasenia = "") THEN
		UPDATE usuario SET nombre=vNombre, apellido=vApellido, noTelefono=vNoTelefono, activo=vActivo,
			TipoUsuario_id = vTipoUsuarioId WHERE id = idUsuario;
		return 1;
	ELSE
		UPDATE usuario SET nombre=vNombre, apellido=vApellido, noTelefono=vNoTelefono, contrasenia=vNcontrasenia,
			activo=vActivo, TipoUsuario_id = vTipoUsuarioId WHERE id = idUsuario;
		return 1;
	END IF;
END; //
delimiter ;

