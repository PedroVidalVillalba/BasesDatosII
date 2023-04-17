package gui.gestionReserva;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import modelo.Reserva;

import java.sql.Date;

public class gestReservaController {

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




}
