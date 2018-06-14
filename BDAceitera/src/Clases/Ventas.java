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
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

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
            return pst.executeUpdate() != 0;
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

    public DefaultTableModel getVentas(String id, String Fecha, JTable tabla) {
        try {
            String titulos[] = new String[4];
            for (byte i = 0; i < titulos.length; i++) {
                titulos[i] = tabla.getColumnName(i);
            }
            String sql = "SELECT \n"
                    + "    v.id AS num, \n"
                    + "    us.nombre, \n"
                    + "    us.apellido, \n"
                    + "    v.fecha, \n"
                    + "    v.total\n"
                    + "FROM\n"
                    + "    ventas v\n"
                    + "        INNER JOIN\n"
                    + "    usuario us ON us.id = v.usuario_id\n"
                    + "    where v.id LIKE'%" + id + "%' and v.fecha like'%" + Fecha + "%';";
            DefaultTableModel modelo = new DefaultTableModel(null, titulos);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            String registros[] = new String[4];
            while (rs.next()) {
                registros[0] = rs.getString("num");
                registros[1] = rs.getString("nombre") + " " + rs.getString("apellido");
                registros[2] = rs.getString("fecha");
                registros[3] = rs.getString("total");
                modelo.addRow(registros);
            }
            return modelo;
        } catch (SQLException ex) {
            Logger.getLogger(Inventario.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public DefaultTableModel getDVentas(int id, JTable tabla) {
        try {
            String titulos[] = new String[4];
            for (byte i = 0; i < titulos.length; i++) {
                titulos[i] = tabla.getColumnName(i);
            }
            String sql = "SELECT \n"
                    + "    dv.cantidad, dv.precio, p.codigo\n"
                    + "FROM\n"
                    + "    detalleventas dv\n"
                    + "        INNER JOIN\n"
                    + "    producto p ON p.id = dv.Producto_id\n"
                    + "    where dv.ventas_id =" + id + ";";
            DefaultTableModel modelo = new DefaultTableModel(null, titulos);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            String registros[] = new String[4];
            while (rs.next()) {
                registros[0] = rs.getString("codigo");
                registros[1] = rs.getString("cantidad");
                registros[2] = rs.getString("precio");
                registros[3] = "" + (Float.parseFloat(registros[2]) * Integer.parseInt(registros[1]));
                modelo.addRow(registros);
            }
            return modelo;
        } catch (SQLException ex) {
            Logger.getLogger(Inventario.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
