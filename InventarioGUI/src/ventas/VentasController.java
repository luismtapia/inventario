package ventas;

import inventariogui.SQL_Consultar;
import javafx.scene.input.KeyEvent;
import productos.*;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
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
import javafx.scene.control.ToggleGroup;

public class VentasController implements Initializable{
    SQL_Consultar con_sql=new SQL_Consultar();
    
    @FXML private TextField bandera,cliente,lbl_cantidad;
    @FXML private Button btn_buscar,btn_marcar,btn_cobrar,btn_quitar;
    @FXML private ComboBox cmb_encontrados;
    @FXML private Label lbl1,lbl2,lbl3,lbl4,lbl5,lbl6,lbl7,lbl8,lbl9,lbl10,lbl11,lbl12,lbl13,lbl14,lbl_folio;
    @FXML private Label lbl_codigo,lbl_nombre,lbl_precio,lbl_existencia,lbl_descripcion;
    @FXML private ListView lista1;
    @FXML private ToggleGroup myToggleGroup;
    @FXML private RadioButton radio1,radio2;
    
    @FXML
    private void handleKeyPressed(KeyEvent ke){
        String buscar;
        buscar=bandera.getText();
        llenarcombobox(buscar);
        
        //lbl_codigo.setText(buscar);
        busqueda(buscar);
        //System.out.println("Key Pressed: " + ke.getCode());
    }
    
    @FXML
    private void handleBotones(ActionEvent event) throws SQLException{
        if(event.getSource() == btn_marcar){
            marcar();
        }else
            if(event.getSource() == btn_buscar){
                elegir();
            }else
                if(event.getSource() == radio1){
                    cliente.setVisible(false);
                    lbl8.setVisible(false);
                }else
                    if(event.getSource() == radio2){
                        cliente.setVisible(true);
                        lbl8.setVisible(true);
                    }else
                        if(event.getSource() == btn_cobrar){
                            cobrar();
                        }else
                            if(event.getSource() == btn_quitar){
                                cancelarProducto();
                            }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb){
        color(Color.web("#D4AF37"));
        bandera.setPromptText("Producto codigo/descripcion");
        cliente.setPromptText("Nombre del cliente");
        llenarcomboboxTODOS();
        cliente.setVisible(false);
        lbl8.setVisible(false);
        //imagenes();
        ticket();
        //al boton cobrar
        if(radio1.isSelected()){
            System.out.println("listo");
        }
        //myToggleGroup.selectToggle(value);
    }
    
    private void cancelarProducto(){
        try {
            List<VistaVenta> sval = lista1.getSelectionModel().getSelectedItems();
            for(int i=0;i<sval.size();i++){
                VistaVenta seleccion = sval.get(i);
                busqueda(seleccion.getCodigo());
                con_sql.existencia(seleccion.getCantidad()+ Integer.parseInt(lbl_existencia.getText()), seleccion.getCodigo());
                busqueda(seleccion.getCodigo());
                lista1.getItems().remove(seleccion);
            }
        } catch (Exception e) {System.out.println(""+e);
        }
    }
    
    private void cobrar(){
        //insert en ventas
       
        //luego recorrer e inertatr en ventas en productos
        List<VistaVenta> sval = lista1.getItems();
        for (VistaVenta recorrido : sval) {
            System.out.println(recorrido.getCodigo());
            System.out.println(recorrido.getNo_ticket());
            
        }
    }
    
    private void elegir(){
        String combo = cmb_encontrados.getSelectionModel().getSelectedItem().toString();
        String substring = combo.substring(0,13);
        //System.out.println(substring);
        busqueda(substring);
    }
    
    private void llenarcomboboxTODOS(){
        cmb_encontrados.getItems().clear();
        ResultSet rs = con_sql.get_SQL("select * from productos");
        try {
            while (rs.next()) {                
                cmb_encontrados.getItems().add(rs.getString("codigo")+"\t"+rs.getString("nombre"));
                cmb_encontrados.setPromptText(rs.getString("codigo")+"\t"+rs.getString("nombre"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    private void llenarcombobox(String like){
        cmb_encontrados.getItems().clear();
        cmb_encontrados.setPromptText("PRODUCTOS");
        ResultSet rs = con_sql.get_SQL("select * from productos where codigo like '"+like+"%'");
        try {
            while (rs.next()) {                
                cmb_encontrados.getItems().add(rs.getString("codigo")+"\t"+rs.getString("nombre"));
                cmb_encontrados.setPromptText(rs.getString("codigo")+"\t"+rs.getString("nombre"));
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
        lbl12.setTextFill(color);
        lbl13.setTextFill(color);
        lbl14.setTextFill(color);
        btn_buscar.setTextFill(color);
        btn_marcar.setTextFill(color);
        btn_cobrar.setTextFill(color);
        lbl_codigo.setTextFill(color);
        lbl_nombre.setTextFill(color);
        lbl_precio.setTextFill(color);
        lbl_existencia.setTextFill(color);
        lbl_descripcion.setTextFill(color);
        lbl_folio.setTextFill(color);
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
                lbl_descripcion.setText(rs.getString("descripcion"));
                lbl_precio.setText(""+Double.parseDouble(rs.getString("precio_venta")));
                lbl_existencia.setText(""+rs.getInt("existencia"));
            }
        } catch (SQLException | NumberFormatException e) {
            System.out.println(e);
        }
    }
    
    private void ticket(){
        ResultSet rs = con_sql.get_SQL("select isnull(max(no_ticket),0) + 1 as folio from ventas");
        try {
            while (rs.next()) {                
                lbl_folio.setText(rs.getString("folio"));
            }
        } catch (SQLException | NumberFormatException e) {System.out.println(e);}
    }
    
    private void marcar(){
        try {
            //existecia excede
            if(Integer.parseInt(lbl_cantidad.getText()) > Integer.parseInt(lbl_existencia.getText())){
                Alert m = new Alert(Alert.AlertType.INFORMATION);
                m.setTitle("existencia");
                m.setContentText("no se puede procesar compra no se puede vender mas de lo que tienes");
                //m.setGraphic(new ImageView(new Image("iconos/usuario.png")));
                m.show();
            }else{
                int nueva_existencia = Integer.parseInt(lbl_existencia.getText()) - Integer.parseInt(lbl_cantidad.getText());
                con_sql.existencia(nueva_existencia,lbl_codigo.getText());
                busqueda(lbl_codigo.getText());
                VistaVenta nota = new VistaVenta(lbl_codigo.getText(), lbl_nombre.getText(), lbl_descripcion.getText(),  lbl_folio.getText(), Double.parseDouble(lbl_precio.getText()), Integer.parseInt(lbl_cantidad.getText()));
                lista1.getItems().add(nota);
            }
            
        } catch (Exception e) {System.out.println(""+e);
        }
    }
}