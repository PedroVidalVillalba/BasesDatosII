package gui.restaurant.reserva;

import baseDatos.DataBase;
import gui.SceneManager;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import modelo.ReservaXantar;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.util.*;

import static gui.restaurant.RestaurantController.restauranteElegido;

/**
 * Clase controlador del patrón Modelo-Vista-Controlador. Tiene asociada una vista del mismo nombre
 * Controla la vista de reservas de restaurantes
 */
public class ReservaController implements Initializable {

    @FXML
    private ComboBox<Time> horaComboBox;
    @FXML
    private DatePicker dateDatePicker;
    @FXML
    private Label errorLabel;

    /** Inicialización de la vista */
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

    /**
     * Recoge los datos escritos por el usuario e intenta crear una nueva reserva
     * En caso de éxito, cambia a la escena de Reserva con éxito
     */
    public void nuevaReserva() {
        if (DataBase.getCurrentDB().getUser()!=null) {
            String nombre = DataBase.getCurrentDB().getUser().getDni();
            String restaurant = restauranteElegido.getNomeEstablecemento();
            LocalDate date = dateDatePicker.getValue();
            LocalDate today = LocalDate.now();
            if (date == null || !(date.isAfter(today) || date.isEqual(today))) {
                errorLabel.setVisible(true);
            } else {
                Time horaInicio = horaComboBox.getValue();
                Date date2 = Date.valueOf(date); // Magic happens here!

                ReservaXantar reserva = new ReservaXantar(nombre, restaurant, horaInicio, date2);
                try {
                    DataBase.getCurrentDB().insertarReservaXantar(reserva);
                    SceneManager.getSceneManager().switchScene("./restaurant/reserva/ReservaExito.fxml");
                } catch (SQLException e){
                    errorLabel.setVisible(true);
                }
            }
        }
    }

    public void switchToRestaurant() {
        SceneManager.getSceneManager().switchScene("./restaurant/Restaurant.fxml");
    }
}
