package gui.admin.sistemasdeaudio;

import baseDatos.DataBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.Espectaculo;
import modelo.SistemaDeAudio;
import modelo.Zona;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


/**
 * Clase controlador del patrón Modelo-Vista-Controlador. Tiene asociada una vista del mismo nombre
 * Controla la vista de administración de sistemas de audio
 */

public class AdminSistemasDeAudioController implements Initializable {
    @FXML
    TableView<SistemaDeAudio> sistemaTable;

    @FXML
    TableColumn<SistemaDeAudio, String> identificadorColumn;
    @FXML
    TableColumn<SistemaDeAudio, String> funcionColumn;
    @FXML
    TableColumn<SistemaDeAudio, String> descricionColumn;
    @FXML
    //TableColumn<SistemaDeAudio, Zona> zonaColumn;

    private ObservableList<SistemaDeAudio> sistemaList;

    /** Inicialización de la vista */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        identificadorColumn.setCellValueFactory(new PropertyValueFactory<>("identificador"));
        funcionColumn.setCellValueFactory(new PropertyValueFactory<>("funcion"));
        descricionColumn.setCellValueFactory(new PropertyValueFactory<>("descricion"));
        //zonaColumn.setCellValueFactory(new PropertyValueFactory<>("zona"));

        // Habilitar la edición en la tabla.
        sistemaTable.setEditable(true);


        try {
            sistemaList = FXCollections.observableList(DataBase.getCurrentDB().getAllSistemas());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        sistemaTable.setItems(sistemaList);
    }

    /**
     * Removes the selected system from the database and the observable list.
     * This method is triggered when the "Borrar sistema" button is clicked.
     */
    public void deleteSistema() throws SQLException {
        // Get the selected shows from the table view
        SistemaDeAudio selectedSistema = sistemaTable.getSelectionModel().getSelectedItem();

        // Only delete the show if one is selected
        if (selectedSistema != null) {
            // Remove the shows from the database
            DataBase.getCurrentDB().borrarSistema(selectedSistema);

            // Remove the shows from the observable list
            sistemaList.remove(selectedSistema);
        }
    }
}
