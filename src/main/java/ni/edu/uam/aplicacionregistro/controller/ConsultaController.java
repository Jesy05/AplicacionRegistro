package ni.edu.uam.aplicacionregistro.controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import ni.edu.uam.aplicacionregistro.Cliente;
import ni.edu.uam.aplicacionregistro.DataStore;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
public class ConsultaController implements Initializable {

        @FXML private TableView<Cliente> tablaClientes;
        @FXML private TableColumn<Cliente, String> colNombre;
        @FXML private TableColumn<Cliente, String> colTipoCliente;
        @FXML private TableColumn<Cliente, String> colCiudad;
        @FXML private TableColumn<Cliente, String> colFechaNacimiento;
        @FXML private TableColumn<Cliente, String> colTipoSolicitud;

        @Override
        public void initialize(URL location, ResourceBundle resources) {
            colNombre.setCellValueFactory(new PropertyValueFactory<>("nombreCompleto"));
            colTipoCliente.setCellValueFactory(new PropertyValueFactory<>("tipoCliente"));
            colCiudad.setCellValueFactory(new PropertyValueFactory<>("ciudad"));
            colFechaNacimiento.setCellValueFactory(new PropertyValueFactory<>("fechaNacimiento"));
            colTipoSolicitud.setCellValueFactory(new PropertyValueFactory<>("tipoSolicitud"));

            tablaClientes.setItems(DataStore.getClientes());
        }

        @FXML
        private void onTablaMouseClicked(MouseEvent event) {
            if (event.getClickCount() == 2) {
                Cliente seleccionado = tablaClientes.getSelectionModel().getSelectedItem();
                if (seleccionado != null) {
                    abrirDetalle(seleccionado);
                }
            }
        }

        private void abrirDetalle(Cliente cliente) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/ni/edu/uam/aplicacionregistro/detalle-view.fxml"));
                Parent root = loader.load();

                DetalleController controller = loader.getController();
                controller.setCliente(cliente);

                Stage stage = new Stage();
                stage.setTitle("Detalle del cliente");
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("No fue posible abrir el detalle del cliente.");
                alert.showAndWait();
            }
        }

        @FXML
        private void onExportarReporteClick(ActionEvent event) {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            directoryChooser.setTitle("Seleccionar carpeta destino");

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            File carpeta = directoryChooser.showDialog(stage);

            if (carpeta != null) {
                File archivo = new File(carpeta, "reporte_clientes.txt");
                try (FileWriter writer = new FileWriter(archivo)) {
                    for (Cliente cliente : DataStore.getClientes()) {
                        writer.write(cliente.getNombreCompleto() + " | " + cliente.getTipoCliente() + " | "
                                + cliente.getCiudad() + " | " + cliente.getFechaNacimiento() + " | "
                                + cliente.getTipoSolicitud() + System.lineSeparator());
                    }
                    Alert ok = new Alert(Alert.AlertType.INFORMATION);
                    ok.setTitle("Exportación exitosa");
                    ok.setHeaderText(null);
                    ok.setContentText("El reporte se guardó en: " + archivo.getAbsolutePath());
                    ok.showAndWait();
                } catch (IOException e) {
                    Alert error = new Alert(Alert.AlertType.ERROR);
                    error.setTitle("Error");
                    error.setHeaderText(null);
                    error.setContentText("No fue posible generar el reporte.");
                    error.showAndWait();
                }
            }
        }

        @FXML
        private void onRegresarClick(ActionEvent event) {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
        }
    }


