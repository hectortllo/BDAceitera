package Clases;

/**
 *
 * @author hectortllo
 */
public class datosProducto {

    public datosProducto() {
        this.Producto_id = 0;
        this.codigo = "";
        this.cantidad = 0;
        this.precio = 0;
        this.TipoProducto_id = -1;
        this.Proveedor_id = -1;
        this.marca_id = -1;
        this.presentacion_id = -1;
        this.detalle_presentacion = "";
        this.costo = 0;
    }

    public int getProducto_id() {
        return Producto_id;
    }

    public void setProducto_id(int Producto_id) {
        this.Producto_id = Producto_id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getTipoProducto_id() {
        return TipoProducto_id;
    }

    public void setTipoProducto_id(int TipoProducto_id) {
        this.TipoProducto_id = TipoProducto_id;
    }

    public int getProveedor_id() {
        return Proveedor_id;
    }

    public void setProveedor_id(int Proveedor_id) {
        this.Proveedor_id = Proveedor_id;
    }

    public int getMarca_id() {
        return marca_id;
    }

    public void setMarca_id(int marca_id) {
        this.marca_id = marca_id;
    }

    public int getPresentacion_id() {
        return presentacion_id;
    }

    public void setPresentacion_id(int presentacion_id) {
        this.presentacion_id = presentacion_id;
    }

    public String getDetalle_presentacion() {
        return detalle_presentacion;
    }

    public void setDetalle_presentacion(String detalle_presentacion) {
        this.detalle_presentacion = detalle_presentacion;
    }

    public float getCosto() {
        return costo;
    }

    public void setCosto(float costo) {
        this.costo = costo;
    }

    private int Producto_id;
    private String codigo;
    private int cantidad;
    private float precio;
    private int TipoProducto_id;
    private int Proveedor_id;
    private int marca_id;
    private int presentacion_id;
    private String detalle_presentacion;
    private float costo;
}
