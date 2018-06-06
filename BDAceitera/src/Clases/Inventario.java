/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mynor
 */
public class Inventario {

    private final Connection con;
    private final Conexion conexion;

    public Inventario() {
        conexion = new Conexion();
        con = conexion.getConnection();
    }

    public DefaultComboBoxModel getMarca(DefaultComboBoxModel modelo) {
        try {
            String sql = "SELECT marca FROM marca;";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                modelo.addElement(rs.getString("marca"));
            }
            return modelo;
        } catch (SQLException ex) {
            Logger.getLogger(Inventario.class.getName()).log(Level.SEVERE, null, ex);
            return modelo;
        }
    }

    public DefaultComboBoxModel getPresentacion(DefaultComboBoxModel modelo) {
        try {
            String sql = "SELECT presentacion FROM presentacion;";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                modelo.addElement(rs.getString("presentacion"));
            }
            return modelo;
        } catch (SQLException ex) {
            Logger.getLogger(Inventario.class.getName()).log(Level.SEVERE, null, ex);
            return modelo;
        }
    }

    public DefaultComboBoxModel getTProd(DefaultComboBoxModel modelo) {
        try {
            String sql = "SELECT tipoProducto FROM tipoproducto;";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                modelo.addElement(rs.getString("tipoProducto"));
            }
            return modelo;
        } catch (SQLException ex) {
            Logger.getLogger(Inventario.class.getName()).log(Level.SEVERE, null, ex);
            return modelo;
        }
    }

    public DefaultTableModel getInventario(String codigo, String presentacion, String marca, String TProd, JTable tabla) {

        try {
            String titulos[] = new String[7];
            for (byte i = 0; i < titulos.length; i++) {
                titulos[i] = tabla.getColumnName(i);
            }
            String sql = "SELECT "
                    + "i.id AS num, "
                    + "i.codigo AS cod, "
                    + "i.cantidad AS cant, "
                    + "i.precio, "
                    + "m.marca, "
                    + "dp.detalle_presentacion AS detalle, "
                    + "p.nombreempresa AS prov "
                    + "FROM producto i "
                    + "INNER JOIN marca m ON m.id = i.marca_id  "
                    + "INNER JOIN detallepresentacion dp ON dp.producto_id = i.id "
                    + "INNER JOIN proveedor p ON p.id = i.proveedor_id "
                    + "INNER JOIN presentacion pres ON pres.id = dp.Presentacion_id "
                    + "INNER JOIN tipoproducto tp ON tp.id = i.TipoProducto_id "
                    + "WHERE "
                    + "i.codigo LIKE '%" + codigo + "%' "
                    + "AND m.marca LIKE '%" + marca + "%' "
                    + "AND pres.presentacion LIKE '%" + presentacion + "%' "
                    + "AND tp.tipoProducto LIKE '%" + TProd + "%';";
            DefaultTableModel modelo = new DefaultTableModel(null, titulos);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            String registros[] = new String[7];
            while (rs.next()) {
                registros[0] = rs.getString("num");
                registros[1] = rs.getString("cod");
                registros[2] = rs.getString("cant");
                registros[3] = rs.getString("precio");
                registros[4] = rs.getString("detalle");
                registros[5] = rs.getString("prov");
                registros[6] = rs.getString("marca");
                modelo.addRow(registros);
            }
            return modelo;
        } catch (SQLException ex) {
            Logger.getLogger(Inventario.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}