package gui.admin.atraccions;

import baseDatos.DataBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.Atraccion;
import modelo.AtraccionFamiliar;
import modelo.Zona;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AdminAtraccionsFamiliaresController implements Initializable {

    @FXML
    TableView<AtraccionFamiliar> atraccionsFamiliaresTable;

    @FXML
    TableColumn<AtraccionFamiliar, String> nomeColumn;
    @FXML
    TableColumn<AtraccionFamiliar, Integer> idadeRecomendadaColumn;

    private ObservableList<AtraccionFamiliar> atraccionsFamiliaresList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nomeColumn.setCellValueFactory(new PropertyValueFactory<>("atraccion"));
        idadeRecomendadaColumn.setCellValueFactory(new PropertyValueFactory<>("idadeRecomendada"));
        atraccionsFamiliaresList = FXCollections.observableList(DataBase.getCurrentDB().getAllAtraccionsFamiliares());

        atraccionsFamiliaresTable.setItems(atraccionsFamiliaresList);
    }


    /**
     * Removes the selected users from the database and the observable list.
     * This method is triggered when the "Eliminar usuario" button is clicked.
     */
    public void deleteAtraccionFamiliar() throws SQLException {
        // Get the selected shows from the table view
        AtraccionFamiliar selectedAtraccion = atraccionsFamiliaresTable.getSelectionModel().getSelectedItem();

        // Only delete the show if one is selected
        if (selectedAtraccion != null) {
            // Remove the shows from the database
            DataBase.getCurrentDB().borrarAtraccionsFamiliares(selectedAtraccion);

            // Remove the shows from the observable list
            atraccionsFamiliaresList.remove(selectedAtraccion);
        }
    }
}
