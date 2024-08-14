/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.util;

import java.io.File;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 *
 * @author omits
 */
public class Util {
    public static void mostrarImagen(String nombreImagen, File imgFile, ImageView imv){
        String rutaProyecto = System.getProperty("user.dir")+File.separator+"src"+File.separator+"main"+File.separator+"resources";
        String rutaCarpetaDestino = rutaProyecto + File.separator + "images";
        imgFile = new File(rutaCarpetaDestino, nombreImagen);
        Image imagen = new Image(imgFile.toURI().toString());
        
        imv.setPreserveRatio(true);
        imv.setFitWidth(imv.getFitWidth());
        imv.setFitHeight(imv.getFitHeight());
        imv.setSmooth(true);
        imv.setImage(imagen);
        // Ajustando imagen con el método creado :)
//        imagen = ajustarTamañoImagen(imagen, imv.getFitWidth() ,imv.getFitHeight());
        imv.setImage(imagen);
    }
    
    public static Image ajustarTamañoImagen(Image imagen, double ancho, double alto){
        ImageView vistaPrevia = new ImageView(imagen);
        vistaPrevia.setFitWidth(ancho);
        vistaPrevia.setFitHeight(alto);
        return vistaPrevia.snapshot(null, null);
    }         
 
    public static MediaPlayer initMediaPlayer(String nombreAudio) {
        MediaPlayer mediaPlayer = null;
        try {
            String rutaAudio = Util.class.getResource("/music/" + nombreAudio).toString();
            if (rutaAudio == null) {
                System.err.println("El archivo de audio no se encontró: " + nombreAudio);
                return null;
            }
            Media media = new Media(rutaAudio);
            mediaPlayer = new MediaPlayer(media);          
            
        } catch (Exception e) {
            System.err.println("Error al inicializar el MediaPlayer: " + e.getMessage());
        }
        
        return mediaPlayer;
    }
    
    public static boolean verificacionesNumericas(String str){
        if (str.matches("\\d+")) {
            int number = Integer.parseInt(str);
            return number > 0;
        }
        
        return false;
    }
    
    public static boolean verificarMax(String str, int num){
        return Integer.parseInt(str) <= num;
    }
    
    public static boolean generarAlertaConfirmacion(String titulo, String mensaje){
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION, mensaje);
        alerta.setTitle(titulo.toUpperCase());
        alerta.setHeaderText(null);
        Optional<ButtonType> decisionBtn = alerta.showAndWait();
        if(decisionBtn.isPresent()){
            if(decisionBtn.get() == ButtonType.OK)
                return true;  
            else if (decisionBtn.get() == ButtonType.CANCEL)
                return false;
            else if (decisionBtn.get() == ButtonType.CLOSE)
                return false;
        }       
        return false;   
    }
    
    public static void generarAlertaError(String titulo, String mensaje){
        Alert alerta = new Alert(Alert.AlertType.ERROR, mensaje);
        alerta.setTitle(titulo.toUpperCase());
        alerta.setHeaderText(null);
        alerta.show();
    }

}
