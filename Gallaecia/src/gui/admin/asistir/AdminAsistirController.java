package gui.admin.asistir;

import baseDatos.DataBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.Asistir;
import modelo.Atraccion;
import modelo.Espectaculo;
import modelo.Zona;

import java.net.URL;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Date;
import java.util.ResourceBundle;

public class AdminAsistirController implements Initializable {

    @FXML
    TableView<Asistir> asistirTable;

    @FXML
    TableColumn<Asistir, String> visitanteColumn;
    @FXML
    TableColumn<Asistir, Espectaculo> espectaculoColumn;
    @FXML
    TableColumn<Asistir, Time> horaInicioColumn;
    @FXML
    TableColumn<Asistir, Date> fechaColumn;


    private ObservableList<Asistir> asistirList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        visitanteColumn.setCellValueFactory(new PropertyValueFactory<>("visitante"));
        espectaculoColumn.setCellValueFactory(new PropertyValueFactory<>("espectaculo"));
        horaInicioColumn.setCellValueFactory(new PropertyValueFactory<>("horaInicio"));
        fechaColumn.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        asistirList = FXCollections.observableList(DataBase.getCurrentDB().getAllAsistir());

        asistirTable.setItems(asistirList);
    }


    /**
     * Removes the selected users from the database and the observable list.
     * This method is triggered when the "Eliminar usuario" button is clicked.
     */
    public void deleteAsistir() throws SQLException {
        // Get the selected shows from the table view
        Asistir selectedAsistir = asistirTable.getSelectionModel().getSelectedItem();

        // Only delete the show if one is selected
        if (selectedAsistir != null) {
            // Remove the shows from the database
            DataBase.getCurrentDB().borrarAsistir(selectedAsistir);

            // Remove the shows from the observable list
            asistirList.remove(selectedAsistir);
        }
    }
}
