package inventario;

import historial.*;
import productos.*;
import inventariogui.SQL;
import inventariogui.SQL_Consultar;
import inventariogui.SQL_Crear;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class InventarioController implements Initializable{
    private final SQL conn=new SQL();
    SQL_Consultar con_sql = new SQL_Consultar();
    String bandera = "";
    @FXML private Button btn_codigo,btn_nombre,btn_todos,btn_buscar, btn_orden_codigo,btn_orden_nombre;
    @FXML private Label lbl1,lbl2;
    @FXML private TextField txt_codigo,txt_nombre;
    @FXML private ListView lista1;
    @FXML
    private void handleBotones(ActionEvent event) throws SQLException{
            if(event.getSource() == btn_nombre){
                txt_nombre.setVisible(true);
                txt_codigo.setVisible(false);
                btn_buscar.setVisible(true);
                bandera = "nombre";
            }else
                if(event.getSource() == btn_codigo){
                    txt_nombre.setVisible(false);
                    txt_codigo.setVisible(true);
                    btn_buscar.setVisible(true);
                    bandera = "codigo";
                }else
                    if(event.getSource() == btn_todos){
                        txt_nombre.setVisible(false);
                        txt_codigo.setVisible(false);
                        btn_buscar.setVisible(false);
                        inicio("codigo");
                        bandera = "todos";
                    }else
                        if(event.getSource() == btn_buscar){
                            System.out.println(bandera);
                            if(bandera.equals("nombre")){
                                busqueda(txt_nombre.getText(), bandera);
                            }else
                                if(bandera.equals("codigo")){
                                    busqueda(txt_codigo.getText(), bandera);
                                }
                        }else
                            if(event.getSource() == btn_orden_codigo){
                                if(bandera.equals("codigo")){
                                   ordenar_por(txt_codigo.getText(), bandera, "codigo");
                                }else
                                    if(bandera.equals("nombre")){
                                        ordenar_por(txt_nombre.getText(), bandera, "codigo");
                                    }else
                                        inicio("codigo");
                                
                            }else
                                if(event.getSource() == btn_orden_nombre){
                                    if(bandera.equals("codigo")){
                                        ordenar_por(txt_codigo.getText(), bandera, "nombre");
                                     }else
                                         if(bandera.equals("nombre")){
                                             ordenar_por(txt_nombre.getText(), bandera, "nombre");
                                         }else
                                             inicio("nombre");
                                }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb){
        conn.Conectar();
        color(Color.web("#D4AF37"));
        inicio("codigo");
        txt_nombre.setPromptText("Nombre del producto");
        txt_codigo.setPromptText("Codigo del producto");
        txt_nombre.setVisible(false);
        txt_codigo.setVisible(false);
        btn_buscar.setVisible(false);
        //imagenes();
    }

    private void color(Color color){
        btn_nombre.setTextFill(color);
        btn_codigo.setTextFill(color);
        btn_buscar.setTextFill(color);
        btn_orden_codigo.setTextFill(color);
        btn_orden_nombre.setTextFill(color);
        btn_todos.setTextFill(color);
        lbl1.setTextFill(color);
        lbl2.setTextFill(color);
    }

    private void imagenes(){
//        btn_depto_1.setGraphic(new ImageView(new Image("iconos/ropa.png")));
//        btn_depto_1.setContentDisplay(ContentDisplay.BOTTOM);
//        btn_depto_2.setGraphic(new ImageView(new Image("iconos/accesorios.png")));
//        btn_depto_2.setContentDisplay(ContentDisplay.BOTTOM);
    }

    private void inicio(String orden){
        lista1.getItems().clear();
        ResultSet rs = con_sql.get_SQL("select * from productos order by "+orden);
        try {
            while (rs.next()) {
                Producto nota = new Producto(rs.getString("codigo"), rs.getString("nombre"), rs.getString("descripcion"), rs.getDouble("precio_compra"), rs.getDouble("precio_venta"), rs.getInt("existencia"), rs.getDouble("total_invertido"), rs.getDouble("ganancia_esperada"));
                lista1.getItems().add(nota);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void busqueda(String like, String tabla){
        lista1.getItems().clear();
        ResultSet rs = con_sql.get_SQL("select * from productos where "+tabla+" like '"+like+"%'");

        try {
            while (rs.next()) {
                Producto nota = new Producto(rs.getString("codigo"), rs.getString("nombre"), rs.getString("descripcion"), rs.getDouble("precio_compra"), rs.getDouble("precio_venta"), rs.getInt("existencia"), rs.getDouble("total_invertido"), rs.getDouble("ganancia_esperada"));
                lista1.getItems().add(nota);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void ordenar_por(String like, String tabla, String orden){
        lista1.getItems().clear();
        ResultSet rs = con_sql.get_SQL("select * from productos where "+tabla+" like '"+like+"%' order by "+orden);
        try {
            while (rs.next()) {
                Producto nota = new Producto(rs.getString("codigo"), rs.getString("nombre"), rs.getString("descripcion"), rs.getDouble("precio_compra"), rs.getDouble("precio_venta"), rs.getInt("existencia"), rs.getDouble("total_invertido"), rs.getDouble("ganancia_esperada"));
                lista1.getItems().add(nota);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
