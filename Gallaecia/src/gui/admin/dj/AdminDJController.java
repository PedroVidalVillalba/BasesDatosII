package gui.admin.dj;

import baseDatos.DataBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.DJ;
import modelo.Espectaculo;
import modelo.Zona;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AdminDJController implements Initializable {

    @FXML
    TableView<DJ> djTable;

    @FXML
    TableColumn<DJ, String> dniColumn;
    @FXML
    TableColumn<DJ, String> nomeColumn;
    @FXML
    TableColumn<DJ, String> ruaColumn;
    @FXML
    TableColumn<DJ, Integer> numeroColumn;
    @FXML
    TableColumn<DJ, Integer> cpColumn;
    @FXML
    TableColumn<DJ, String> localidadeColumn;
    @FXML
    TableColumn<DJ, Float> salarioColumn;
    @FXML
    TableColumn<DJ, String> telefonoColumn;
    @FXML
    TableColumn<DJ, LocalDate> fechaInicioColumn;
    @FXML
    TableColumn<DJ, LocalDate> fechaNacimientoColumn;
    @FXML
    TableColumn<DJ, String> formacionColumn;

    private ObservableList<DJ> djList;

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
        fechaInicioColumn.setCellValueFactory(new PropertyValueFactory<>("dataInicio"));
        fechaNacimientoColumn.setCellValueFactory(new PropertyValueFactory<>("dataNacemento"));
        formacionColumn.setCellValueFactory(new PropertyValueFactory<>("formacion"));


        djList = FXCollections.observableList(DataBase.getCurrentDB().getAllDj());

        djTable.setItems(djList);
    }


    /**
     * Removes the selected users from the database and the observable list.
     * This method is triggered when the "Eliminar usuario" button is clicked.
     */
    public void deleteDj() throws SQLException {
        // Get the selected shows from the table view
        DJ selectedDj = djTable.getSelectionModel().getSelectedItem();

        // Only delete the show if one is selected
        if (selectedDj != null) {
            // Remove the shows from the database
            DataBase.getCurrentDB().borrarDj(selectedDj);

            // Remove the shows from the observable list
            djList.remove(selectedDj);
        }
    }
}