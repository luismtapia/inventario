package inicio;

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

public class InicioController implements Initializable{
    
    @FXML
    private TextField cantidad;
    @FXML
    private Button btn_ver_tallas,btn_agregar_a_ticket,btn_quitar_de_ticket,btn_cancelar,btn_cobrar;
    @FXML
    private Label lbl1,lbl2,lbl3,lbl4;
    @FXML
    private Label lbl_total_invertido,lbl_ganancia_esperada,lbl_total;
    @FXML
    private void handleBotones(ActionEvent event) throws SQLException{
//        if(event.getSource() == btn_depto_1){
//            lbl3.setText("ROPA");
//            //llenarProductos();
//        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        color(Color.web("#D4AF37"));
        //imagenes();
//        nombre_empleado.setPromptText("Nombre del empleado");
//        direccion_empleado.setPromptText("Direccion del Empleado");
    }
    
    
    
    
    private void color(Color color){
        btn_agregar_a_ticket.setTextFill(color);
        btn_ver_tallas.setTextFill(color);
        btn_quitar_de_ticket.setTextFill(color);
        btn_cancelar.setTextFill(color);
        btn_cobrar.setTextFill(color);
        lbl1.setTextFill(color);
        lbl2.setTextFill(color);
        lbl3.setTextFill(color);
        lbl4.setTextFill(color);
        lbl_total_invertido.setTextFill(color);
        lbl_ganancia_esperada.setTextFill(color);
        lbl_total.setTextFill(color);
    }
    
   
    
    private void imagenes(){
//        btn_depto_1.setGraphic(new ImageView(new Image("iconos/ropa.png")));
//        btn_depto_1.setContentDisplay(ContentDisplay.BOTTOM);
//        btn_depto_2.setGraphic(new ImageView(new Image("iconos/accesorios.png")));
//        btn_depto_2.setContentDisplay(ContentDisplay.BOTTOM);
    }
    
}