package ni.edu.uam.aplicacionregistro.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import ni.edu.uam.aplicacionregistro.Cliente;
import ni.edu.uam.aplicacionregistro.DataStore;

import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class RegistroController implements Initializable {

    @FXML private TextField txtNombres;
    @FXML private TextField txtApellidos;
    @FXML private ComboBox<String> cmbTipoCliente;
    @FXML private ComboBox<String> cmbCiudad;
    @FXML private DatePicker dpFechaNacimiento;
    @FXML private RadioButton rbConsulta;
    @FXML private RadioButton rbReclamo;
    @FXML private RadioButton rbSoporte;
    @FXML private ToggleGroup grupoTipoSolicitud;
    @FXML private CheckBox chkInstalacion;
    @FXML private CheckBox chkMantenimiento;
    @FXML private CheckBox chkAsesoria;
    @FXML private ImageView imgFoto;

    private String rutaFotoSeleccionada;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cmbTipoCliente.setItems(FXCollections.observableArrayList("Nuevo", "Frecuente", "VIP"));
        cmbCiudad.setItems(FXCollections.observableArrayList("Managua", "León", "Granada", "Masaya", "Estelí"));

        txtNombres.addEventFilter(KeyEvent.KEY_TYPED, this::soloLetras);
        txtApellidos.addEventFilter(KeyEvent.KEY_TYPED, this::soloLetras);
    }

    private void soloLetras(KeyEvent event) {
        if (!event.getCharacter().matches("[a-zA-ZÁÉÍÓÚáéíóúÑñ ]")) {
            event.consume();
        }
    }

    @FXML
    private void onSeleccionarFotoClick(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar fotografía");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Imágenes", "*.png", "*.jpg", "*.jpeg"));

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        File archivo = fileChooser.showOpenDialog(stage);

        if (archivo != null) {
            rutaFotoSeleccionada = archivo.toURI().toString();
            imgFoto.setImage(new Image(rutaFotoSeleccionada));
        }
    }

    @FXML
    private void onGuardarClick(ActionEvent event) {
        String nombres = txtNombres.getText();
        String apellidos = txtApellidos.getText();
        String tipoCliente = cmbTipoCliente.getValue();
        String ciudad = cmbCiudad.getValue();
        LocalDate fechaNacimiento = dpFechaNacimiento.getValue();
        RadioButton seleccionado = (RadioButton) grupoTipoSolicitud.getSelectedToggle();

        if (nombres == null || nombres.isBlank() || apellidos == null || apellidos.isBlank()
                || tipoCliente == null || ciudad == null || fechaNacimiento == null || seleccionado == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Datos incompletos");
            alert.setHeaderText(null);
            alert.setContentText("Debe completar todos los campos y seleccionar un tipo de solicitud.");
            alert.showAndWait();
            return;
        }

        List<String> servicios = new ArrayList<>();
        if (chkInstalacion.isSelected()) servicios.add(chkInstalacion.getText());
        if (chkMantenimiento.isSelected()) servicios.add(chkMantenimiento.getText());
        if (chkAsesoria.isSelected()) servicios.add(chkAsesoria.getText());

        Cliente cliente = new Cliente(nombres, apellidos, tipoCliente, ciudad, fechaNacimiento,
                seleccionado.getText(), servicios, rutaFotoSeleccionada);

        DataStore.getClientes().add(cliente);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Registro exitoso");
        alert.setHeaderText(null);
        alert.setContentText("El cliente " + cliente.getNombres() + " " + cliente.getApellidos() + " fue registrado correctamente.");
        alert.showAndWait();

        limpiarFormulario();
    }

    @FXML
    private void onLimpiarClick(ActionEvent event) {
        limpiarFormulario();
    }

    private void limpiarFormulario() {
        txtNombres.clear();
        txtApellidos.clear();
        cmbTipoCliente.getSelectionModel().clearSelection();
        cmbCiudad.getSelectionModel().clearSelection();
        dpFechaNacimiento.setValue(null);
        grupoTipoSolicitud.selectToggle(null);
        chkInstalacion.setSelected(false);
        chkMantenimiento.setSelected(false);
        chkAsesoria.setSelected(false);
        imgFoto.setImage(null);
        rutaFotoSeleccionada = null;
    }

    @FXML
    private void onCancelarClick(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cancelar registro");
        alert.setHeaderText(null);
        alert.setContentText("¿Desea cancelar el registro? Los datos no guardados se perderán.");
        Optional<ButtonType> resultado = alert.showAndWait();
        if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
        }
    }
}