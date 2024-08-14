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
    @FXML
    private AnchorPane PlayZoneScreen;
    @FXML
    private ImageView imvPrest;
    

    
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
        Util.mostrarImagen("logoConsola.png", imgFile, imvPrest);
        
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.dispose();
        }
        
        mediaPlayer = Util.initMediaPlayer("pistaConsola.mp3");
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();

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
        
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.dispose();
        }
        
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
                    // Probando que se presente algo en el label
                    lblQuestions.setText("Hola, debería ser una pregunta.");
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
            // Otra comprobación
            lblQuestions.setText("Holi, soy el botón sí siendo aplastado");
            respuestasUsuario.add("si");
        });
        
        btnNo.setOnAction(e -> {
            // Aquí van acciones
            Util.generarAlertaError("Prueba y error", "Prueba de presionar No");
            // Otra comprobación
            lblQuestions.setText("Holi, soy el botón no siendo aplastado");
            respuestasUsuario.add("no");
        });
    }
    
//
    @FXML
    private void fnMachinePlay(MouseEvent event) {
    }

    @FXML
    private void fnSafariQuestions(MouseEvent event) {
        PlayZoneScreen.setVisible(false);
        MainScreen.setVisible(true);
        SecondScreen.setVisible(false);
        PlayScreen.setVisible(false);
        
    }

    @FXML
    private void fnMachineGame(MouseEvent event) {
    }

    @FXML
    private void fnAddAnimal(MouseEvent event) {
    }

    @FXML
    private void fnReturn(MouseEvent event) {
        PlayZoneScreen.setVisible(true);
        MainScreen.setVisible(false);
        SecondScreen.setVisible(false);
        PlayScreen.setVisible(false);
        clear();
        
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.dispose();
        }
        
        mediaPlayer = Util.initMediaPlayer("pistaConsola.mp3");
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
    }
    
    public void clear(){
        lblQuestions.setText("Hola, debo ser una pregunta.");
        txtNQuestions.setText("");
        nQuestions = 0;
    }   
}
