module ni.edu.uam.aplicacionregistro {
    requires javafx.controls;
    requires javafx.fxml;


    opens ni.edu.uam.aplicacionregistro to javafx.fxml;
    exports ni.edu.uam.aplicacionregistro;
}