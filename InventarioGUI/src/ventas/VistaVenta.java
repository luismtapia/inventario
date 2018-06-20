package ventas;

/**
 *
 * @author luis
 */
public class VistaVenta {
    String codigo;
    String nombre;
    String descripcion;
    String no_ticket;
    double precio_venta;
    //double subtotal;
    int cantidad;

    public VistaVenta(String codigo, String nombre, String descripcion, String no_ticket, double precio_venta, int cantidad) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.no_ticket = no_ticket;
        this.precio_venta = precio_venta;
        this.cantidad = cantidad;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNo_ticket() {
        return no_ticket;
    }

    public void setNo_ticket(String no_ticket) {
        this.no_ticket = no_ticket;
    }

    public double getPrecio_venta() {
        return precio_venta;
    }

    public void setPrecio_venta(double precio_venta) {
        this.precio_venta = precio_venta;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return String.format("%-20s", codigo) + String.format("%1$-40s",descripcion) + String.format("%15d", cantidad)+ String.format("%15s", precio_venta);
    }
    
    
}
