package productos;

import inventariogui.SQL_Crear;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class ProductosController implements Initializable{
    SQL_Crear con_sql = new SQL_Crear();
    
    @FXML private Button btn_guardar,btn_cancelar;
    @FXML private Label lbl1,lbl2,lbl3,lbl4,lbl5,lbl6,lbl7;
    @FXML private TextField codigo,nombre,descripcion,precio_compra,precio_venta,existencia;
    @FXML
    private void handleBotones(ActionEvent event) throws SQLException{
            if(event.getSource() == btn_cancelar){
                limpieza();
            }else
                if(event.getSource() == btn_guardar){
                    controlDATOS();
                }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        color(Color.web("#D4AF37"));
        //imagenes();
        codigo.setPromptText("Codigo del producto");
        nombre.setPromptText("Nombre del producto");
        descripcion.setPromptText("Descripcion del producto");
        precio_compra.setPromptText("Precio de compra");
        precio_venta.setPromptText("Precio de venta");
        existencia.setPromptText("Existencia del producto");
    }
    
    private void color(Color color){
        btn_cancelar.setTextFill(color);
        btn_guardar.setTextFill(color);
        lbl1.setTextFill(color);
        lbl2.setTextFill(color);
        lbl3.setTextFill(color);
        lbl4.setTextFill(color);
        lbl5.setTextFill(color);
        lbl6.setTextFill(color);
        lbl7.setTextFill(color);
    }
    
    private void imagenes(){
//        btn_depto_1.setGraphic(new ImageView(new Image("iconos/ropa.png")));
//        btn_depto_1.setContentDisplay(ContentDisplay.BOTTOM);
//        btn_depto_2.setGraphic(new ImageView(new Image("iconos/accesorios.png")));
//        btn_depto_2.setContentDisplay(ContentDisplay.BOTTOM);
    }
    
    private void guardar(){
        try {
            Producto nuevo= new Producto(
                    codigo.getText(),
                    nombre.getText(),
                    descripcion.getText(),
                    Double.parseDouble(precio_compra.getText()),
                    Double.parseDouble(precio_venta.getText()),
                    Integer.parseInt(existencia.getText())
            );
                if(con_sql.insertarProducto(nuevo)){
                   Alert msg=new Alert(Alert.AlertType.INFORMATION);
                   msg.setTitle("Registro guardado");
                   msg.setContentText("guardado correctamente");
                   //msg.setGraphic(new ImageView(new Image("iconos/area.png")));
                   msg.show();
                }else{
                    System.out.println("hay error");
                }
            } catch (Exception e) {
                Alert m=new Alert(Alert.AlertType.INFORMATION);
                m.setTitle("Hay un error");
                m.setContentText("No se puede guardar"+e);
                //m.setGraphic(new ImageView(new Image("iconos/area.png")));
                m.show();
                System.out.println(e);
            }
    }
    
    private void controlDATOS(){
        if(isEmpty()){
            Alert msg=new Alert(Alert.AlertType.INFORMATION);
            msg.setTitle("Los campos no pueden estar vacios");
            msg.setContentText("Favor de llenar los datos");
            //msg.setGraphic(new ImageView(new Image("iconos/area.png")));
            msg.show();
        }else{
            //guardar();
            //actualizar inicio
        }
    }
    
    private boolean isEmpty(){
        return codigo.getText().equals("") || nombre.getText().equals("") || descripcion.getText().equals("") || precio_compra.getText().equals("") || precio_venta.getText().equals("") || existencia.getText().equals("");
    }
    
    private void limpieza(){
        codigo.setText("");
        nombre.setText("");
        descripcion.setText("");
        precio_compra.setText("");
        precio_venta.setText("");
        existencia.setText("");
    }
}