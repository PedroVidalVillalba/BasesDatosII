package gui.reserva;

import baseDatos.DataBase;
import gui.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import modelo.Hostalaria;
import modelo.Reserva;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

import static gui.restaurant.RestaurantController.restauranteElegido;

public class ReservaController implements Initializable {

    @FXML
    private ComboBox<Time> horaComboBox;
    @FXML
    private DatePicker dateDatePicker;
    @FXML
    private Text errorText;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<Time> tiempos = new ArrayList<>();
        for (int i=0; i<12; i++) {
            Time time = new Time(12+i,0,0);
            tiempos.set(i,time);
        }
        horaComboBox.getItems().setAll(tiempos);
        /*java.util.List<Hostalaria> hostalarias = DataBase.getCurrentDB().getAllRestaurants();
        ArrayList<String> restaurants = new ArrayList<>();
        for (Hostalaria h : hostalarias) {
            restaurants.add(h.getNomeEstablecemento());
        }
        restaurantComboBox.getItems().setAll(restaurants);*/


    }

    public void NuevaReserva(ActionEvent event) throws IOException {
        if (DataBase.getCurrentDB().getUser()!=null) {
            String nombre = DataBase.getCurrentDB().getUser().getUsername();
            String restaurant = restauranteElegido.getNomeEstablecemento();
            LocalDate date = dateDatePicker.getValue();
            Time horaInicio = horaComboBox.getValue();
            Date date2 = Date.valueOf(date); // Magic happens here!

            Reserva reserva = new Reserva(nombre, restaurant, horaInicio, date2);
            try {
                DataBase.getCurrentDB().insertarReserva(reserva);
                SceneManager.getSceneManager().switchScene("./ReservaExito/ReservaExito.fxml");
            } catch (SQLException e){
                errorText.setVisible(true);
            }
        }


    }
}
