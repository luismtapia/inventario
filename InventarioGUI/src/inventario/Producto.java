/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventario;

/**
 *
 * @author luis
 */
public class Producto {
    String codigo;
    String nombre;
    String descripcion;
    double precio_compra;
    double precio_venta;
    int existencia;
    double total_invertido;
    double ganancia_esperada;

    public Producto(String codigo, String nombre, String descripcion, double precio_compra, double precio_venta, int existencia, double total_invertido, double ganancia_esperada) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio_compra = precio_compra;
        this.precio_venta = precio_venta;
        this.existencia = existencia;
        this.total_invertido = total_invertido;
        this.ganancia_esperada = ganancia_esperada;
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

    public double getPrecio_compra() {
        return precio_compra;
    }

    public void setPrecio_compra(double precio_compra) {
        this.precio_compra = precio_compra;
    }

    public double getPrecio_venta() {
        return precio_venta;
    }

    public void setPrecio_venta(double precio_venta) {
        this.precio_venta = precio_venta;
    }

    public int getExistencia() {
        return existencia;
    }

    public void setExistencia(int existencia) {
        this.existencia = existencia;
    }

    public double getTotal_invertido() {
        return total_invertido;
    }

    public void setTotal_invertido(double total_invertido) {
        this.total_invertido = total_invertido;
    }

    public double getGanancia_esperada() {
        return ganancia_esperada;
    }

    public void setGanancia_esperada(double ganancia_esperada) {
        this.ganancia_esperada = ganancia_esperada;
    }

    @Override
    public String toString() {
        return  codigo + "\t" + nombre + ", descripcion=" + descripcion + ", precio_compra=" + precio_compra + ", precio_venta=" + precio_venta + ", existencia=" + existencia + "\n total_invertido=" + total_invertido + ", ganancia_esperada=" + ganancia_esperada;
    }
    
    
    
}
