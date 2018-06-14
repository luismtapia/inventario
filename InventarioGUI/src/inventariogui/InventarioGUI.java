

package inventariogui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    private BorderPane border;

    Button btnagregar_productos;

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
        VBox vertical_inicio = new VBox();
        vertical_inicio.setPadding(new Insets(20, 30, 10, 30));
        
        border = new BorderPane();
        try {
            Parent rooot = FXMLLoader.load(getClass().getResource("/inicio/Inicio.fxml"));
            border.setCenter(rooot);
        } catch (Exception e) {System.err.println(""+e);}


        vertical_inicio.getChildren().addAll(encabezado(usuario),border);

        return vertical_inicio;
    }
    
    private HBox encabezado(String usuario){
        HBox topp = new HBox();
        topp.setPadding(new Insets(20, 30, 10, 10));
        //AGREGAR BOTONES


        Label nombre_usuario = new Label(usuario);//, new ImageView(new Image("iconos/usuario.png",16,16,true,true,true)));
        nombre_usuario.setTextFill(Color.web("#D4AF37"));
        nombre_usuario.setPrefSize(250, 20);
        
        btnagregar_productos = new Button("Nuevo Producto");
        btnagregar_productos.setPrefSize(150, 30);
        //btnagregar_productos.setContentDisplay(ContentDisplay.TOP);
        btnagregar_productos.setTextFill(Color.web("#D4AF37"));
        btnagregar_productos.setStyle("-fx-background-color: transparent;");
        
        topp.getChildren().addAll(nombre_usuario,btnagregar_productos);
        return topp;
    }
    

    private void inicializaBaseDatos(){
        conn.Conectar();
    }


    public static void main(String[] args) {
        launch(args);
    }

}
