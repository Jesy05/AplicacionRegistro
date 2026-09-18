package ni.edu.uam.aplicacionregistro.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import ni.edu.uam.aplicacionregistro.Cliente;

public class DetalleController {
        @FXML private ImageView imgFoto;
        @FXML private Label lblNombre;
        @FXML private Label lblTipoCliente;
        @FXML private Label lblCiudad;
        @FXML private Label lblFechaNacimiento;
        @FXML private Label lblTipoSolicitud;
        @FXML private Label lblServicios;

        public void setCliente(Cliente cliente) {
            lblNombre.setText(cliente.getNombreCompleto());
            lblTipoCliente.setText(cliente.getTipoCliente());
            lblCiudad.setText(cliente.getCiudad());
            lblFechaNacimiento.setText(String.valueOf(cliente.getFechaNacimiento()));
            lblTipoSolicitud.setText(cliente.getTipoSolicitud());
            lblServicios.setText(String.join(", ", cliente.getServiciosInteres()));

            if (cliente.getRutaFoto() != null) {
                imgFoto.setImage(new Image(cliente.getRutaFoto()));
            }
        }

        @FXML
        private void onCerrarClick(ActionEvent event) {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
        }
    }

