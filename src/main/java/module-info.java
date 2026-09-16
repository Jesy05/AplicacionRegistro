module ni.edu.uam.aplicacionregistro {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;


    opens ni.edu.uam.aplicacionregistro to javafx.fxml;
    exports ni.edu.uam.aplicacionregistro;
    exports ni.edu.uam.aplicacionregistro.controller;
    opens ni.edu.uam.aplicacionregistro.controller to javafx.fxml;
}