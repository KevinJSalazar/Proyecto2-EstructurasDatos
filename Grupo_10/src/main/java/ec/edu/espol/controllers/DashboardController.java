/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.controllers;

import ec.edu.espol.grupo_10_iiparcial.ArbolBinario;
import ec.edu.espol.grupo_10_iiparcial.CrearArbol;
import ec.edu.espol.util.Util;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author omits
 */
public class DashboardController implements Initializable {

    // Atributos privados
    private File imgFile;
    private MediaPlayer mediaPlayer;
    private int nQuestions;
    
    private HashMap<String, List<String>> respuestas;
    private List<String> arregloPreguntas;
    private ArrayList<String> preguntas;
    private ArbolBinario arbol;
    private List<String> respuestasUsuario;
    // 
    
    @FXML
    private AnchorPane MainScreen;
    @FXML
    private ImageView imvAnimalQuiz;
    @FXML
    private AnchorPane SecondScreen;
    @FXML
    private TextField txtNQuestions;
    @FXML
    private ImageView imvQuestions;
    @FXML
    private AnchorPane PlayScreen;
    @FXML
    private ImageView imvAnswers;
    @FXML
    private Label lblQuestions;
    @FXML
    private Button btnYes;
    @FXML
    private Button btnNo;
    @FXML
    private AnchorPane GameOverSection;

    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Util.mostrarImagen("logoQuiz.png", imgFile, imvAnimalQuiz);
        Util.mostrarImagen("logoQuestions.png", imgFile, imvQuestions);
        Util.mostrarImagen("logoAnswers.png", imgFile, imvAnswers);
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.dispose();
        }
        
//        respuestas= CrearArbol.leerRespuestas();
//        arregloPreguntas = CrearArbol.leerPreguntas();
//        preguntas = new ArrayList<>();
//        for(String pregunta : arregloPreguntas){
//            preguntas.add(pregunta);
//        }
//        
//        arbol = new ArbolBinario<String>(preguntas.get(0));
//        CrearArbol.crearArbolDeDecisiones(arbol, preguntas, 1);
//        CrearArbol.añadirAnimales(arbol, respuestas);
        respuestasUsuario = new ArrayList<>();
    }    

    @FXML
    private void fnClose(MouseEvent event) {
        if(Util.generarAlertaConfirmacion("SALIR", "¿Estás seguro de querer salir del juego?")){
            Stage stage = (Stage) ((Label) event.getSource()).getScene().getWindow();
            stage.close();
        } 
    }

    @FXML
    private void fnPlay(MouseEvent event) {
        MainScreen.setVisible(false);
        SecondScreen.setVisible(true);
        PlayScreen.setVisible(false);
        mediaPlayer = Util.initMediaPlayer("Pista.mp3");
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
    }

    @FXML
    private void fnPlayAgain(MouseEvent event) {
        String nUser = (String) txtNQuestions.getText();   
        if(Util.verificacionesNumericas(nUser)){
            if(Util.verificarMax(nUser, 20)){
                if(Util.generarAlertaConfirmacion("EMPEZAR A JUGAR", "¿De verdad? ¡Asegúrate de haber ingresado el número correcto!")){
                    PlayScreen.setVisible(true);
                    MainScreen.setVisible(false);
                    SecondScreen.setVisible(false);

                    if (mediaPlayer != null) {
                        mediaPlayer.stop(); 
                        mediaPlayer.dispose(); 
                    }

                    mediaPlayer = Util.initMediaPlayer("PistaJuego.mp3");

                    if (mediaPlayer != null) {
                        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
                        mediaPlayer.play();
                    }

                    nQuestions = Integer.parseInt(nUser);
                    fnGame();
                    
                    
                }
            } else{
                Util.generarAlertaError("DEMASIADAS PREGUNTAS", "¡Nunca terminaríamos de jugar si escoges un número tan grande!");
            }
        } else{
            Util.generarAlertaError("INGRESO INVÁLIDO", "¡Vamos, no podremos jugar si no te lo tomas en serio!");
        }
    }
    
    
    
    
   
    
    
    private void fnGame(){
        btnYes.setOnAction(e -> {
            // Aquí van acciones
            Util.generarAlertaError("Prueba y error", "Prueba de presionar Sí");
            respuestasUsuario.add("si");
        });
        
        btnNo.setOnAction(e -> {
            // Aquí van acciones
            Util.generarAlertaError("Prueba y error", "Prueba de presionar No");
            respuestasUsuario.add("no");
        });
    }
    
//
    @FXML
    private void fnMachinePlay(MouseEvent event) {
    }
    
    
    
}
