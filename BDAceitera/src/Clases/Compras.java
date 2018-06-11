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
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

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
            String dia = Integer.toString(c.get(Calendar.DATE));
            String mes = Integer.toString(c.get(Calendar.MONTH)+1);
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
    
    public DefaultTableModel getCompras(String id, String Fecha, JTable tabla) {
        try {
            String titulos[] = new String[5];
            for (byte i = 0; i < titulos.length; i++) {
                titulos[i] = tabla.getColumnName(i);
            }
            String sql = "SELECT compras.id AS id, compras.fecha AS fecha, compras.total AS total,"
                    + "proveedor.nombreDistribuidor AS Distribuidor, "
                    + "proveedor.nombreEmpresa AS Empresa FROM compras INNER JOIN detallecompra ON "
                    + "compras.id = detallecompra.compras_id INNER JOIN producto ON "
                    + "detallecompra.Producto_id = producto.id INNER JOIN proveedor ON "
                    + "producto.Proveedor_id = proveedor.id WHERE compras.id LIKE '%" + id + "%'"
                    + "AND fecha LIKE '%" + Fecha + "%' GROUP BY compras.id;";
            DefaultTableModel modelo = new DefaultTableModel(null, titulos);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            String registros[] = new String[5];
            while (rs.next()) {
                registros[0] = rs.getString("id");
                registros[1] = rs.getString("Empresa");
                registros[2] = rs.getString("Distribuidor");
                registros[3] = rs.getString("fecha");
                registros[4] = rs.getString("total");
                modelo.addRow(registros);
            }
            return modelo;
        } catch (SQLException ex) {
            Logger.getLogger(Inventario.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public DefaultTableModel getDCompras(int id, JTable tabla) {
        try {
            String titulos[] = new String[4];
            for (byte i = 0; i < titulos.length; i++) {
                titulos[i] = tabla.getColumnName(i);
            }
            String sql = "SELECT compras.id AS id, detallecompra.cantidad AS cantidad, producto.codigo AS Codigo, "
                    + "producto.precio AS precio FROM compras "
                    + "INNER JOIN detallecompra ON compras.id = detallecompra.compras_id INNER JOIN producto ON "
                    + "detallecompra.Producto_id = producto.id WHERE compras.id = " + id + ";";
            DefaultTableModel modelo = new DefaultTableModel(null, titulos);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            String registros[] = new String[4];
            while (rs.next()) {
                registros[0] = rs.getString("id");
                registros[1] = rs.getString("Codigo");
                registros[2] = rs.getString("cantidad");
                registros[3] = rs.getString("precio");
                modelo.addRow(registros);
            }
            return modelo;
        } catch (SQLException ex) {
            Logger.getLogger(Inventario.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}