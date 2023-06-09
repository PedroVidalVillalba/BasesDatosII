package gui.admin.espectaculos;

import baseDatos.DataBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.Espectaculo;
import modelo.Zona;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ResourceBundle;

/**
 * Clase controlador del patrón Modelo-Vista-Controlador. Tiene asociada una vista del mismo nombre
 * Controla la vista de administración de espectáculos
 */
public class AdminEspectaculosController implements Initializable {

    @FXML
    TableView<Espectaculo> espectaculoTable;

    @FXML
    TableColumn<Espectaculo, String> nomeColumn;
    @FXML
    TableColumn<Espectaculo, Time> horaInicioColumn;
    @FXML
    TableColumn<Espectaculo, Time> horaFinColumn;
    @FXML
    TableColumn<Espectaculo, String> tematicaColumn;
    @FXML
    TableColumn<Espectaculo, String> descricionColumn;

    private ObservableList<Espectaculo> espectaculoList;

    /** Inicialización de la vista */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
        horaInicioColumn.setCellValueFactory(new PropertyValueFactory<>("horaInicio"));
        horaFinColumn.setCellValueFactory(new PropertyValueFactory<>("horaFin"));
        tematicaColumn.setCellValueFactory(new PropertyValueFactory<>("tematica"));
        descricionColumn.setCellValueFactory(new PropertyValueFactory<>("descricion"));
        espectaculoList = FXCollections.observableList(DataBase.getCurrentDB().getAllEspectaculos());

        espectaculoTable.setItems(espectaculoList);
    }


    /**
     * Removes the selected users from the database and the observable list.
     * This method is triggered when the "Eliminar usuario" button is clicked.
     */
    public void deleteEspectaculo() throws SQLException {
        // Get the selected shows from the table view
        Espectaculo selectedEspectaculo = espectaculoTable.getSelectionModel().getSelectedItem();

        // Only delete the show if one is selected
        if (selectedEspectaculo != null) {
            // Remove the shows from the database
            DataBase.getCurrentDB().borrarEspectaculo(selectedEspectaculo);

            // Remove the shows from the observable list
            espectaculoList.remove(selectedEspectaculo);
        }
    }
}
