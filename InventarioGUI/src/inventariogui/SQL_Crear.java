package inventariogui;

import static inventariogui.SQL.Conexion;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import productos.Producto;

/**
 *
 * @author luis
 */
public class SQL_Crear {
    public Boolean insertarProducto(Producto nuevo){
        try{
            String query="INSERT INTO productos (codigo, nombre, descripcion,precio_compra,precio_venta,existencia,total_invertido,ganancia_esperada) values(?, ?, ?, ?, ?, ?, null, null)";
            PreparedStatement st=Conexion.prepareStatement(query);
            st.setString(1, nuevo.getCodigo());
            st.setString(2, nuevo.getNombre());
            st.setString(3, nuevo.getDescripcion());
            st.setDouble(4, nuevo.getPrecio_compra());
            st.setDouble(5, nuevo.getPrecio_venta());
            st.setInt(6, nuevo.getExistencia());
            st.execute();
            return true;
        }catch(SQLException e){
            System.out.println(e);
        }
        return false;//Nos indica se se hizo bien la inserción en la base de datos //investigar este comentario
    }
    
    public Boolean insertarVenta(String cliente, double total){
        try{
            String query="INSERT INTO ventas (fecha_venta, id_cliente, total_a_pagar) values(getdate(), "+cliente+", "+total+")";
            PreparedStatement st=Conexion.prepareStatement(query);
            st.execute();
            return true;
        }catch(SQLException e){
            System.out.println(e);
        }
        return false;//Nos indica se se hizo bien la inserción en la base de datos //investigar este comentario
    }
    
    public Boolean insertarCliente(String cliente){
        try{
            String query="INSERT INTO clientes (nombre_cliente) values('"+cliente+"')";
            PreparedStatement st=Conexion.prepareStatement(query);
            st.execute();
            return true;
        }catch(SQLException e){
            System.out.println(e);
        }
        return false;//Nos indica se se hizo bien la inserción en la base de datos //investigar este comentario
    }
    
    public Boolean insertarVenta_es_de_producto(int no_ticket, String codigo, int cantidad, double precio_venta){
        try{
            String query="INSERT INTO venta_es_de_producto (no_ticket, codigo, cantidad, precio_venta) values("+no_ticket+", "+codigo+", "+cantidad+", "+precio_venta+")";
            PreparedStatement st=Conexion.prepareStatement(query);
            st.execute();
            return true;
        }catch(SQLException e){
            System.out.println(e);
        }
        return false;//Nos indica se se hizo bien la inserción en la base de datos //investigar este comentario
    }
    
    
}
