package gui.restaurant.gestionReserva;

import baseDatos.DataBase;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.ReservaXantar;
import javafx.collections.FXCollections;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.List;
import java.util.ResourceBundle;

public class GestionReservaController implements Initializable {

    @FXML
    private TableView<ReservaXantar> tablaReservas;
    @FXML
    private TableColumn<ReservaXantar, String> visitanteColumn;
    @FXML
    private TableColumn<ReservaXantar, String> hostalariaColumn;
    @FXML
    private TableColumn<ReservaXantar, Date> fechaColumn;
    @FXML
    private TableColumn<ReservaXantar, Time> horaInicioColumn;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        visitanteColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        hostalariaColumn.setCellValueFactory(new PropertyValueFactory<>("hostalaria"));
        fechaColumn.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        horaInicioColumn.setCellValueFactory(new PropertyValueFactory<>("horaInicio"));

        List<ReservaXantar> reservas = null;
        try {
            reservas = DataBase.getCurrentDB().getAllReservasDNI(DataBase.getCurrentDB().getUser());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ObservableList<ReservaXantar> listaReservas = FXCollections.observableList(reservas);

        tablaReservas.setItems(listaReservas);
    }

    public void eliminarReserva() {
            ReservaXantar selectedItem = tablaReservas.getSelectionModel().getSelectedItem();

            if (selectedItem != null) {
                // Elimina la fila seleccionada de la tabla
                tablaReservas.getItems().remove(selectedItem);

                // Actualiza la vista de la tabla
                tablaReservas.refresh();
                DataBase.getCurrentDB().borrarReservaXantar(selectedItem);
            }
    }
}
