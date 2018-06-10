/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mynor
 */
public class Ventas {

    private final Connection con;
    private final Conexion conexion;

    public Ventas() {
        conexion = new Conexion();
        con = conexion.getConnection();
    }

    public boolean insertarVenta(int id) {
        try {
            String query = "INSERT INTO Ventas(total,fecha,Usuario_id) VALUES (?,?,?);";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setFloat(1, 0);
            pst.setString(2, "2018-01-01");
            pst.setInt(3, id);
            int n = pst.executeUpdate();
            return n != 0;
        } catch (SQLException ex) {
            Logger.getLogger(Compras.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean insertarDVentas(int idProducto, int cantidad, int idUs) {
        try {
            CallableStatement procedimiento = con.prepareCall("{call InsertarVenta(?,?,?)}");
            procedimiento.setInt(1, idProducto);
            procedimiento.setInt(2, cantidad);
            procedimiento.setInt(3, idUs);
            procedimiento.execute();
        } catch (SQLException ex) {
            Logger.getLogger(Proveedor.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
}
