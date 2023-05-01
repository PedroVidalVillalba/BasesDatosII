package gui.admin.traballadoresparque;

import baseDatos.DataBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.TraballadorParque;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

/**
 * Clase controlador del patrón Modelo-Vista-Controlador. Tiene asociada una vista del mismo nombre
 * Controla la vista de administración de trabajadores del parque
 */
public class AdminTraballadoresParqueController implements Initializable {
    @FXML
    TableView<TraballadorParque> trabajadorTable;

    @FXML
    TableColumn<TraballadorParque, String> dniColumn;
    @FXML
    TableColumn<TraballadorParque, String> nomeColumn;
    @FXML
    TableColumn<TraballadorParque, String> ruaColumn;
    @FXML
    TableColumn<TraballadorParque, Integer> numeroColumn;
    @FXML
    TableColumn<TraballadorParque, Integer> cpColumn;
    @FXML
    TableColumn<TraballadorParque, String> localidadeColumn;
    @FXML
    TableColumn<TraballadorParque, Float> salarioColumn;
    @FXML
    TableColumn<TraballadorParque, String> telefonoColumn;
    @FXML
    TableColumn<TraballadorParque, LocalDate> dataInicioColumn;
    @FXML
    TableColumn<TraballadorParque, LocalDate> dataNacementoColumn;
    @FXML
    TableColumn<TraballadorParque, String> formacionColumn;

    private ObservableList<TraballadorParque> trabajadorList;

    /** Inicialización de la vista */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dniColumn.setCellValueFactory(new PropertyValueFactory<>("dni"));
        nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
        ruaColumn.setCellValueFactory(new PropertyValueFactory<>("rua"));
        numeroColumn.setCellValueFactory(new PropertyValueFactory<>("numero"));
        cpColumn.setCellValueFactory(new PropertyValueFactory<>("cp"));
        localidadeColumn.setCellValueFactory(new PropertyValueFactory<>("localidade"));
        salarioColumn.setCellValueFactory(new PropertyValueFactory<>("salario"));
        telefonoColumn.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        dataInicioColumn.setCellValueFactory(new PropertyValueFactory<>("dataInicio"));
        dataNacementoColumn.setCellValueFactory(new PropertyValueFactory<>("dataNacemento"));
        formacionColumn.setCellValueFactory(new PropertyValueFactory<>("formacion"));

        // Habilitar la edición en la tabla.
        trabajadorTable.setEditable(true);


        try {
            trabajadorList = FXCollections.observableList(DataBase.getCurrentDB().getAllWorkers());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        trabajadorTable.setItems(trabajadorList);
    }

    /**
     * Removes the selected worker from the database and the observable list.
     * This method is triggered when the "Eliminar trabajador" button is clicked.
     */
    public void deleteTrabajador() throws SQLException {
        // Get the selected shows from the table view
        TraballadorParque selectedSistema = trabajadorTable.getSelectionModel().getSelectedItem();

        // Only delete the show if one is selected
        if (selectedSistema != null) {
            // Remove the shows from the database
            DataBase.getCurrentDB().borrarTrabajador(selectedSistema);

            // Remove the shows from the observable list
            trabajadorList.remove(selectedSistema);
        }
    }
}
