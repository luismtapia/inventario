package ventas;

import inventariogui.SQL_Consultar;
import inventariogui.SQL_Crear;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class VentasController implements Initializable{
    SQL_Consultar con_sql=new SQL_Consultar();
    SQL_Crear con_sql_crear = new SQL_Crear();
    
    @FXML private TextField bandera_nombre,cliente,lbl_cantidad;
    @FXML private Button btn_buscar,btn_marcar,btn_cobrar,btn_quitar,btn_historial;
    @FXML private ComboBox cmb_encontrados;
    @FXML private Label lbl1,lbl2,lbl3,lbl4,lbl5,lbl6,lbl7,lbl8,lbl9,lbl10,lbl11,lbl12,lbl13,lbl14,lbl18,lbl19,lbl_folio;
    @FXML private Label lbl_codigo,lbl_nombre,lbl_precio,lbl_existencia,lbl_descripcion,lbl_total,lbl_ganancia,lbl_precio_compra;
    @FXML private ListView lista1;
    @FXML private ToggleGroup myToggleGroup;
    @FXML private RadioButton radio1,radio2;
    
    @FXML
    private void handleKeyPressed(KeyEvent ke){
        llenarcombobox(bandera_nombre.getText());
        busqueda(bandera_nombre.getText());
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
                            }else
                                if(event.getSource() == btn_historial){
                                    Historial();
                                }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb){
        color(Color.web("#D4AF37"));
        bandera_nombre.setPromptText("Nombre de Producto");
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
                busqueda(seleccion.getNombre());
                con_sql.existencia(seleccion.getCantidad() + Integer.parseInt(lbl_existencia.getText()), seleccion.getCodigo());
                
                double pre = seleccion.getPrecio_venta();
                int can = seleccion.getCantidad();
                double tot= pre*can;
                double ant = Double.parseDouble(lbl_total.getText());
                tot = ant - tot;
                lbl_total.setText(String.valueOf(tot));
                
                lbl_ganancia.setText("0.0");
                busqueda(seleccion.getNombre());
                lista1.getItems().remove(seleccion);
            }
        } catch (Exception e) {System.out.println(""+e);
        }
    }
    
    private String ultimocliente(){
        String client="";
        ResultSet rs = con_sql.get_SQL("select MAX(id_cliente) as ultimo from clientes");
        try {
            while (rs.next()) {                
                client = rs.getString("ultimo");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return client;
    }
    
    private void cobrar(){
        try {
            String client;
            //insert en ventas
            if(cliente.getText().equals("")){
                con_sql_crear.insertarCliente("anónimo");
                client = ultimocliente();
                con_sql_crear.insertarVenta(client, Double.parseDouble(lbl_total.getText()));
            }else{
                con_sql_crear.insertarCliente(cliente.getText());
                client = ultimocliente();
                con_sql_crear.insertarVenta(client, Double.parseDouble(lbl_total.getText()));
            }
            //luego recorrer e inertatr en ventas en productos
            List<VistaVenta> sval = lista1.getItems();
            for (VistaVenta recorrido : sval) {
                con_sql_crear.insertarVenta_es_de_producto(Integer.parseInt(lbl_folio.getText()), recorrido.getCodigo(), recorrido.getCantidad(), recorrido.getPrecio_venta());
            }
            double total_ganado;
            total_ganado = getEfectivo() + Double.parseDouble(lbl_total.getText());
            
            con_sql.get_SQL("update banco set efectivo = '"+total_ganado+"'");
            double utiliddes = getUtilidades();
            utiliddes = utiliddes + Double.parseDouble(lbl_ganancia.getText());
            con_sql.get_SQL("update banco set utilidades_retenidas = '"+utiliddes+"'");
                Alert m = new Alert(Alert.AlertType.INFORMATION);
                m.setTitle("compra realizada");
                m.setContentText("gracias por comprar");
                //m.setGraphic(new ImageView(new Image("iconos/usuario.png")));
                m.show();
            
            lista1.getItems().clear();
            lbl_ganancia.setText("0.0");
            lbl_total.setText("0.0");
            ticket();
            limpiar();
        } catch (Exception e) {System.out.println("error al cobrar"+e);}
            
    }
    
    private double getEfectivo(){
        double efe=0.0;
        ResultSet rs = con_sql.get_SQL("select * from banco");
        try {
            while (rs.next()) {                
                efe = Double.parseDouble(rs.getString("efectivo"));
            }
        } catch (SQLException | NumberFormatException e) {
            System.out.println(e);
        }
        return efe;
    }
    
    private double getUtilidades(){
        double uti=0.0;
        ResultSet rs = con_sql.get_SQL("select * from banco");
        try {
            while (rs.next()) {                
                uti = Double.parseDouble(rs.getString("utilidades_retenidas"));
            }
        } catch (SQLException | NumberFormatException e) {
            System.out.println(e);
        }
        return uti;
    }
    
    private void elegir(){
        String combo = cmb_encontrados.getSelectionModel().getSelectedItem().toString();
        String substring = combo.substring(0,13);
        //System.out.println(substring);
        busqueda_codigo(substring);
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
        cmb_encontrados.setPromptText("Sin resultados");
        ResultSet rs = con_sql.get_SQL("select * from productos where nombre like '"+like+"%'");
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
        lbl18.setTextFill(color);
        lbl19.setTextFill(color);
        btn_historial.setTextFill(color);
        btn_buscar.setTextFill(color);
        btn_marcar.setTextFill(color);
        btn_cobrar.setTextFill(color);
        btn_quitar.setTextFill(color);
        lbl_codigo.setTextFill(color);
        lbl_nombre.setTextFill(color);
        lbl_precio.setTextFill(color);
        lbl_existencia.setTextFill(color);
        lbl_descripcion.setTextFill(color);
        lbl_folio.setTextFill(color);
        lbl_total.setTextFill(color);
        lbl_ganancia.setTextFill(color);
    }
    
    private void imagenes(){
//        btn_depto_1.setGraphic(new ImageView(new Image("iconos/ropa.png")));
//        btn_depto_1.setContentDisplay(ContentDisplay.BOTTOM);
//        btn_depto_2.setGraphic(new ImageView(new Image("iconos/accesorios.png")));
//        btn_depto_2.setContentDisplay(ContentDisplay.BOTTOM);
    }
    
    private void busqueda(String like){
        boolean interruptor=false;
        ResultSet rs = con_sql.get_SQL("select * from productos where nombre like '"+like+"%'");
        try {
            while (rs.next()) {                
                lbl_codigo.setText(rs.getString("codigo"));
                lbl_nombre.setText(rs.getString("nombre"));
                lbl_descripcion.setText(rs.getString("descripcion"));
                lbl_precio.setText(""+Double.parseDouble(rs.getString("precio_venta")));
                lbl_existencia.setText(""+rs.getInt("existencia"));
                lbl_precio_compra.setText(""+rs.getDouble("precio_compra"));
                interruptor=true;
            }
            if(!interruptor){
                limpiar();
            }
        } catch (SQLException | NumberFormatException e) {
            System.out.println(e);
        }
    }
    
    private void busqueda_codigo(String like){
        ResultSet rs = con_sql.get_SQL("select * from productos where codigo like '"+like+"%'");
        try {
            while (rs.next()) {                
                lbl_codigo.setText(rs.getString("codigo"));
                lbl_nombre.setText(rs.getString("nombre"));
                lbl_descripcion.setText(rs.getString("descripcion"));
                lbl_precio.setText(""+Double.parseDouble(rs.getString("precio_venta")));
                lbl_existencia.setText(""+rs.getInt("existencia"));
                lbl_precio_compra.setText(""+rs.getDouble("precio_compra"));
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
        String bandera = "no";
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
                busqueda(lbl_nombre.getText());
                
                List<VistaVenta> sval = lista1.getItems();
                for (VistaVenta recorrido : sval) {
                    if(recorrido.getCodigo().equals(lbl_codigo.getText())){
                        bandera = "si";
                        int cantidadN = Integer.parseInt(lbl_cantidad.getText()) + recorrido.getCantidad();
                        VistaVenta nota = new VistaVenta(lbl_codigo.getText(), lbl_nombre.getText(), lbl_descripcion.getText(),  lbl_folio.getText(), Double.parseDouble(lbl_precio.getText()), cantidadN);
                        lista1.getItems().add(nota);
                        lista1.getItems().remove(recorrido);
                    }
                }
                if(bandera.equals("no")){
                    VistaVenta nota = new VistaVenta(lbl_codigo.getText(), lbl_nombre.getText(), lbl_descripcion.getText(),  lbl_folio.getText(), Double.parseDouble(lbl_precio.getText()), Integer.parseInt(lbl_cantidad.getText()));
                    lista1.getItems().add(nota);
                }
                
                double pre = Double.parseDouble(lbl_precio.getText());
                double compra = Double.parseDouble(lbl_precio_compra.getText());
                int can = Integer.parseInt(lbl_cantidad.getText());
                double tot= pre*can;
                double ganancia = pre - compra;
                double ant = Double.parseDouble(lbl_total.getText());
                double anterior_ganancia = Double.parseDouble(lbl_ganancia.getText());
                tot = tot + ant;
                ganancia = ganancia * can;
                ganancia = ganancia + anterior_ganancia;
                lbl_total.setText(String.valueOf(tot));
                lbl_ganancia.setText(String.valueOf(ganancia));
            }
            
        } catch (Exception e) {System.out.println(""+e);
        }
    }
    
    private void Historial(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/historial/Historial.fxml"));
            StackPane pane = new StackPane();
            
            //Coloca una imagen de fondo
//            Image url = new Image("imagenes/54.gif");
//            ImageView image = new ImageView();
//            image.setFitWidth(900);
//            image.setFitHeight(700);
//            image.setImage(url);
            
            pane.getChildren().addAll(/*image,*/root);
            Scene scene = new Scene(pane, 900, 600, Color.BLUE);
            Stage stage = new Stage();
            stage.setScene(scene);
            //stageActualizar.getIcons().add(new Image("iconos/IconosEmpleado/actualizar_empleado.png"));
            stage.setTitle("Historial");
            //stage.setFullScreen(true);
            stage.show();
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    private void limpiar(){
        lbl_codigo.setText("");
        lbl_nombre.setText("");
        lbl_descripcion.setText("");
        lbl_precio.setText("");
        lbl_existencia.setText("");
        lbl_precio_compra.setText("");
    }
}