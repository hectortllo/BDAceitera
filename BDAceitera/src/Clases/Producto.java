/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class Producto {
    
    private final Connection con;
    private final Conexion conexion;

    public Producto() {
        conexion = new Conexion();
        con = conexion.getConnection();
    }
    
    public boolean insertarProducto(String nombreProducto, int cantidad, float costo, float precio,
            String descripcion, String TipoProducto, int idProveedor)
    {
        try {
            CallableStatement procedimiento = con.prepareCall("{call InsertarProducto(?,?,?,?,?,?,?)}");
            procedimiento.setString(1, nombreProducto);
            procedimiento.setInt(2, cantidad);
            procedimiento.setFloat(3, costo);
            procedimiento.setFloat(4, precio);
            procedimiento.setString(5, descripcion);
            procedimiento.setString(6, TipoProducto);
            procedimiento.setInt(7, idProveedor);
        } catch (SQLException ex) {
            Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
}
