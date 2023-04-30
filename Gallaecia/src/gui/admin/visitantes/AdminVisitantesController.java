package gui.admin.visitantes;

import baseDatos.DataBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.Espectaculo;
import modelo.Medio;
import modelo.Visitante;
import modelo.Zona;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ResourceBundle;

/**
 * Clase controlador del patrón Modelo-Vista-Controlador. Tiene asociada una vista del mismo nombre
 * Controla la vista de administración de visitantes
 */
public class AdminVisitantesController implements Initializable {

    @FXML
    TableView<Visitante> visitantesTable;

    @FXML
    TableColumn<Visitante, String> dniColumn;
    @FXML
    TableColumn<Visitante, String> nomeColumn;
    @FXML
    TableColumn<Visitante, String> nacionalidadeColumn;
    @FXML
    TableColumn<Visitante, String> telefonoColumn;
    @FXML
    TableColumn<Visitante, Date> dataNacementoColumn;
    @FXML
    TableColumn<Visitante, Integer> alturaColumn;


    private ObservableList<Visitante> visitantesList;

    /** Inicialización de la vista */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dniColumn.setCellValueFactory(new PropertyValueFactory<>("dni"));
        nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
        nacionalidadeColumn.setCellValueFactory(new PropertyValueFactory<>("nacionalidade"));
        telefonoColumn.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        dataNacementoColumn.setCellValueFactory(new PropertyValueFactory<>("dataNacemento"));
        alturaColumn.setCellValueFactory(new PropertyValueFactory<>("altura"));
        visitantesList = FXCollections.observableList(DataBase.getCurrentDB().getAllVisitantes());

        visitantesTable.setItems(visitantesList);
    }


    /**
     * Removes the selected users from the database and the observable list.
     * This method is triggered when the "Eliminar usuario" button is clicked.
     */
    public void deleteVisitante() throws SQLException {
        // Get the selected shows from the table view
        Visitante selectedVisitante = visitantesTable.getSelectionModel().getSelectedItem();

        // Only delete the show if one is selected
        if (selectedVisitante != null) {
            // Remove the shows from the database
            DataBase.getCurrentDB().borrarVisitante(selectedVisitante);

            // Remove the shows from the observable list
            visitantesList.remove(selectedVisitante);
        }
    }

}