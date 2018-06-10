package Clases;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
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

    public boolean insertarCompra(float total) {
        try {
            Calendar c = Calendar.getInstance();
            String dia = Integer.toString(c.get(Calendar.DATE) + 1);
            String mes = Integer.toString(c.get(Calendar.MONTH));
            String anio = Integer.toString(c.get(Calendar.YEAR));
            String fecha = anio + "/" + mes + "/" + dia;
            String query = "INSERT INTO compras(total, fecha) VALUES(?,?);";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setFloat(1, total);
            pst.setString(2, fecha);
            int n = pst.executeUpdate();
            return n != 0;
        } catch (SQLException ex) {
            Logger.getLogger(Compras.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean insertarMarca(String marca) {
        try {
            String query = "INSERT INTO marca(marca) VALUE(?)";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, marca);
            int n = pst.executeUpdate();
            return n != 0;
        } catch (SQLException ex) {
            Logger.getLogger(Compras.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean insertarTipoProducto(String tipo) {
        try {
            String query = "INSERT INTO tipoproducto(tipoProducto) VALUE(?)";

            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, tipo);
            int n = pst.executeUpdate();
            return n != 0;
        } catch (SQLException ex) {
            Logger.getLogger(Compras.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean insertarPresentacion(String presentacion) {
        try {
            String query = "INSERT INTO presentacion(presentacion) VALUE(?)";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, presentacion);
            int n = pst.executeUpdate();
            return n != 0;
        } catch (SQLException ex) {
            Logger.getLogger(Compras.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean insertarProducto(String nombreEmpresa, String nombreDistribuidor,
            String telefonoEmpresa, String telefonoDistribuidor) {
        try {
            CallableStatement procedimiento = con.prepareCall("{call InsertarProveedor(?,?,?,?)}");
            procedimiento.setString(1, nombreEmpresa);
            procedimiento.setString(2, nombreDistribuidor);
            procedimiento.setString(3, telefonoEmpresa);
            procedimiento.setString(4, telefonoDistribuidor);
            procedimiento.execute();
        } catch (SQLException ex) {
            Logger.getLogger(Proveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
}
