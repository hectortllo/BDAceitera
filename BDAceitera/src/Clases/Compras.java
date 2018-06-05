package Clases;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author hectortllo
 */
public class Compras {
    private final Connection con;
    private final Conexion conexion;

    public Compras() {
        conexion = new Conexion();
        con = conexion.getConnection();
    }
    
    public DefaultComboBoxModel getProveedor(DefaultComboBoxModel modelo) {
        try {
            String sql = "SELECT nombreEmpresa FROM proveedor;";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                modelo.addElement(rs.getString("nombreEmpresa"));
            }
            return modelo;
        } catch (SQLException ex) {
            Logger.getLogger(Inventario.class.getName()).log(Level.SEVERE, null, ex);
            return modelo;
        }
    }
}
