package ec.edu.espol.grupo_10_iiparcial;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javafx.scene.input.MouseEvent;
import javafx.stage.StageStyle;

/**
 * JavaFX App
 */
public class App extends Application {

    private double xOffset = 0;
    private double yOffset = 0;
    
    private static Scene scene;
    private static Stage st;


    @Override
    public void start(Stage stage) throws IOException {
        st = stage;
        scene = new Scene(loadFXML("dashboard").load(), 700, 600);
        stage.initStyle(StageStyle.UNDECORATED);
        
        scene.setOnMousePressed((MouseEvent event) -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        
        scene.setOnMouseDragged((MouseEvent event) -> {
            stage.setX(event.getScreenX()- xOffset);
            stage.setY(event.getScreenY()- yOffset);
        });
        
        stage.setScene(scene);
        stage.show();
    }
        
    public static Stage getStage(){
        return st;
    }
    
    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml).load());
    }
    
    public static FXMLLoader loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader;
    }
    public static void setScene(Scene sc) throws IOException {
        st.setScene(sc);
    }

    public static void main(String[] args) {
        HashMap<String, List<String>> respuestas = new HashMap<String, List<String>>();
        String[] animales = {"Oso", "Lechuza", "Venado", "Paloma", "Goku"};
        String[][] respSiNo = {{"si", "si" ,"si"}, {"no", "si" ,"no"}, {"si", "no" ,"si"}, {"no", "no" ,"no"}, {"no", "si" ,"si"}};
        for(int i = 0; i < 5; i++){
            respuestas.put(animales[i], new ArrayList<>());
            for(int j = 0; j < 3; j++){
                respuestas.get(animales[i]).add(respSiNo[i][j]);
            }
            System.out.println(animales[i]);
            System.out.println(respuestas.get(animales[i]));
        }
        
        ArrayList<String> preguntas = new ArrayList<>();
        String[] arregloPreguntas = {"¿Es este animal un mamífero?", "¿Es este animal un carnívoro?", "¿Se para este animal en 4 patas?"};
        for(String pregunta : arregloPreguntas){
            preguntas.add(pregunta);
        }
        
        ArbolBinario arbol = new ArbolBinario<String>(preguntas.get(0));
        CrearArbol.crearArbolDeDecisiones(arbol, preguntas, 1);
        CrearArbol.añadirAnimales(arbol, respuestas);
        System.out.println(arbol.recorridoPreOrden());
        
        List<String> respuestasUsuario = new ArrayList<>();
        respuestasUsuario.add("si");
        respuestasUsuario.add("no");
        respuestasUsuario.add("si");
        List<String> posiblesRespuestas = arbol.respuestasPorRecorrido(respuestasUsuario);
        for(String posRep : posiblesRespuestas){
            System.out.println(posRep);
        }
        launch();
    }

}