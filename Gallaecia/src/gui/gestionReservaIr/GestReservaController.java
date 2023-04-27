package gui.gestionReservaIr;

import baseDatos.DataBase;
import baseDatos.ReservasIrDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.ReservaIrAtraccion;
import modelo.ReservaXantar;

import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.ResourceBundle;

public class GestReservaController implements Initializable {

    @FXML
    private TableView<ReservaIrAtraccion> tablaReservas;
    @FXML
    private TableColumn<ReservaIrAtraccion, String> visitanteColumn;
    @FXML
    private TableColumn<ReservaIrAtraccion, String> atraccionColumn;
    @FXML
    private TableColumn<ReservaIrAtraccion, Date> fechaColumn;
    @FXML
    private TableColumn<ReservaIrAtraccion, Time> horaInicioColumn;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        visitanteColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        atraccionColumn.setCellValueFactory(new PropertyValueFactory<>("atraccion"));
        fechaColumn.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        horaInicioColumn.setCellValueFactory(new PropertyValueFactory<>("horaInicio"));

        List<ReservaIrAtraccion> reservas = DataBase.getCurrentDB().getAllReservasIr();
        for (ReservaIrAtraccion r : reservas) {
            if (!r.getNombre().equals(DataBase.getCurrentDB().getUser().getDni())) {
                reservas.remove(r);
            }
        }
        ObservableList<ReservaIrAtraccion> listaReservas = FXCollections.observableList(reservas);

        tablaReservas.setItems(listaReservas);
    }

    public void eliminarReserva(javafx.event.ActionEvent actionEvent) {
            ReservaIrAtraccion selectedItem = tablaReservas.getSelectionModel().getSelectedItem();

            if (selectedItem != null) {
                // Elimina la fila seleccionada de la tabla
                tablaReservas.getItems().remove(selectedItem);
                //DataBase.getCurrentDB().borrarReserva();

                // Actualiza la vista de la tabla
                tablaReservas.refresh();
                DataBase.getCurrentDB().borrarReservaIr(selectedItem);
            }

    }
}
