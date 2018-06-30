package productos;

import inventariogui.SQL;
import inventariogui.SQL_Consultar;
import inventariogui.SQL_Crear;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

public class ProductosController implements Initializable{
    private final SQL conn=new SQL();
    SQL_Crear con_sql = new SQL_Crear();
    SQL_Consultar con_sql1=new SQL_Consultar();
    
    @FXML private Button btn_guardar,btn_cancelar,btn_actualizar;
    @FXML private Label lbl1,lbl2,lbl3,lbl4,lbl5,lbl6,lbl7;
    @FXML private TextField codigo,nombre,descripcion,precio_compra,precio_venta,existencia;
    @FXML private Label lbl8,lbl9,lbl10,lbl11,lbl12,lbl13;
    @FXML private Label codigo_,nombre_,descripcion_,precio_compra_,precio_venta_;
    @FXML private TextField existencia_;
    
    @FXML
    private void handleKeyPressed(KeyEvent ke){
        String buscar;
        buscar= codigo.getText();
        busqueda(buscar);
        //System.out.println("Key Pressed: " + ke.getCode());
    }
    
    @FXML
    private void handleBotones(ActionEvent event) throws SQLException{
            if(event.getSource() == btn_cancelar){
                limpieza();
            }else
                if(event.getSource() == btn_guardar){
                    controlDATOS();
                }else
                    if(event.getSource() == btn_actualizar){
                        actualizar();
                    }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        conn.Conectar();
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
        btn_actualizar.setTextFill(color);
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
        lbl12.setTextFill(color);
        lbl13.setTextFill(color);
        codigo_.setTextFill(color);
        nombre_.setTextFill(color);
        descripcion_.setTextFill(color);
        precio_compra_.setTextFill(color);
        precio_venta_.setTextFill(color);
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
            guardar();
            limpieza();
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
    
    private void busqueda(String like){
        ResultSet rs = con_sql1.get_SQL("select * from productos where codigo like '"+like+"%'");
        try {
            while (rs.next()) {
                codigo_.setText(rs.getString("codigo"));
                nombre_.setText(rs.getString("nombre"));
                descripcion_.setText(rs.getString("descripcion"));
                precio_compra_.setText(""+Double.parseDouble(rs.getString("precio_compra")));
                precio_venta_.setText(""+Double.parseDouble(rs.getString("precio_venta")));
                existencia_.setText(""+rs.getInt("existencia"));
            }
        } catch (SQLException | NumberFormatException e) {System.out.println(e);}
    }
    
    private void actualizar(){
        Alert msg=new Alert(Alert.AlertType.CONFIRMATION);
        msg.setTitle("Vas a actualizar los datos");
        msg.setContentText("estas seguro");
        //msg.setGraphic(new ImageView(new Image("iconos/area.png")));
        Optional<ButtonType> result = msg.showAndWait();
        if (result.get() == ButtonType.OK){
            con_sql1.existencia(Integer.parseInt(existencia_.getText()), codigo_.getText());
        } else {
            // ... user chose CANCEL or closed the dialog
        }
    }
}