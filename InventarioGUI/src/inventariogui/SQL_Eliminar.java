package inventariogui;

import static inventariogui.SQL.Conexion;
import java.sql.PreparedStatement;

/**
 *
 * @author luis
 */
public class SQL_Eliminar {
    public boolean elimina(String tabla, String columna, String bandera){
        try 
        {
            String query ="DELETE FROM "+tabla+" WHERE "+columna+" = '"+bandera+"'";
            PreparedStatement st =Conexion.prepareStatement(query);
            st.execute();
            return true;
        }
        catch(Exception e){System.out.println(e.getMessage());}
        return false;
    }
}
