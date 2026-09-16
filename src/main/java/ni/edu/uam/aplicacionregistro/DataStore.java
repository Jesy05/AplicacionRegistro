package ni.edu.uam.aplicacionregistro;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DataStore {
    private static final ObservableList<Cliente> clientes = FXCollections.observableArrayList();

    private DataStore() {}

    public static ObservableList<Cliente> getClientes() {
        return clientes;
    }
}