package ventas;

import inventariogui.SQL_Consultar;
import javafx.scene.input.KeyEvent;
import productos.*;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;

public class VentasController implements Initializable{
    SQL_Consultar con_sql=new SQL_Consultar();
    
    @FXML private TextField bandera,cliente;
    @FXML private Button btn_buscar,btn_agregar;
    @FXML private ComboBox cmb_encontrados;
    @FXML private Label lbl1,lbl2,lbl3,lbl4,lbl5,lbl6,lbl7,lbl8,lbl9,lbl10,lbl11,lbl12,lbl_folio;
    @FXML private Label lbl_codigo,lbl_nombre,lbl_precio,lbl_existencia;
    @FXML
    private ListView lista1;
    
    @FXML
    private void handleKeyPressed(KeyEvent ke){
        String buscar;
        buscar=bandera.getText();
        llenarcombobox(buscar);
        
        //lbl_codigo.setText(buscar);
        busqueda(buscar);
        System.out.println("Key Pressed: " + ke.getCode());
    }
    
    @FXML
    private void handleBotones(ActionEvent event) throws SQLException{
        if(event.getSource() == btn_agregar){
            //llenarProductos();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb){
        color(Color.web("#D4AF37"));
        //imagenes();
        ticket();
        bandera.setPromptText("Producto codigo/descripcion");
        cliente.setPromptText("Nombre del cliente");
    }
    
    private void llenarcombobox(String like){
        cmb_encontrados.getItems().clear();
        cmb_encontrados.setPromptText("PRODUCTOS");
        ResultSet rs = con_sql.get_SQL("select * from productos where codigo like '"+like+"%'");
        try {
            //cmb_encontrados.setPromptText(rs.getString("nombre"));
            while (rs.next()) {                
                cmb_encontrados.getItems().add(rs.getString("nombre"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    private void color(Color color){
        lbl1.setTextFill(color);
        lbl2.setTextFill(color);
        lbl3.setTextFill(color);
        lbl4.setTextFill(color);
        lbl5.setTextFill(color);
        lbl6.setTextFill(color);
        lbl7.setTextFill(color);
        lbl8.setTextFill(color);
        lbl9.setTextFill(color);
        lbl10.setTextFill(color);
        lbl11.setTextFill(color);
        btn_buscar.setTextFill(color);
        lbl_codigo.setTextFill(color);
        lbl_nombre.setTextFill(color);
        lbl_precio.setTextFill(color);
        lbl_existencia.setTextFill(color);
    }
    
    private void imagenes(){
//        btn_depto_1.setGraphic(new ImageView(new Image("iconos/ropa.png")));
//        btn_depto_1.setContentDisplay(ContentDisplay.BOTTOM);
//        btn_depto_2.setGraphic(new ImageView(new Image("iconos/accesorios.png")));
//        btn_depto_2.setContentDisplay(ContentDisplay.BOTTOM);
    }
    
    private void busqueda(String like){
        ResultSet rs = con_sql.get_SQL("select * from productos where codigo like '"+like+"%'");
        try {
            while (rs.next()) {                
                lbl_codigo.setText(rs.getString("codigo"));
                lbl_nombre.setText(rs.getString("nombre"));
                lbl_precio.setText(""+Double.parseDouble(rs.getString("precio_venta")));
                lbl_existencia.setText(""+rs.getInt("existencia"));
            }
        } catch (SQLException | NumberFormatException e) {
            System.out.println(e);
        }
    }
    
    private void ticket(){
        ResultSet rs = con_sql.get_SQL("select top 1 * from ventas order by no_ticket desc");
        try {
            while (rs.next()) {                
                lbl_folio.setText(rs.getString("no_ticket"));
            }
        } catch (SQLException | NumberFormatException e) {System.out.println(e);}
    }
}