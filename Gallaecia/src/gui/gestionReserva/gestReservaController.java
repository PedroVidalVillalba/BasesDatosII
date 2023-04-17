package gui.gestionReserva;

import baseDatos.ReservasDAO;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.Reserva;

import javafx.collections.FXCollections;


import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
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
    private TableColumn<Reserva, String> horaInicioColumn;
    @FXML
    private TableColumn<Reserva, String> horaFinColumn;

    private ReservasDAO reservasDAO;
    private Connection connection;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        personaColumn.setCellValueFactory(new PropertyValueFactory<>("Persona"));
        hostalariaColumn.setCellValueFactory(new PropertyValueFactory<>("Establecimiento"));
        fechaColumn.setCellValueFactory(new PropertyValueFactory<>("Fecha"));
        horaInicioColumn.setCellValueFactory(new PropertyValueFactory<>("Hora inicio"));
        horaFinColumn.setCellValueFactory(new PropertyValueFactory<>("Hora fin"));

        //tablaReservas.setPlaceholder(new Label());  // Dejar vacío el texto por defecto cuando la tabla no tiene elementos
        connection= reservasDAO.getConnection();
        reservasDAO = new ReservasDAO(connection);
        List<Reserva> reservas = reservasDAO.getAllReservas();
        ObservableList<Reserva> listaReservas = FXCollections.observableArrayList(reservas);
        tablaReservas.setItems(listaReservas);


        /*reservasDAO = new ReservasDAO(connection);
        List<Reserva> reservas = reservasDAO.getAllReservas();
        ObservableList<Reserva> listaReservas = FXCollections.observableArrayList(reservas);
        tablaReservas.setItems(listaReservas);*/
    }




}
