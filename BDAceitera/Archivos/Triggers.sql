delimiter //
DROP TRIGGER IF EXISTS ventaPrecio //
CREATE TRIGGER ventaPrecio
BEFORE INSERT ON detalleventas
FOR EACH ROW
BEGIN
	declare var int unsigned default 0;
    select precio into var from producto where id=new.Producto_id;
    set new.precio=var; 
    update ventas set total=total+(var*new.cantidad), fecha=now() where id = new.ventas_id;
END;//
delimiter ;