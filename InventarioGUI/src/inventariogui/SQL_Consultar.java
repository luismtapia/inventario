
package inventariogui;

import static inventariogui.SQL.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author luis
 */
public class SQL_Consultar {
    
    public ResultSet get_SQL(String query){
        ResultSet rs=null;
        try{
            Statement st= Conexion.createStatement();
            rs=st.executeQuery(query);
        }catch(Exception e){
            System.out.println("error al ejecutar"+e);
        }
        return rs;
    }
    
    public boolean existencia(int actualizacion_existencia, String codigo) {
        String query = "update productos set existencia = '"+actualizacion_existencia+"' where codigo = '"+codigo+"'";
        try {
            PreparedStatement st =Conexion.prepareStatement(query);
            st.execute();
            return true;
        } catch (Exception e) {
            System.out.println("ERROR EN SQL en existencia...: "+e);
            System.out.println(query+"\n");
        }
        return false;
    }
    
}
