package Clases;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hectortllo
 */
public class Proveedor {

    private final Connection con;
    private final Conexion conexion;

    public Proveedor() {
        conexion = new Conexion();
        con = conexion.getConnection();
    }
    
    public boolean insertarProveedor(String nombreEmpresa, String nombreDistribuidor,
            String telefonoEmpresa, String telefonoDistribuidor)
    {
        try {
            CallableStatement procedimiento = con.prepareCall("{call InsertarProveedor(?,?,?,?)}");
            procedimiento.setString(1, nombreEmpresa);
            procedimiento.setString(2, nombreDistribuidor);
            procedimiento.setString(3, telefonoEmpresa);
            procedimiento.setString(4, telefonoDistribuidor);
            procedimiento.execute();
        } catch (SQLException ex) {
            Logger.getLogger(Proveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
}