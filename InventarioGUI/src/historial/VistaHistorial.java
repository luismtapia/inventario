package historial;

import java.sql.Date;

public class VistaHistorial {
    int no_ticket;
    Date fecha_venta;
    int id_cliente;
    double total_a_pagar;
    String codigo;
    int cantidad;
    double precio_venta;
    String nombre;
    String descripcion;
    double precio_compra;
    int existencia;
    String nombre_cliente;

    public VistaHistorial(int no_ticket, Date fecha_venta, int id_cliente, double total_a_pagar, String codigo, int cantidad, double precio_venta, String nombre, String descripcion, double precio_compra, int existencia, String nombre_cliente) {
        this.no_ticket = no_ticket;
        this.fecha_venta = fecha_venta;
        this.id_cliente = id_cliente;
        this.total_a_pagar = total_a_pagar;
        this.codigo = codigo;
        this.cantidad = cantidad;
        this.precio_venta = precio_venta;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio_compra = precio_compra;
        this.existencia = existencia;
        this.nombre_cliente = nombre_cliente;
    }

    public int getNo_ticket() {
        return no_ticket;
    }

    public void setNo_ticket(int no_ticket) {
        this.no_ticket = no_ticket;
    }

    public Date getFecha_venta() {
        return fecha_venta;
    }

    public void setFecha_venta(Date fecha_venta) {
        this.fecha_venta = fecha_venta;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public double getTotal_a_pagar() {
        return total_a_pagar;
    }

    public void setTotal_a_pagar(double total_a_pagar) {
        this.total_a_pagar = total_a_pagar;
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

    public double getPrecio_venta() {
        return precio_venta;
    }

    public void setPrecio_venta(double precio_venta) {
        this.precio_venta = precio_venta;
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

    public double getPrecio_compra() {
        return precio_compra;
    }

    public void setPrecio_compra(double precio_compra) {
        this.precio_compra = precio_compra;
    }

    public int getExistencia() {
        return existencia;
    }

    public void setExistencia(int existencia) {
        this.existencia = existencia;
    }

    public String getNombre_cliente() {
        return nombre_cliente;
    }

    public void setNombre_cliente(String nombre_cliente) {
        this.nombre_cliente = nombre_cliente;
    }

    

    @Override
    public String toString() {
        return "no_ticket=" + no_ticket + "\t fecha_venta=" + fecha_venta +  " \tcantidad=" + cantidad + "\t nombre=" + nombre;
    }
    
    
}
