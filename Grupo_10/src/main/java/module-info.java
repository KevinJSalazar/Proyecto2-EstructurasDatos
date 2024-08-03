module ec.edu.espol.grupo_10_iiparcial {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires java.base;
    

    opens ec.edu.espol.grupo_10_iiparcial to javafx.fxml;
    exports ec.edu.espol.grupo_10_iiparcial;
    opens ec.edu.espol.controllers to javafx.fxml;
    exports ec.edu.espol.controllers;
    opens ec.edu.espol.util to javafx.fxml;
    exports ec.edu.espol.util;
}
