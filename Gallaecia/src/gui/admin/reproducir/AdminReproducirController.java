package gui.admin.reproducir;

import baseDatos.DataBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.Reproducir;

import java.net.URL;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Date;
import java.util.ResourceBundle;

public class AdminReproducirController implements Initializable {

    @FXML
    TableView<Reproducir> reproducirTable;

    @FXML
    TableColumn<Reproducir, Date> dataColumn;
    @FXML
    TableColumn<Reproducir, String> musicaColumn;
    @FXML
    TableColumn<Reproducir, Time> sistemaColumn;

    private ObservableList<Reproducir> reproducirList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dataColumn.setCellValueFactory(new PropertyValueFactory<>("data"));
        musicaColumn.setCellValueFactory(new PropertyValueFactory<>("musica"));
        sistemaColumn.setCellValueFactory(new PropertyValueFactory<>("sistema"));
        reproducirList = FXCollections.observableList(DataBase.getCurrentDB().getAllReproducir());

        reproducirTable.setItems(reproducirList);
    }


    /**
     * Removes from the database and the observable list.
     * This method is triggered when the "Eliminar registro" button is clicked.
     */
    public void deleteReproducir() throws SQLException {
        // Get the selected shows from the table view
        Reproducir selectedReproducir = reproducirTable.getSelectionModel().getSelectedItem();

        if (selectedReproducir != null) {
            // Remove from the database
            DataBase.getCurrentDB().borrarReproducir(selectedReproducir);

            // Remove from the observable list
            reproducirList.remove(selectedReproducir);
        }
    }
}
