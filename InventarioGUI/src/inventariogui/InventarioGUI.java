

package inventariogui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    private BorderPane border;

    Button btn_inicio,btn_ventas,btn_inventario,btn_apartado,btnagregar_productos,btn_activo,btn_salir;

    @Override
    public void start(Stage primaryStage) {
        estacionPrimaria = primaryStage;
        initGUI();
    }

    @SuppressWarnings("empty-statement")
    private void initGUI(){
        StackPane root = new StackPane();
        Scene scene;
        inicializaBaseDatos();

        root.setStyle("-fx-background-color: #000000;");
        root.getChildren().addAll(generaEncabezado("Luis"));
        scene = new Scene(root, 1370, 700);
        estacionPrimaria.setTitle("El Guardarropa de Fernanda");
        //estacionPrimaria.getIcons().add(new Image("iconos/logo.png"));
        estacionPrimaria.setResizable(false);
        estacionPrimaria.setScene(scene);
        estacionPrimaria.setFullScreen(true);
        estacionPrimaria.show();
    }

    public VBox generaEncabezado(String usuario){
        VBox vertical_inicio = new VBox();
        vertical_inicio.setPadding(new Insets(10, 30, 10, 30));
        
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
        //nombre_usuario.setTextFill(Color.web("#D4AF37"));
        nombre_usuario.setPrefSize(250, 20);
        
        btn_inicio = new Button("Inicio");
        btn_inicio.setPrefSize(150, 30);
        //btnagregar_productos.setContentDisplay(ContentDisplay.TOP);
        btn_inicio.setTextFill(Color.web("#D4AF37"));
        btn_inicio.setStyle("-fx-padding: 10 20 10 20;"
                + "-fx-background-color: linear-gradient(#262626,#000000);"
                + "-fx-font-size: 15pt;"
                + "-fx-border-color: #D4AF37;"
                + "-fx-border-style: solid inside;"
                + "-fx-border-width: 1;"
                + "-fx-border-radius: 3;");
        //btnagregar_productos.setStyle("-fx-background-color: transparent;");
        btn_inicio.setOnAction(handleBotones);
        
        btn_ventas = new Button("Ventas");
        btn_ventas.setPrefSize(150, 30);
        //btnagregar_productos.setContentDisplay(ContentDisplay.TOP);
        btn_ventas.setTextFill(Color.web("#D4AF37"));
        btn_ventas.setStyle(""
                + "-fx-background-color: linear-gradient(#262626,#000000);"
                + "-fx-font-size: 15pt;");
        //btnagregar_productos.setStyle("-fx-background-color: transparent;");
        btn_ventas.setOnAction(handleBotones);
        
        btn_inventario = new Button("Inventario");
        btn_inventario.setPrefSize(150, 30);
        //btnagregar_productos.setContentDisplay(ContentDisplay.TOP);
        btn_inventario.setTextFill(Color.web("#D4AF37"));
        btn_inventario.setStyle(""
                + "-fx-background-color: linear-gradient(#262626,#000000);"
                + "-fx-font-size: 15pt;"
                + "-fx-border-radius: 5;");
        //btnagregar_productos.setStyle("-fx-background-color: transparent;");
        btn_inventario.setOnAction(handleBotones);
        
        btnagregar_productos = new Button("Nuevo Producto");
        btnagregar_productos.setPrefSize(150, 30);
        //btnagregar_productos.setContentDisplay(ContentDisplay.TOP);
        btnagregar_productos.setTextFill(Color.web("#D4AF37"));
        btnagregar_productos.setStyle("-fx-background-color: linear-gradient(#262626,#000000);");
        //btnagregar_productos.setStyle("-fx-background-color: transparent;");
        btnagregar_productos.setOnAction(handleBotones);
        
        btn_activo = new Button("Activo fijo");
        btn_activo.setPrefSize(150, 30);
        //btnagregar_productos.setContentDisplay(ContentDisplay.TOP);
        btn_activo.setTextFill(Color.web("#D4AF37"));
        btn_activo.setStyle("-fx-background-color: linear-gradient(#262626,#000000);");
        //btnagregar_productos.setStyle("-fx-background-color: transparent;");
        btn_activo.setOnAction(handleBotones);
        
        btn_salir = new Button("Salir");
        btn_salir.setPrefSize(150, 30);
        //btnagregar_productos.setContentDisplay(ContentDisplay.TOP);
        btn_salir.setTextFill(Color.web("#D4AF37"));
        btn_salir.setStyle("-fx-background-color: linear-gradient(#262626,#000000);");
        //btnagregar_productos.setStyle("-fx-background-color: transparent;");
        btn_salir.setOnAction(handleBotones);
        
        


        topp.getChildren().addAll(nombre_usuario,btn_inicio,btn_ventas,btn_inventario,btnagregar_productos,btn_activo,btn_salir);
        return topp;
    }

    private void inicializaBaseDatos(){
        //probar coneccion
        conn.Conectar();
        //mensaje de alerta
        //conn.closeConnection();
        
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    final EventHandler<ActionEvent> handleBotones = new EventHandler<ActionEvent>() {
        @Override
        public void handle(final ActionEvent event) {
            Button x = (Button) event.getSource();
            switch (x.getText()) {
                case "Inicio":
                    try {
                        Parent root = FXMLLoader.load(getClass().getResource("/inicio/Inicio.fxml"));
                        //root.getStylesheets().add("estilo1.css");
                        border.setCenter(root);
                    } catch (Exception e) {System.err.println(""+e);}
                    break;
                case "Ventas":
                    try {
                        Parent root = FXMLLoader.load(getClass().getResource("/ventas/Ventas.fxml"));
                        border.setCenter(root);
                    } catch (Exception e) {System.err.println(""+e);}
                    break;
                case "Inventario":
                    try {
                        Parent root = FXMLLoader.load(getClass().getResource("/inventario/Inventario.fxml"));
                        border.setCenter(root);
                    } catch (Exception e) {System.err.println(""+e);}
                    break;
                case "Nuevo Producto":
                    try {
                        Nuevo_Producto();
                    } catch (Exception e) {System.err.println(""+e);}
                    break;
                case "Activo fijo":
                    try {
                        Activo_fijo();
                    } catch (Exception e) {System.err.println(""+e);}
                    break;
                case "Salir":
                    try {
                        System.exit(0);
                    } catch (Exception e) {System.err.println(""+e);}
                    break;
                default:
                    break;
            }
        }
    };
    
    private void Nuevo_Producto(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/productos/Productos.fxml"));
            StackPane pane = new StackPane();
            
            //Coloca una imagen de fondo
//            Image url = new Image("imagenes/54.gif");
//            ImageView image = new ImageView();
//            image.setFitWidth(900);
//            image.setFitHeight(700);
//            image.setImage(url);
            
            pane.getChildren().addAll(/*image,*/root);
            Scene sceneActualizar = new Scene(pane, 900, 600);
            Stage stageActualizar = new Stage();
            stageActualizar.setScene(sceneActualizar);
            //stageActualizar.getIcons().add(new Image("iconos/IconosEmpleado/actualizar_empleado.png"));
            stageActualizar.setTitle("Agregar Nuevo Producto");
            stageActualizar.show();
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    private void Activo_fijo(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/activofijo/Activos.fxml"));
            StackPane pane = new StackPane();
            
            //Coloca una imagen de fondo
//            Image url = new Image("imagenes/54.gif");
//            ImageView image = new ImageView();
//            image.setFitWidth(900);
//            image.setFitHeight(700);
//            image.setImage(url);
            
            pane.getChildren().addAll(/*image,*/root);
            Scene sceneActualizar = new Scene(pane, 900, 600);
            Stage stageActualizar = new Stage();
            stageActualizar.setScene(sceneActualizar);
            //stageActualizar.getIcons().add(new Image("iconos/IconosEmpleado/actualizar_empleado.png"));
            stageActualizar.setTitle("Activos");
            stageActualizar.show();
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
