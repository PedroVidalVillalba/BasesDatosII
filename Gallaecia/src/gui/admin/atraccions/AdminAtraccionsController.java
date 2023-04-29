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
import modelo.Zona;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AdminAtraccionsController implements Initializable {

    @FXML
    TableView<Atraccion> atraccionsTable;

    @FXML
    TableColumn<Atraccion, String> nomeColumn;
    @FXML
    TableColumn<Atraccion, Integer> aforoColumn;
    @FXML
    TableColumn<Atraccion, Integer> alturaMinColumn;
    @FXML
    TableColumn<Atraccion, Float> custoMantamentoColumn;
    @FXML
    TableColumn<Atraccion, String> descricionColumn;
    @FXML
    TableColumn<Atraccion, Zona> zonaColumn;

    private ObservableList<Atraccion> atraccionsList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
        aforoColumn.setCellValueFactory(new PropertyValueFactory<>("aforo"));
        alturaMinColumn.setCellValueFactory(new PropertyValueFactory<>("alturaMin"));
        custoMantamentoColumn.setCellValueFactory(new PropertyValueFactory<>("custoMantemento"));
        descricionColumn.setCellValueFactory(new PropertyValueFactory<>("descricion"));
        zonaColumn.setCellValueFactory(new PropertyValueFactory<>("zona"));
        atraccionsList = FXCollections.observableList(DataBase.getCurrentDB().getAllRides());

        atraccionsTable.setItems(atraccionsList);
    }


    /**
     * Removes the selected users from the database and the observable list.
     * This method is triggered when the "Eliminar usuario" button is clicked.
     */
    public void deleteAtraccion() throws SQLException {
        // Get the selected shows from the table view
        Atraccion selectedAtraccion = atraccionsTable.getSelectionModel().getSelectedItem();

        // Only delete the show if one is selected
        if (selectedAtraccion != null) {
            // Remove the shows from the database
            DataBase.getCurrentDB().borrarAtraccion(selectedAtraccion);

            // Remove the shows from the observable list
            atraccionsList.remove(selectedAtraccion);
        }
    }
}
