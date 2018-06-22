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
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mynor
 */
public class Usuario {

    private final Connection con;
    private final Conexion conexion;
    private String usuario;
    private int id;
    private boolean admin;

    public Usuario() {
        conexion = new Conexion();
        con = conexion.getConnection();
    }

    public boolean isAdmin() {
        return admin;
    }

    public int getId() {
        return id;
    }

    public String getUsuario() {
        return usuario;
    }

    public boolean IncioSesion(String Contrasenia, String Usuario) {
        try {
            String sql = "SELECT contrasenia, nombre, id, tipousuario_id FROM usuario where nombre = '" + Usuario + "' and activo = true;";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            String pass = "";
            boolean encontrado = false;
            while (rs.next()) {
                pass = rs.getString("contrasenia");
                if (pass.equals(Contrasenia)) {
                    usuario = rs.getString("nombre");
                    id = rs.getInt("id");
                    admin = rs.getInt("tipousuario_id") == 1;
                    encontrado = true;
                    break;
                }
            }
            return encontrado;
        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean insertarUsuario(String nombre, String apellido, String noTelefono, String password,
            boolean activo, int TipoUsuario) {
        try {
            CallableStatement procedimiento = con.prepareCall("{call InsertarUsuario(?,?,?,?,?,?)}");
            procedimiento.setString(1, nombre);
            procedimiento.setString(2, apellido);
            procedimiento.setString(3, noTelefono);
            procedimiento.setString(4, password);
            procedimiento.setBoolean(5, activo);
            procedimiento.setInt(6, TipoUsuario);
            return procedimiento.executeUpdate() != 0;
        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public DefaultComboBoxModel puestos() {
        try {
            DefaultComboBoxModel modelo = new DefaultComboBoxModel();
            String sql = "SELECT tipoUsuario FROM tipousuario;";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                modelo.addElement(rs.getObject("tipoUsuario"));
            }
            return modelo;
        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public boolean RecuperarUsuario(String noTel, String contrasenia) {
        try {
            String sql = "SELECT recuperarUsuario(?,?);";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, noTel);
            pst.setString(2, contrasenia);
            System.out.println(contrasenia);
            ResultSet rs = pst.executeQuery();
            rs.next();
            return rs.getBoolean(1);
        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public DefaultTableModel getUsuarios(String nombre, String apellido, JTable tabla) {
        try {
            String titulos[] = new String[6];
            for (byte i = 0; i < titulos.length; i++) {
                titulos[i] = tabla.getColumnName(i);
            }
            String sql = "SELECT id AS No, nombre AS Nombre, apellido AS Apellido, "
                    + "noTelefono AS telefono, activo AS Activo, TipoUsuario_id AS tUsuario FROM Usuario"
                    + " WHERE nombre LIKE '%" + nombre + "%' AND apellido LIKE '%"
                    + apellido + "%'";
            DefaultTableModel modelo = new DefaultTableModel(null, titulos);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            String registros[] = new String[6];
            rs.next();
            while (rs.next()) {
                registros[0] = "" + (rs.getInt("No") - 1);
                registros[1] = rs.getString("Nombre");
                registros[2] = rs.getString("Apellido");
                registros[3] = rs.getString("telefono");
                if (rs.getBoolean("Activo")) {
                    registros[4] = "Activo";
                } else {
                    registros[4] = "No Activo";
                }
                if (rs.getInt("tUsuario") == 1) {
                    registros[5] = "Admin";
                } else {
                    registros[5] = "Vendedor";
                }
                modelo.addRow(registros);
            }
            return modelo;
        } catch (SQLException ex) {
            Logger.getLogger(Inventario.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
