package gui.admin.zonas;

import baseDatos.DataBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.Zona;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Clase controlador del patrón Modelo-Vista-Controlador. Tiene asociada una vista del mismo nombre
 * Controla la vista de administración de espectáculos
 */
public class AdminZonaController implements Initializable {

    @FXML
    TableView<Zona> zonaTable;

    @FXML
    TableColumn<Zona, String> nomeColumn;
    @FXML
    TableColumn<Zona, Float> extensionColumn;
    @FXML
    TableColumn<Zona, Float> coordenadaxColumn;
    @FXML
    TableColumn<Zona, Float> coordenadayColumn;

    private ObservableList<Zona> zonaList;

    /** Inicialización de la vista */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
        extensionColumn.setCellValueFactory(new PropertyValueFactory<>("extension"));
        coordenadaxColumn.setCellValueFactory(new PropertyValueFactory<>("coordenadaX"));
        coordenadayColumn.setCellValueFactory(new PropertyValueFactory<>("coordenadaY"));
        zonaList = FXCollections.observableList(DataBase.getCurrentDB().getAllZones());

        zonaTable.setItems(zonaList);
    }


    /**
     * Removes the selected users from the database and the observable list.
     * This method is triggered when the "Eliminar usuario" button is clicked.
     */
    public void deleteZona() throws SQLException {
        // Get the selected shows from the table view
        Zona selectedZona = zonaTable.getSelectionModel().getSelectedItem();

        // Only delete the show if one is selected
        if (selectedZona != null) {
            // Remove the shows from the database
            DataBase.getCurrentDB().borrarZona(selectedZona);

            // Remove the shows from the observable list
            zonaList.remove(selectedZona);
        }
    }
}
