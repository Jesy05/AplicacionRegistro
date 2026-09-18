package ni.edu.uam.aplicacionregistro.controller;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class LoginController {

    @FXML private TextField txtUsuario;
    @FXML private PasswordField txtPassword;

    @FXML
    private void onIniciarSesionClick(ActionEvent event) {
        intentarIniciarSesion(event);
    }

    @FXML
    private void onPasswordKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            intentarIniciarSesion(event);
        }
    }

    private void intentarIniciarSesion(Event event) {
        String usuario = txtUsuario.getText();
        String password = txtPassword.getText();

        if (usuario == null || usuario.isBlank() || password == null || password.isBlank()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Datos incompletos");
            alert.setHeaderText(null);
            alert.setContentText("Debe ingresar usuario y contraseña.");
            alert.showAndWait();
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ni/edu/uam/aplicacionregistro/main-view.fxml"));
            Parent root = loader.load();

            Stage stagePrincipal = new Stage();
            stagePrincipal.setTitle("Sistema de Registro de Clientes");
            stagePrincipal.setScene(new Scene(root));
            stagePrincipal.show();

            ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("No fue posible abrir la ventana principal: " + e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    private void onSalirClick(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmar salida");
        alert.setHeaderText(null);
        alert.setContentText("¿Está seguro de que desea salir de la aplicación?");
        Optional<ButtonType> resultado = alert.showAndWait();
        if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
            ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
        }
    }
}