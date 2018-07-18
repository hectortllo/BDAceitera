package Clases;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

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
            return false;
        }
        return true;
    }
    
    public DefaultTableModel getProveedores(String empresa, String distribuidor, JTable tabla) {
        try {
            String titulos[] = new String[4];
            for (byte i = 0; i < titulos.length; i++) {
                titulos[i] = tabla.getColumnName(i);
            }
            String sql = "SELECT * FROM proveedor"
                    + " WHERE nombreEmpresa LIKE '%" + empresa + "%' AND nombreDistribuidor LIKE '%"
                    + distribuidor + "%'";
            DefaultTableModel modelo = new DefaultTableModel(null, titulos);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            String registros[] = new String[4];
            while (rs.next()) {
                registros[0] = rs.getString("nombreEmpresa");
                registros[1] = rs.getString("noTelEmpresa");
                registros[2] = rs.getString("nombreDistribuidor");
                registros[3] = rs.getString("noTelDistribuidor");
                modelo.addRow(registros);
            }
            return modelo;
        } catch (SQLException ex) {
            Logger.getLogger(Inventario.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}