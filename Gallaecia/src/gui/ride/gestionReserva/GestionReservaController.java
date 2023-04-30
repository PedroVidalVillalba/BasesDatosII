package gui.ride.gestionReserva;

import baseDatos.DataBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.ReservaIrAtraccion;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Clase controlador del patrón Modelo-Vista-Controlador. Tiene asociada una vista del mismo nombre
 * Controla la vista de gestión de reservas de atracciones
 */
public class GestionReservaController implements Initializable {

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


    /** Inicialización de la vista */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        visitanteColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        atraccionColumn.setCellValueFactory(new PropertyValueFactory<>("atraccion"));
        fechaColumn.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        horaInicioColumn.setCellValueFactory(new PropertyValueFactory<>("horaInicio"));

        List<ReservaIrAtraccion> reservas = null;
        try {
            reservas = DataBase.getCurrentDB().getAllReservasIrDNI(DataBase.getCurrentDB().getUser());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        /*for (ReservaIrAtraccion r : reservas) {
            if (!r.getNombre().equals(DataBase.getCurrentDB().getUser().getDni())) {
                reservas.remove(r);
            }
        }*/
        ObservableList<ReservaIrAtraccion> listaReservas = FXCollections.observableList(reservas);

        tablaReservas.setItems(listaReservas);
    }

    /**
     * Eliminación de una reserva seleccionada
     * @param actionEvent Click en el botón "Eliminar"
     */
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
