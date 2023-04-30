package gui.espectaculo.reserva;

import baseDatos.DataBase;
import gui.SceneManager;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import modelo.ReservaAsistir;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static gui.espectaculo.EspectaculoController.espectaculoElegido;

public class ReservaController implements Initializable {

    @FXML
    private ComboBox<Time> horaComboBox;
    @FXML
    private DatePicker dateDatePicker;
    @FXML
    private Label errorLabel;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (horaComboBox != null) {
            ArrayList<Time> tiempos = new ArrayList<>();
            for (int i = 0; i < 11; i++) {
                Time time = new Time(12 + i, 0, 0);
                tiempos.add(i, time);
            }
            horaComboBox.getItems().setAll(FXCollections.observableList(tiempos));
        }
    }

    public void nuevaReserva() {
        if (DataBase.getCurrentDB().getUser()!=null) {
            String nombre = DataBase.getCurrentDB().getUser().getDni();
            String espectaculo = espectaculoElegido.getNome();
            LocalDate date = dateDatePicker.getValue();
            LocalDate today = LocalDate.now();
            if (date == null || !(date.isAfter(today) || date.isEqual(today))) {
                errorLabel.setVisible(true);
            } else {
                Time horaInicio = horaComboBox.getValue();
                Date date2 = Date.valueOf(date); // Magic happens here!

                ReservaAsistir reserva = new ReservaAsistir(nombre, espectaculo, horaInicio, date2);
                try {
                    DataBase.getCurrentDB().insertarReservaAsistir(reserva);
                    SceneManager.getSceneManager().switchScene("./espectaculo/reserva/ReservaExito.fxml");
                } catch (SQLException e){
                    errorLabel.setVisible(true);
                }
            }
        }
    }

    public void switchToEspectaculo() {
        SceneManager.getSceneManager().switchScene("./espectaculo/Espectaculo.fxml");
    }
}
