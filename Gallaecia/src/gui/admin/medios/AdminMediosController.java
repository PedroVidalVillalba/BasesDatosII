package gui.admin.medios;

import baseDatos.DataBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.Medio;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Clase controlador del patrón Modelo-Vista-Controlador. Tiene asociada una vista del mismo nombre
 * Controla la vista de administración de medios de acceso al parque
 */
public class AdminMediosController implements Initializable {
    @FXML
    TableView<Medio> medioTable;

    @FXML
    TableColumn<Medio, String> nomeMedioColumn;
    @FXML
    TableColumn<Medio, String> tipoColumn;
    @FXML
    TableColumn<Medio, Float> prezoColumn;
    @FXML
    TableColumn<Medio, Integer> capacidadeColumn;
    @FXML
    TableColumn<Medio, Float> velocidadeColumn;
    @FXML
    private ObservableList<Medio> medioList;

    /** Inicialización de la vista */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nomeMedioColumn.setCellValueFactory(new PropertyValueFactory<>("nomeMedio"));
        tipoColumn.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        prezoColumn.setCellValueFactory(new PropertyValueFactory<>("prezo"));
        capacidadeColumn.setCellValueFactory(new PropertyValueFactory<>("capacidade"));
        velocidadeColumn.setCellValueFactory(new PropertyValueFactory<>("velocidade"));

        // Habilitar la edición en la tabla.
        medioTable.setEditable(true);


        try {
            medioList = FXCollections.observableList(DataBase.getCurrentDB().getAllMedios());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        medioTable.setItems(medioList);
    }

    /**
     * Removes the selected medio from the database and the observable list.
     * This method is triggered when the "Borrar medio" button is clicked.
     */
    public void deleteMedio() throws SQLException {
        // Get the selected shows from the table view
        Medio selectedMedio = medioTable.getSelectionModel().getSelectedItem();

        // Only delete the show if one is selected
        if (selectedMedio != null) {
            // Remove the shows from the database
            DataBase.getCurrentDB().borrarMedio(selectedMedio);

            // Remove the shows from the observable list
            medioList.remove(selectedMedio);
        }
    }
}
