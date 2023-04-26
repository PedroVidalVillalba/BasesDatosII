package gui.gestionReserva;

import baseDatos.DataBase;
import baseDatos.ReservasDAO;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.Reserva;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;

import javafx.collections.FXCollections;


import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.ResourceBundle;

public class gestReservaController implements Initializable {

    @FXML
    private TableView<Reserva> tablaReservas;
    @FXML
    private TableColumn<Reserva, String> personaColumn;
    @FXML
    private TableColumn<Reserva, String> hostalariaColumn;
    @FXML
    private TableColumn<Reserva, Date> fechaColumn;
    @FXML
    private TableColumn<Reserva, Time> horaInicioColumn;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        personaColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        hostalariaColumn.setCellValueFactory(new PropertyValueFactory<>("hostalaria"));
        fechaColumn.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        horaInicioColumn.setCellValueFactory(new PropertyValueFactory<>("horaInicio"));

        List<Reserva> reservas = DataBase.getCurrentDB().getAllReservas();
        for (Reserva r : reservas) {
            if (!r.getNombre().equals(DataBase.getCurrentDB().getUser().getUsername())) {
                reservas.remove(r);
            }
        }
        ObservableList<Reserva> listaReservas = FXCollections.observableList(reservas);

        tablaReservas.setItems(listaReservas);
    }

    public void eliminarReserva(javafx.event.ActionEvent actionEvent) {
            Reserva selectedItem = tablaReservas.getSelectionModel().getSelectedItem();

            if (selectedItem != null) {
                // Elimina la fila seleccionada de la tabla
                tablaReservas.getItems().remove(selectedItem);
                //DataBase.getCurrentDB().borrarReserva();

                // Actualiza la vista de la tabla
                tablaReservas.refresh();
                DataBase.getCurrentDB().borrarReserva(selectedItem);
            }

    }
}
