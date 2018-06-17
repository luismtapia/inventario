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
            String query="INSERT INTO productos (codigo, nombre, descripcion,precio_compra,precio_venta,existencia) values(?, ?, ?, ?, ?, ?)";
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
}
