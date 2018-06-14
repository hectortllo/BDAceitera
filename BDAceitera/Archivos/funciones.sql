delimiter //
DROP FUNCTION IF EXISTS recuperarUsuario //
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
END;//
delimiter ;