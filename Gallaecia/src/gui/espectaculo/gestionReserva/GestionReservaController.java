package gui.espectaculo.gestionReserva;

import baseDatos.DataBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.ReservaAsistir;

import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Clase controlador del patrón Modelo-Vista-Controlador. Tiene asociada una vista del mismo nombre
 * Controla la vista de gestión de reservas de espectáculos
 */
public class GestionReservaController implements Initializable {

    @FXML
    private TableView<ReservaAsistir> tablaReservas;
    @FXML
    private TableColumn<ReservaAsistir, String> visitanteColumn;
    @FXML
    private TableColumn<ReservaAsistir, String> espectaculoColumn;
    @FXML
    private TableColumn<ReservaAsistir, Date> fechaColumn;
    @FXML
    private TableColumn<ReservaAsistir, Time> horaInicioColumn;


    /** Inicialización de la vista */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        visitanteColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        espectaculoColumn.setCellValueFactory(new PropertyValueFactory<>("espectaculo"));
        fechaColumn.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        horaInicioColumn.setCellValueFactory(new PropertyValueFactory<>("horaInicio"));

        List<ReservaAsistir> reservas = DataBase.getCurrentDB().getAllReservasAsistirDNI(DataBase.getCurrentDB().getUser());
        ObservableList<ReservaAsistir> listaReservas = FXCollections.observableList(reservas);

        tablaReservas.setItems(listaReservas);
    }

    /**
     * Eliminación de una reserva seleccionada
     * @param actionEvent Click en el botón "Eliminar"
     */
    public void eliminarReserva(javafx.event.ActionEvent actionEvent) {
            ReservaAsistir selectedItem = tablaReservas.getSelectionModel().getSelectedItem();

            if (selectedItem != null) {
                // Elimina la fila seleccionada de la tabla
                tablaReservas.getItems().remove(selectedItem);

                // Actualiza la vista de la tabla
                tablaReservas.refresh();
                DataBase.getCurrentDB().borrarReservaAsistir(selectedItem);
            }
    }
}
