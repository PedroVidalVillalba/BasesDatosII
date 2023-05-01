package gui.admin.ir;

import baseDatos.DataBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.Ir;

import java.net.URL;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Date;
import java.util.ResourceBundle;

public class AdminIrController implements Initializable {

    @FXML
    TableView<Ir> irTable;

    @FXML
    TableColumn<Ir, String> visitanteColumn;
    @FXML
    TableColumn<Ir, String> atraccionColumn;
    @FXML
    TableColumn<Ir, Time> horaInicioColumn;
    @FXML
    TableColumn<Ir, Date> fechaColumn;


    private ObservableList<Ir> irList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        visitanteColumn.setCellValueFactory(new PropertyValueFactory<>("visitante"));
        atraccionColumn.setCellValueFactory(new PropertyValueFactory<>("atraccion"));
        horaInicioColumn.setCellValueFactory(new PropertyValueFactory<>("horaInicio"));
        fechaColumn.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        irList = FXCollections.observableList(DataBase.getCurrentDB().getAllIr());

        irTable.setItems(irList);
    }


    /**
     * Removes from the database and the observable list.
     * This method is triggered when the "Eliminar asistencia" button is clicked.
     */
    public void deleteIr() throws SQLException {
        // Get the selected shows from the table view
        Ir selectedIr = irTable.getSelectionModel().getSelectedItem();

        if (selectedIr != null) {
            // Remove from the database
            DataBase.getCurrentDB().borrarIr(selectedIr);

            // Remove from the observable list
            irList.remove(selectedIr);
        }
    }
}
