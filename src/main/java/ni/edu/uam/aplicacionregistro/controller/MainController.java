package ni.edu.uam.aplicacionregistro.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.stage.Stage;
import ni.edu.uam.aplicacionregistro.DataStore;

import java.io.IOException;
import java.util.Optional;

public class MainController {

    @FXML
    private void onMenuRegistroClick(ActionEvent event) {
        abrirVentana("registro-view.fxml", "Registro de cliente");
    }

    @FXML
    private void onToolbarRegistroClick(ActionEvent event) {
        abrirVentana("registro-view.fxml", "Registro de cliente");
    }

    @FXML
    private void onMenuConsultaClick(ActionEvent event) {
        abrirVentana("consulta-view.fxml", "Consulta de clientes");
    }

    @FXML
    private void onToolbarConsultaClick(ActionEvent event) {
        abrirVentana("consulta-view.fxml", "Consulta de clientes");
    }

    private void abrirVentana(String fxml, String titulo) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
            Parent root = loader.load();

            Stage nuevaVentana = new Stage();
            nuevaVentana.setTitle(titulo);
            nuevaVentana.setScene(new Scene(root));
            nuevaVentana.show();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("No fue posible abrir la ventana: " + titulo);
            alert.showAndWait();
        }
    }

    @FXML
    private void onCerrarSesionClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login-view.fxml"));
            Parent root = loader.load();

            Stage login = new Stage();
            login.setTitle("Inicio de sesión");
            login.setScene(new Scene(root));
            login.show();

            obtenerStage(event).close();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("No fue posible regresar al inicio de sesión.");
            alert.showAndWait();
        }
    }

    @FXML
    private void onMenuSalirClick(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmar salida");
        alert.setHeaderText(null);
        alert.setContentText("¿Está seguro de que desea salir de la aplicación?");
        Optional<ButtonType> resultado = alert.showAndWait();
        if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
            Platform.exit();
        }
    }

    @FXML
    private void onVerInfoClick(ActionEvent event) {
        Dialog<Void> dialog = new Dialog<>();
        dialog.setTitle("Acerca del sistema");
        dialog.setHeaderText("Sistema de Registro de Clientes");
        dialog.setContentText("Total de clientes registrados: " + DataStore.getClientes().size()
                + "\nAplicación desarrollada en JavaFX.");
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
        dialog.showAndWait();
    }

    private Stage obtenerStage(ActionEvent event) {
        return (Stage) ((Node) event.getSource()).getScene().getWindow();
    }
}