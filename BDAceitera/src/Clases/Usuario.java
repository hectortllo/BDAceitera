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
import org.omg.CosNaming.BindingType;

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
}
