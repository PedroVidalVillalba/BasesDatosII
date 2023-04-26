package gui.rating.newRating;

import baseDatos.DataBase;
import gui.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import modelo.Reserva;
import modelo.Valoracion;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.sql.Date;

import static gui.restaurant.RestaurantController.restauranteElegido;

public class NewRatingController implements Initializable {

    @FXML
    private TextArea descriptionField;
    @FXML
    private Slider scoreField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {




    }

    public void newRating(ActionEvent event) throws IOException {
        if (DataBase.getCurrentDB().getUser()!=null) {
            DataBase.getCurrentDB().newRating(descriptionField.getText(), (int)scoreField.getValue());
        }

        SceneManager.getSceneManager().switchScene("./rating/Rating.fxml");
    }

    public void cancel(ActionEvent event) throws IOException {
        SceneManager.getSceneManager().switchScene("./rating/Rating.fxml");
    }


}
