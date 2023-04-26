package gui.gestionReservaXantar;

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
import java.sql.Time;
import java.util.List;
import java.util.ResourceBundle;

public class GestReservaController implements Initializable {

    @FXML
    private TableView<ReservaXantar> tablaReservas;
    @FXML
    private TableColumn<ReservaXantar, String> personaColumn;
    @FXML
    private TableColumn<ReservaXantar, String> hostalariaColumn;
    @FXML
    private TableColumn<ReservaXantar, Date> fechaColumn;
    @FXML
    private TableColumn<ReservaXantar, Time> horaInicioColumn;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        personaColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        hostalariaColumn.setCellValueFactory(new PropertyValueFactory<>("hostalaria"));
        fechaColumn.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        horaInicioColumn.setCellValueFactory(new PropertyValueFactory<>("horaInicio"));

        List<ReservaXantar> reservas = DataBase.getCurrentDB().getAllReservas();
        for (ReservaXantar r : reservas) {
            if (!r.getNombre().equals(DataBase.getCurrentDB().getUser().getUsername())) {
                reservas.remove(r);
            }
        }
        ObservableList<ReservaXantar> listaReservas = FXCollections.observableList(reservas);

        tablaReservas.setItems(listaReservas);
    }

    public void eliminarReserva(javafx.event.ActionEvent actionEvent) {
            ReservaXantar selectedItem = tablaReservas.getSelectionModel().getSelectedItem();

            if (selectedItem != null) {
                // Elimina la fila seleccionada de la tabla
                tablaReservas.getItems().remove(selectedItem);
                //DataBase.getCurrentDB().borrarReserva();

                // Actualiza la vista de la tabla
                tablaReservas.refresh();
                DataBase.getCurrentDB().borrarReservaXantar(selectedItem);
            }

    }
}
