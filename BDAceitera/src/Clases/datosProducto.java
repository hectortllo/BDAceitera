package Clases;

/**
 *
 * @author hectortllo
 */
public class datosProducto 
{
    public datosProducto(String vCodigo, int vCantidad, float vPrecio, int vTipoProductoId, int vProveedorId,
            int vMarcaId, int vPresentacion_id, String vDetallePresent, float vTotal, String vFecha, float vCosto)
    {
        this.codigo = vCodigo;
        this.cantidad = vCantidad;
        this.precio = vPrecio;
        this.TipoProducto_id = vTipoProductoId;
        this.Proveedor_id = vProveedorId;
        this.marca_id = vMarcaId;
        this.presentacion_id = vPresentacion_id;
        this.detalle_presentacion = vDetallePresent;
        this.total = vTotal;
        this.fecha = vFecha;
        this.costo = vCosto;
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

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public float getCosto() {
        return costo;
    }

    public void setCosto(float costo) {
        this.costo = costo;
    }
    
    
    private String codigo;
    private int cantidad;
    private float precio;
    private int TipoProducto_id;
    private int Proveedor_id;
    private int marca_id;
    private int presentacion_id;
    private String detalle_presentacion;
    private float total;
    private String fecha;
    private float costo;
    
}
