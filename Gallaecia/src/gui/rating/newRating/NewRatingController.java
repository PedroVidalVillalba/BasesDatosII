package gui.rating.newRating;

import baseDatos.DataBase;
import gui.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Clase controlador del patrón Modelo-Vista-Controlador. Tiene asociada una vista del mismo nombre
 * Controla la vista de Nuevas valoraciones
 */
public class NewRatingController implements Initializable {

    @FXML
    private TextArea descriptionField;
    @FXML
    private Slider scoreField;

    /** Inicialización de la vista */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    /**
     * Recoge los datos escritos por el usuario e intenta crear una nueva reseña con ellos
     * Luego, cambia a la escena de Valoraciones
     * @param event Click en "Aceptar"
     * @throws IOException
     */
    public void newRating(ActionEvent event) throws IOException {
        if (DataBase.getCurrentDB().getUser()!=null) {
            int score = (int)scoreField.getValue();
            if(score < 1 || score > 5) score = 5;
            DataBase.getCurrentDB().newRating(descriptionField.getText(), score);
        }

        SceneManager.getSceneManager().switchScene("./rating/Rating.fxml");
    }

    /**
     * Ignora los datos introducidos por el usuario y cambia a la escena de Valoraciones
     * @param event Click en "Cancelar"
     * @throws IOException
     */
    public void cancel(ActionEvent event) throws IOException {
        SceneManager.getSceneManager().switchScene("./rating/Rating.fxml");
    }


}
