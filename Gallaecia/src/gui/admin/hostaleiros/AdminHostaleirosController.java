package gui.admin.hostaleiros;

import baseDatos.DataBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.Hostaleiro;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

/**
 * Clase controlador del patrón Modelo-Vista-Controlador. Tiene asociada una vista del mismo nombre
 * Controla la vista de administración de hostaleiros.
 */
public class AdminHostaleirosController implements Initializable {
    @FXML
    TableView<Hostaleiro> hostaleiroTable;

    @FXML
    TableColumn<Hostaleiro, String> dniColumn;
    @FXML
    TableColumn<Hostaleiro, String> nomeColumn;
    @FXML
    TableColumn<Hostaleiro, String> ruaColumn;
    @FXML
    TableColumn<Hostaleiro, Integer> numeroColumn;
    @FXML
    TableColumn<Hostaleiro, Integer> cpColumn;
    @FXML
    TableColumn<Hostaleiro, String> localidadeColumn;
    @FXML
    TableColumn<Hostaleiro, Float> salarioColumn;
    @FXML
    TableColumn<Hostaleiro, String> telefonoColumn;
    @FXML
    TableColumn<Hostaleiro, LocalDate> datainicioColumn;
    @FXML
    TableColumn<Hostaleiro, LocalDate> datanacementoColumn;
    @FXML
    TableColumn<Hostaleiro, String> formacionColumn;
    @FXML
    //TableColumn<Hostaleiro, Hostalaria> nomeestablecementoColumn;

    private ObservableList<Hostaleiro> hostaleiroList;

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
        datainicioColumn.setCellValueFactory(new PropertyValueFactory<>("dataInicio"));
        datanacementoColumn.setCellValueFactory(new PropertyValueFactory<>("dataNacemento"));
        formacionColumn.setCellValueFactory(new PropertyValueFactory<>("formacion"));
        //nomeestablecementoColumn.setCellValueFactory(new PropertyValueFactory<>("hostalaria"));

        try {
            hostaleiroList = FXCollections.observableList(DataBase.getCurrentDB().getAllHostaleiros());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        hostaleiroTable.setItems(hostaleiroList);
    }

    /**
     * Removes the selected hostaleiro from the database and the observable list.
     * This method is triggered when the "Eliminar hostaleiro" button is clicked.
     */
    public void deleteHostaleiro() throws SQLException {
        // Get the selected hostaleiro from the table view
        Hostaleiro selectedhostaleiro = hostaleiroTable.getSelectionModel().getSelectedItem();

        // Only delete the show if one is selected
        if (selectedhostaleiro != null) {
            // Remove the hostaleiro from the database
            DataBase.getCurrentDB().borrarHostaleiro(selectedhostaleiro);

            // Remove the hostaleiro from the observable list
            hostaleiroList.remove(selectedhostaleiro);
        }
    }
}