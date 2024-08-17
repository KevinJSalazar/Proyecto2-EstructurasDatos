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
import java.util.Random;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
    private int contador = 0;
    private int nMachineQuestions;

    private List<String> respuestasUsuario;
    private List<String> preguntasJuego;
    private HashMap<String, List<String>> respuestasJuego;
    private ArbolBinario arbol;
    private List<String> preguntasRealizadas;
    private List<String> posiblesRespuestas;
    
    private ObservableList<String> preguntasCbx;
    private ArrayList<String> copia;
    
    private String selection;
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
    private AnchorPane GameOverSection;
    @FXML
    private AnchorPane PlayZoneScreen;
    @FXML
    private ImageView imvPrest;
    @FXML
    private Button IDYes;
    @FXML
    private Button IDNo;
    @FXML
    private Button IDNs;
    @FXML
    private AnchorPane MachineGameScreen;
    @FXML
    private ImageView imvMachine;
    @FXML
    private Label lblMachineQuestions;
    @FXML
    private ComboBox<String> cbxQuestions;
    @FXML
    private Label lblSelection;
    @FXML
    private Label lblMachineAnswer;
    @FXML
    private Label lblTexto1;
    @FXML
    private Button btnPreguntar;
    

    
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
        Util.mostrarImagen("logoMachine.jpeg", imgFile, imvMachine);
        
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.dispose();
        }
        
        mediaPlayer = Util.initMediaPlayer("pistaConsola.mp3");
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.setVolume(0.2);
        mediaPlayer.play();

        preguntasJuego = CrearArbol.leerPreguntas();
        respuestasJuego = CrearArbol.leerRespuestas();
        
        preguntasRealizadas = new ArrayList<String>();
        respuestasUsuario = CrearArbol.crearListaRespuesta(preguntasJuego.size());
        
        arbol = new ArbolBinario<String>(preguntasJuego.get(0));
        CrearArbol.crearArbolDeDecisiones(arbol, preguntasJuego, 1);
        CrearArbol.añadirAnimales(arbol, respuestasJuego);
        
        copia = new ArrayList<>(preguntasJuego);
        
//        preguntasCbx = FXCollections.observableArrayList(copia);
        
        if(!copia.isEmpty()){
            
            llenarComboBox(copia);
            do{
                nMachineQuestions = new Random().nextInt(preguntasJuego.size() + 1);
            } while(nMachineQuestions == 1);
        }
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
        MachineGameScreen.setVisible(false);
        
//        if (mediaPlayer != null) {
//            mediaPlayer.stop();
//            mediaPlayer.dispose();
//        }
//        
//        mediaPlayer = Util.initMediaPlayer("Pista.mp3");
//        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
//        mediaPlayer.setVolume(0.2);
//        mediaPlayer.play();
    }

    @FXML
    private void fnPlayAgain(MouseEvent event) {
        String nUser = (String) txtNQuestions.getText();   
        if(Util.verificacionesNumericas(nUser)){
            if(Util.verificarMax(nUser, preguntasJuego.size())){
                if(Util.generarAlertaConfirmacion("EMPEZAR A JUGAR", "¿De verdad? ¡Asegúrate de haber ingresado el número correcto!")){
                    PlayScreen.setVisible(true);
                    MainScreen.setVisible(false);
                    SecondScreen.setVisible(false);
                    MachineGameScreen.setVisible(false);

//                    if (mediaPlayer != null) {
//                        mediaPlayer.stop(); 
//                        mediaPlayer.dispose(); 
//                    }
//
//                    mediaPlayer = Util.initMediaPlayer("PistaJuego.mp3");
//
//                    if (mediaPlayer != null) {
//                        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
//                        mediaPlayer.setVolume(0.2);
//                        mediaPlayer.play();
//                    }

                    nQuestions = Integer.parseInt(nUser);
                    jugar();
                }
            } else{
                Util.generarAlertaError("DEMASIADAS PREGUNTAS", "¡Nunca terminaríamos de jugar si escoges un número tan grande!");
            }
        } else{
            Util.generarAlertaError("INGRESO INVÁLIDO", "¡Vamos, no podremos jugar si no te lo tomas en serio!");
        }
    }
    
    private void jugar(){
        if(contador == nQuestions)
           acabarjuego();
        else{
            String preguntaElegida;
            do{
                preguntaElegida = preguntasJuego.get(new Random().nextInt(preguntasJuego.size()));
            } while(preguntasRealizadas.contains(preguntaElegida));
            preguntasRealizadas.add(preguntaElegida);
            lblQuestions.setText(preguntaElegida);
        }
    }
    
    private void seguirJugando(){
        contador++;
        jugar();
    }
    
    private void acabarjuego(){
        deshabilitarBtns();
        posiblesRespuestas = arbol.respuestasPorRecorrido(respuestasUsuario, 0);
        String resultado = Util.resultado(posiblesRespuestas);
        if(Util.confirmarTamaño(posiblesRespuestas)){
            Util.generarAlertaInfo("¡No he llegado a una única respuesta :c!", resultado);
        } else{
            Util.generarAlertaInfo("¡Creo que he adivinado correctamente!", resultado);
        }
        
        GameOverSection.setVisible(true);
//        clear();
        //Poner pantalla de final de juego y mostrar el animal o animales resultantes.
    }
    
    @FXML
    private void btnYes(MouseEvent event) {
        int id = CrearArbol.getIndicePregunta(preguntasJuego, lblQuestions.getText());
        respuestasUsuario.set(id, "si");
        seguirJugando();
    }

    @FXML
    private void btnNo(MouseEvent event) {
        int id = CrearArbol.getIndicePregunta(preguntasJuego, lblQuestions.getText());
        respuestasUsuario.set(id, "no");
        seguirJugando();
    }

    @FXML
    private void btnNoSe(MouseEvent event) {
        seguirJugando();
    }
    
//    private void fnGame(String pregunta){
//        btnYes.setOnAction(e -> {
////            Util.generarAlertaError("Prueba y error", "Prueba de presionar Sí");
//            int id = CrearArbol.getIndicePregunta(preguntasJuego, pregunta);
//            respuestasUsuario.set(id, "si");
//        });
//        btnNo.setOnAction(e -> {
////            Util.generarAlertaError("Prueba y error", "Prueba de presionar Sí");
//            int id = CrearArbol.getIndicePregunta(preguntasJuego, pregunta);
//            respuestasUsuario.set(id, "no");
//        });
//        respondido = true;
//    }

    @FXML
    private void fnMachinePlay(MouseEvent event) {
        if(Util.generarAlertaConfirmacion("¿Asustado, Potter?", "Es tu última oportunidad para escapar, ¿en verdad quieres retarme?")){
            fnMachineGame(event);
        } else{
            fnReturn(event);
        }   
    }

    @FXML
    private void fnSafariQuestions(MouseEvent event) {
        PlayZoneScreen.setVisible(false);
        MainScreen.setVisible(true);
        SecondScreen.setVisible(false);
        PlayScreen.setVisible(false);
        MachineGameScreen.setVisible(false);
    }

    @FXML
    private void fnMachineGame(MouseEvent event) {
        MachineGameScreen.setVisible(true);
        PlayZoneScreen.setVisible(false);
        MainScreen.setVisible(false);
        SecondScreen.setVisible(false);
        PlayScreen.setVisible(false);
        
        lblMachineQuestions.setText(nMachineQuestions + " preguntas");
        
        cbxQuestions.setOnAction(eh->{
            selection = cbxQuestions.getSelectionModel().getSelectedItem();
        });
        
        lblMachineAnswer.setText("MI RESPUESTA");
        

//            while(nMachineQuestions == 1){
//                nMachineQuestions = (int) (Math.random() * copia.size() + 1);
//            }

        

//            manejarSeleccion(cbxQuestions, lblSelection);
        
        
        
    
        
        
//        String nUser = (String) txtNQuestions.getText();   
//        if(Util.verificacionesNumericas(nUser)){
//            if(Util.verificarMax(nUser, preguntasJuego.size())){
//                if(Util.generarAlertaConfirmacion("EMPEZAR A JUGAR", "¿De verdad? ¡Asegúrate de haber ingresado el número correcto!")){
//                    PlayScreen.setVisible(true);
//                    MainScreen.setVisible(false);
//                    SecondScreen.setVisible(false);
//
//                    if (mediaPlayer != null) {
//                        mediaPlayer.stop(); 
//                        mediaPlayer.dispose(); 
//                    }
//
//                    mediaPlayer = Util.initMediaPlayer("PistaJuego.mp3");
//
//                    if (mediaPlayer != null) {
//                        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
//                        mediaPlayer.play();
//                    }
//
//                    nQuestions = Integer.parseInt(nUser);
//                    jugar();
//                }
//            } else{
//                Util.generarAlertaError("DEMASIADAS PREGUNTAS", "¡Nunca terminaríamos de jugar si escoges un número tan grande!");
//            }
//        } else{
//            Util.generarAlertaError("INGRESO INVÁLIDO", "¡Vamos, no podremos jugar si no te lo tomas en serio!");
//        }
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
        GameOverSection.setVisible(false);
        MachineGameScreen.setVisible(false);

        clear();
        
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.dispose();
        }
        
        mediaPlayer = Util.initMediaPlayer("pistaConsola.mp3");
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.setVolume(0.2);
        mediaPlayer.play();
    }
    
    public void clear(){
//        lblQuestions.setText("");
        habilitarBtns();
        txtNQuestions.setText("");
        nQuestions = 0;
        contador = 0;
        respuestasUsuario = CrearArbol.crearListaRespuesta(preguntasJuego.size());
        preguntasRealizadas = new ArrayList<>();
        
        copia = new ArrayList<>(preguntasJuego);
        
        if(!copia.isEmpty()){
            
            llenarComboBox(copia);
            do{
                nMachineQuestions = new Random().nextInt(preguntasJuego.size());
            } while(nMachineQuestions == 0 || nMachineQuestions == 1);
        }
        
        lblTexto1.setText("He decidido que podrás hacerme");
        lblMachineQuestions.setText("");
        lblMachineAnswer.setText("");
        cbxQuestions.setDisable(false);
        btnPreguntar.setDisable(false);
        
    }
    
    public void deshabilitarBtns(){
        IDYes.setDisable(true);
        IDNo.setDisable(true);
        IDNs.setDisable(true);
    }
    
    public void habilitarBtns(){
        IDYes.setDisable(false);
        IDNo.setDisable(false);
        IDNs.setDisable(false);
    }
    
    public void llenarComboBox(List<String> preguntas){
        
        ObservableList<String> items = FXCollections.observableArrayList(preguntas);
        items.add(0, "Elija su pregunta");
       
        cbxQuestions.setItems(items);
    }
    
//    public void manejarSeleccion(ComboBox<String> cbx, Label lbl){
//        cbx.setOnAction(eh ->{
//            selection = cbx.getSelectionModel().getSelectedItem();
//            
//            if(!selection.equals("Elija su pregunta")){
//                lbl.setText(selection);
//
////                cbx.getItems().remove(selection);
////                preguntasCbx.remove(selection);
//                copia.remove(selection);
//                llenarComboBox(copia);
////                copia.remove(selection);
//               
//
//                nMachineQuestions--;
//                lblMachineQuestions.setText(nMachineQuestions + " preguntas");
//            }
//            
//            if(preguntasCbx.isEmpty() || nMachineQuestions <= 0){
//                lblMachineQuestions.setText("Ya no puedes preguntar más.");
//                cbx.setDisable(true);
//            }
//            
//        });
//        
//        
//        
//    }

    @FXML
    private void fnPreguntar(MouseEvent event) {
        if(nMachineQuestions != 0){
            if(!selection.equals("Elija su pregunta")){
                nMachineQuestions--;
                lblSelection.setText(selection);               
                lblMachineQuestions.setText(nMachineQuestions + " preguntas");
                cbxQuestions.getItems().remove(selection);
            } else{
                Util.generarAlertaError("Selección pendiente", "Debes seleccionar una pregunta");                
            }
        } else{
            lblTexto1.setText("Es una lástima,");
            lblMachineQuestions.setText("ya no te quedan más preguntas.");
            lblSelection.setText("");
            btnPreguntar.setDisable(true);
            cbxQuestions.setDisable(true);
        }
    }
}
