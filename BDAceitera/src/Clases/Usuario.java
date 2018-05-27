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

/**
 *
 * @author mynor
 */
public class Usuario {

    private final Connection con;
    private final Conexion conexion;

    public Usuario() {
        conexion = new Conexion();
        con = conexion.getConnection();
    }

    public boolean IncioSesion(String Contrasenia,String Usuario) {
        try {
            String sql = "SELECT contrasenia FROM usuario where usuario = '"+Usuario+"';";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            String pass="";
            while (rs.next()) {
                pass=rs.getString("contrasenia");
            }
            return pass.equals(Contrasenia);
        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
