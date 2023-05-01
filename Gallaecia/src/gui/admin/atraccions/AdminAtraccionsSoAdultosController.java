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
import modelo.AtraccionSoAdultos;
import modelo.Zona;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AdminAtraccionsSoAdultosController implements Initializable {

    @FXML
    TableView<AtraccionSoAdultos> atraccionSoAdultosTable;

    @FXML
    TableColumn<AtraccionSoAdultos, String> nomeColumn;
    @FXML
    TableColumn<AtraccionSoAdultos, Integer> idadeMinColumn;

    private ObservableList<AtraccionSoAdultos> atraccionSoAdultosList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nomeColumn.setCellValueFactory(new PropertyValueFactory<>("atraccion"));
        idadeMinColumn.setCellValueFactory(new PropertyValueFactory<>("idadeMin"));
        atraccionSoAdultosList = FXCollections.observableList(DataBase.getCurrentDB().getAllAtraccionSoAdultos());

        atraccionSoAdultosTable.setItems(atraccionSoAdultosList);
    }


    /**
     * Removes the selected users from the database and the observable list.
     * This method is triggered when the "Eliminar usuario" button is clicked.
     */
    public void deleteAtraccionSoAdultos() throws SQLException {
        // Get the selected shows from the table view
        AtraccionSoAdultos selectedAtraccion = atraccionSoAdultosTable.getSelectionModel().getSelectedItem();

        // Only delete the show if one is selected
        if (selectedAtraccion != null) {
            // Remove the shows from the database
            DataBase.getCurrentDB().borrarAtraccionSoAdultos(selectedAtraccion);

            // Remove the shows from the observable list
            atraccionSoAdultosList.remove(selectedAtraccion);
        }
    }
}