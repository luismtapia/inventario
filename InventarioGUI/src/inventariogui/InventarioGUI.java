

package inventariogui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author luis
 */
public class InventarioGUI extends Application {
    private Stage estacionPrimaria= new Stage();
    private final SQL conn=new SQL();
    public BorderPane border;
    
    @Override
    public void start(Stage primaryStage) {
        estacionPrimaria = primaryStage;
        
        initGUI();
    }
    
    @SuppressWarnings("empty-statement")
    private void initGUI(){
        StackPane root = new StackPane();
        Scene scene;
        //inicializaBaseDatos();
       
        
        root.setStyle("-fx-background-color: #000000");
        root.getChildren().addAll(generaEncabezado("Luis"));
        scene = new Scene(root, 1400, 700);
        estacionPrimaria.setTitle("El Guardarropa de Fernanda");
        //estacionPrimaria.getIcons().add(new Image("iconos/logo.png"));
        estacionPrimaria.setResizable(false);
        estacionPrimaria.setScene(scene);
        estacionPrimaria.show();
    }
    
    //ENCABEZADO
    public VBox generaEncabezado(String usuario){
        VBox Hbox_ENCABEZADO = new VBox();
        Hbox_ENCABEZADO.setPadding(new Insets(10, 30, 10, 100));
        
        Label nombre_usuario = new Label(usuario);//, new ImageView(new Image("iconos/usuario.png",16,16,true,true,true)));
        nombre_usuario.setTextFill(Color.web("#D4AF37"));
        nombre_usuario.setPrefSize(250, 20);
        
        border = new BorderPane();
        try {
            Parent rooot = FXMLLoader.load(getClass().getResource("/inicio/Inicio.fxml"));
            border.setCenter(rooot);
        } catch (Exception e) {System.err.println(""+e);}
        
        
        Hbox_ENCABEZADO.getChildren().addAll(nombre_usuario,border);
        
        return Hbox_ENCABEZADO;
    }
    
    
    
    private void inicializaBaseDatos(){
        conn.Conectar();
    }
    
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
