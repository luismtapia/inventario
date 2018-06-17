package inicio;

import inventariogui.SQL_Consultar;
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
    SQL_Consultar con_sql = new SQL_Consultar();
    
    @FXML
    private TextField cantidad;
    @FXML
    private Button btn_ver_tallas,btn_agregar_a_ticket,btn_cancelar;
    @FXML
    private Label lbl1,lbl2,lbl3,lbl4,lbl5,lbl6,lbl7;
    @FXML private Label lbl_total_invertido,lbl_ganancia_esperada,lbl_total,lbl_activo_fijo,lbl_efectivo,lbl_utilidades_retenidas;
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
        fontSize("-fx-font-size: 13pt");
        //imagenes();
        //recuperarDATOS();
        cantidad.setPromptText("$ Ingrese monto");
        operaciones();
    }
    
    private void color(Color color){
        btn_agregar_a_ticket.setTextFill(color);
        btn_ver_tallas.setTextFill(color);
        btn_cancelar.setTextFill(color);
        lbl1.setTextFill(color);
        lbl2.setTextFill(color);
        lbl3.setTextFill(color);
        lbl4.setTextFill(color);
        lbl5.setTextFill(color);
        lbl6.setTextFill(color);
        lbl7.setTextFill(color);
        lbl_total_invertido.setTextFill(color);
        lbl_ganancia_esperada.setTextFill(color);
        lbl_total.setTextFill(color);
        lbl_activo_fijo.setTextFill(color);
        lbl_efectivo.setTextFill(color);
        lbl_utilidades_retenidas.setTextFill(color);
    }
    
    private void fontSize(String css){
        lbl1.setStyle(css);
        lbl2.setStyle(css);
        lbl3.setStyle(css);
        lbl4.setStyle(css);
        lbl5.setStyle(css);
        lbl6.setStyle(css);
        lbl_total_invertido.setStyle(css);
        lbl_ganancia_esperada.setStyle(css);
        lbl_total.setStyle(css);
        lbl_activo_fijo.setStyle(css);
        lbl_efectivo.setStyle(css);
        lbl_utilidades_retenidas.setStyle(css);
    }
    
    private void imagenes(){
        
//        btn_depto_1.setGraphic(new ImageView(new Image("iconos/ropa.png")));
//        btn_depto_1.setContentDisplay(ContentDisplay.BOTTOM);
//        btn_depto_2.setGraphic(new ImageView(new Image("iconos/accesorios.png")));
//        btn_depto_2.setContentDisplay(ContentDisplay.BOTTOM);
    }
    
    private void recuperarDATOS(){
        try {
            con_sql.get_SQL("select * from productos where codigo = ");//sumas
        } catch (Exception e) {System.out.println(""+e);
        }
    }
    
    private void operaciones(){
        double invertido=Double.parseDouble(lbl_total_invertido.getText());
        double ganancia_esperada=Double.parseDouble(lbl_ganancia_esperada.getText());
        double total=invertido+ganancia_esperada;
        lbl_total.setText(""+total);
    }
}