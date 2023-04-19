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
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

public class ReservaController implements Initializable {

    @FXML
    private TextField personaTextField;
    @FXML
    private TextField horaInicioTextField;
    @FXML
    private TextField horaFinTextField;
    @FXML
    private ComboBox<String> restaurantComboBox;
    @FXML
    private DatePicker dateDatePicker;
    @FXML
    private Text errorText;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        java.util.List<Hostalaria> hostalarias = DataBase.getCurrentDB().getAllRestaurants();
        ArrayList<String> restaurants = new ArrayList<>();
        for (Hostalaria h : hostalarias) {
            restaurants.add(h.getNomeEstablecemento());
        }
        restaurantComboBox.getItems().setAll(restaurants);
    }

    public void NuevaReserva(ActionEvent event) throws IOException {
        String nombre = personaTextField.getText();
        String restaurant = restaurantComboBox.getValue();
        LocalDate date = dateDatePicker.getValue();
        String horaInicio = horaInicioTextField.getText();
        String horaFin = horaFinTextField.getText();
            Date date2 = Date.valueOf(date); // Magic happens here!

            Reserva reserva = new Reserva(nombre, restaurant, horaInicio, horaFin, date2);
            try {
                DataBase.getCurrentDB().insertarReserva(reserva);
                SceneManager.getSceneManager().switchScene("./ReservaExito/ReservaExito.fxml");
            } catch (SQLException e){
                errorText.setVisible(true);
            }


    }
}
