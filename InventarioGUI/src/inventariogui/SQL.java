package inventariogui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luis
 */
public class SQL {
    public static Connection Conexion;
    private final String usuario = "fernanda";
    private final String contraseña = "123";
    private final String dbnombre = "FERNANDA";
    
    public void Conectar(){
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");//CARGA EL DRIVER
            String connectionUrl = "jdbc:sqlserver://TaRo:1433;databaseName=FERNANDA";
            Conexion = DriverManager.getConnection(connectionUrl,usuario,contraseña);
            
            //Conexion = DriverManager.getConnection(connectionUrl,usuario,contraseña);
            //Conexion = DriverManager.getConnection(connectionUrl,usuariogerente,contraseña);
            System.out.print("Conectado. INICIO");
            System.out.println("Se ha iniciado la conexión con el servidor de forma exitosa");
        }
        catch (ClassNotFoundException | SQLException ex){
            System.out.println("Error.");
            Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void closeConnection() {
        try {
            Conexion.close();
            System.out.println("Se ha finalizado la conexión con el servidor");
        } catch (SQLException ex) {
            Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
