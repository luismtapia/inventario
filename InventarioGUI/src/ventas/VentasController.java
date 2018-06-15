package ventas;

import productos.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
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
    @FXML
    private TextField bandera,cliente;
    @FXML
    private Button btn_buscar;
    @FXML
    private Label lbl1,lbl2,lbl3,lbl4,lbl5,lbl6,lbl7,lbl8;
    @FXML
    private ListView lista1;
    
    @FXML
    private void handleBotones(ActionEvent event) throws SQLException{
//        if(event.getSource() == btn_depto_1){
//            lbl3.setText("ROPA");
//            //llenarProductos();
//        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        //color(Color.web("#D4AF37"));
        //imagenes();
        bandera.setPromptText("Producto codigo/descripcion");
        cliente.setPromptText("Nombre del cliente");
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
    }
    
   
    
    private void imagenes(){
//        btn_depto_1.setGraphic(new ImageView(new Image("iconos/ropa.png")));
//        btn_depto_1.setContentDisplay(ContentDisplay.BOTTOM);
//        btn_depto_2.setGraphic(new ImageView(new Image("iconos/accesorios.png")));
//        btn_depto_2.setContentDisplay(ContentDisplay.BOTTOM);
    }
    
}