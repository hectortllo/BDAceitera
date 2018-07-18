/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    public boolean insertarProducto(String codigo, int cantidad, float precio,
            int idTipoProducto, int proveedorId, int idMarca, int idPresentacion, String detallePresent, float costo) {
        try {
            CallableStatement procedimiento = con.prepareCall("{call InsertarProducto(?,?,?,?,?,?,?,?,?)}");
            procedimiento.setString(1, codigo);
            procedimiento.setInt(2, cantidad);
            procedimiento.setFloat(3, precio);
            procedimiento.setInt(4, idTipoProducto);
            procedimiento.setInt(5, proveedorId);
            procedimiento.setInt(6, idMarca);
            procedimiento.setInt(7, idPresentacion);
            procedimiento.setString(8, detallePresent);
            procedimiento.setFloat(9, costo);
            procedimiento.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean ActualizarProducto(int id, int cantidad, float precio, float costo) {
        try {
            CallableStatement procedimiento = con.prepareCall("{call ActualizarProducto(?,?,?,?)}");
            procedimiento.setInt(1, id);
            procedimiento.setInt(2, cantidad);
            procedimiento.setFloat(3, precio);
            procedimiento.setFloat(4, costo);
            procedimiento.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean repetirCodigoProducto(String codigo) {
        try {
            String sql = "SELECT repetirCodigoProducto(?);";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, codigo);
            ResultSet rs = pst.executeQuery();
            rs.next();
            System.out.println("codigo: " + codigo);
            System.out.println(rs.getBoolean(1));
            return rs.getBoolean(1);
        } catch (SQLException ex) {
            Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("codigo: " + codigo);
            return false;
        }
    }
}
