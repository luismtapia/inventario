package historial;

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
import ventas.VistaVenta;

public class HistorialController implements Initializable{
    private final SQL conn=new SQL();
    SQL_Consultar con_sql = new SQL_Consultar();
    String bandera = "";
    @FXML private Button btn_producto,btn_cliente,btn_fecha,btn_buscar,btn_detalles;
    @FXML private Label lbl1;
    @FXML private TextField txt_producto,txt_cliente;
    @FXML private ListView lista1;
    @FXML private DatePicker fecha1,fecha2;
    @FXML
    private void handleBotones(ActionEvent event) throws SQLException{
            if(event.getSource() == btn_cliente){
                txt_cliente.setVisible(true);
                txt_producto.setVisible(false);
                fecha1.setVisible(false);
                fecha2.setVisible(false);
                btn_buscar.setVisible(true);
                bandera = "cliente";
            }else
                if(event.getSource() == btn_producto){
                    txt_cliente.setVisible(false);
                    txt_producto.setVisible(true);
                    fecha1.setVisible(false);
                    fecha2.setVisible(false);
                    btn_buscar.setVisible(true);
                    bandera = "producto";
                }else
                    if(event.getSource() == btn_fecha){
                        txt_cliente.setVisible(false);
                        txt_producto.setVisible(false);
                        fecha1.setVisible(true);
                        fecha2.setVisible(true);
                        btn_buscar.setVisible(true);
                        bandera = "fecha";
                    }else
                        if(event.getSource() == btn_buscar){
                            System.out.println(bandera);
                            if(bandera.equals("cliente")){
                                por_cliente(txt_cliente.getText());
                            }else
                                if(bandera.equals("producto")){
                                    por_producto(txt_producto.getText());
                                }
                        }else
                            if(event.getSource() == btn_detalles){
                                List<VistaHistorial> sval = lista1.getSelectionModel().getSelectedItems();
                                for(int i=0;i<sval.size();i++){
                                    VistaHistorial seleccion = sval.get(i);
                                    Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
                                    mensaje.setTitle(seleccion.getNombre());
                                    mensaje.setHeaderText("Numero de Ticket: "+seleccion.getNo_ticket()
                                    + "\n\t\tTotal: $\t"+seleccion.getTotal_a_pagar());
                                    mensaje.setContentText("\nFecha de Venta: "+seleccion.getFecha_venta()
                                    + "\nDescripcion: "+seleccion.getDescripcion()
                                    + "\nCantidad: "+seleccion.getCantidad());
                                    //mensaje.setGraphic(new ImageView(new Image("iconos/usuario.png")));
                                    mensaje.show();
                                }
                            }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        conn.Conectar();
        color(Color.web("#D4AF37"));
        inicio();
        txt_cliente.setPromptText("Cliente");
        txt_producto.setPromptText("Producto");
        txt_cliente.setVisible(false);
        txt_producto.setVisible(false);
        fecha1.setVisible(false);
        fecha2.setVisible(false);
        btn_buscar.setVisible(false);
        fecha1.setValue(LocalDate.now());
        fecha2.setValue(LocalDate.now());
        //imagenes();
    }
    
    private void color(Color color){
        btn_cliente.setTextFill(color);
        btn_producto.setTextFill(color);
        btn_fecha.setTextFill(color);
        btn_buscar.setTextFill(color);
        btn_detalles.setTextFill(color);
        lbl1.setTextFill(color);
    }
    
    private void imagenes(){
//        btn_depto_1.setGraphic(new ImageView(new Image("iconos/ropa.png")));
//        btn_depto_1.setContentDisplay(ContentDisplay.BOTTOM);
//        btn_depto_2.setGraphic(new ImageView(new Image("iconos/accesorios.png")));
//        btn_depto_2.setContentDisplay(ContentDisplay.BOTTOM);
    }
    
    private void inicio(){
        lista1.getItems().clear();
        ResultSet rs = con_sql.get_SQL("select * from ventas v inner join clientes c on v.id_cliente = c.id_cliente inner join venta_es_de_producto vp on vp.no_ticket=v.no_ticket inner join productos p on p.codigo=vp.codigo;");
        try {
            while (rs.next()) {
                VistaHistorial nota = new VistaHistorial(rs.getInt("no_ticket"), rs.getDate("fecha_venta"), rs.getInt("id_cliente"), rs.getDouble("total_a_pagar"), rs.getString("codigo"), rs.getInt("cantidad"), rs.getDouble("precio_venta"), rs.getString("nombre"), rs.getString("descripcion"), rs.getDouble("precio_compra"), rs.getInt("existencia"), rs.getString("nombre_cliente"));
                    lista1.getItems().add(nota);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    private void por_producto(String like){
        lista1.getItems().clear();
        //ResultSet rs = con_sql.get_SQL("select * from ventas where no_ticket in ( select no_ticket from venta_es_de_producto where codigo in (select codigo from productos where nombre = '"+like+"'))");
        ResultSet rs = con_sql.get_SQL("select * from ventas v inner join venta_es_de_producto vp on v.no_ticket=vp.no_ticket inner join productos p on vp.codigo=p.codigo where nombre = '"+like+"'");
        
        try {
            while (rs.next()) {                
                VistaHistorial nota = new VistaHistorial(rs.getInt("no_ticket"), rs.getDate("fecha_venta"), rs.getInt("id_cliente"), rs.getDouble("total_a_pagar"), rs.getString("codigo"), rs.getInt("cantidad"), rs.getDouble("precio_venta"), rs.getString("nombre"), rs.getString("descripcion"), rs.getDouble("precio_compra"), rs.getInt("existencia"), rs.getString("nombre_cliente"));
                    lista1.getItems().add(nota);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    private void por_cliente(String like){
        lista1.getItems().clear();
        ResultSet rs = con_sql.get_SQL("select * from ventas v inner join clientes c on v.id_cliente = c.id_cliente inner join venta_es_de_producto vp on vp.no_ticket=v.no_ticket inner join productos p on p.codigo=vp.codigo where nombre_cliente = '"+like+"'");
        try {
            while (rs.next()) {                
                VistaHistorial nota = new VistaHistorial(rs.getInt("no_ticket"), rs.getDate("fecha_venta"), rs.getInt("id_cliente"), rs.getDouble("total_a_pagar"), rs.getString("codigo"), rs.getInt("cantidad"), rs.getDouble("precio_venta"), rs.getString("nombre"), rs.getString("descripcion"), rs.getDouble("precio_compra"), rs.getInt("existencia"), rs.getString("nombre_cliente"));
                    lista1.getItems().add(nota);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    
}