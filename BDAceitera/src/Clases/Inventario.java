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
            String titulos[] = new String[8];
            for (byte i = 0; i < titulos.length; i++) {
                titulos[i] = tabla.getColumnName(i);
            }
            String sql = "SELECT \n"
                    + "    i.id AS num,\n"
                    + "    p.tipoProducto AS producto,\n"
                    + "    i.codigo AS cod,\n"
                    + "    m.marca,\n"
                    + "    dp.detalle_presentacion AS detalle,\n"
                    + "    i.cantidad AS cant,\n"
                    + "    i.precio,\n"
                    + "    pv.nombreEmpresa AS empresa\n"
                    + "FROM\n"
                    + "    producto i\n"
                    + "        INNER JOIN\n"
                    + "    marca m ON m.id = i.marca_id\n"
                    + "        INNER JOIN\n"
                    + "    proveedor pv ON pv.id = i.Proveedor_id\n"
                    + "        INNER JOIN\n"
                    + "    detallepresentacion dp ON dp.producto_id = i.id\n"
                    + "        INNER JOIN\n"
                    + "    tipoproducto p ON p.id = i.TipoProducto_id\n"
                    + "        INNER JOIN\n"
                    + "    presentacion pres ON pres.id = dp.Presentacion_id\n"
                    + "        INNER JOIN\n"
                    + "    tipoproducto tp ON tp.id = i.TipoProducto_id\n"
                    + "WHERE\n"
                    + "    i.codigo LIKE '%" + codigo + "%'\n"
                    + "        AND m.marca LIKE '%" + marca + "%'\n"
                    + "        AND pres.presentacion LIKE '%" + presentacion + "%'\n"
                    + "        AND tp.tipoProducto LIKE '%" + TProd + "%'\n"
                    + "ORDER BY num;";
            DefaultTableModel modelo = new DefaultTableModel(null, titulos);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            String registros[] = new String[8];
            while (rs.next()) {
                registros[0] = rs.getString("num");
                registros[1] = rs.getString("producto");
                registros[2] = rs.getString("cod");
                registros[3] = rs.getString("marca");
                registros[4] = rs.getString("detalle");
                registros[5] = rs.getString("cant");
                registros[6] = rs.getString("precio");
                registros[7] = rs.getString("empresa");
                modelo.addRow(registros);
            }
            return modelo;
        } catch (SQLException ex) {
            Logger.getLogger(Inventario.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
