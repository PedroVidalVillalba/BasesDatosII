package gui.rating;

import baseDatos.DataBase;
import gui.SceneManager;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.util.Callback;
import modelo.Valoracion;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Clase controlador del patrón Modelo-Vista-Controlador. Tiene asociada una vista del mismo nombre
 * Controla la vista de Valoraciones
 */
public class RatingController implements Initializable {
    @FXML
    private ListView<Valoracion> myListView;
    @FXML
    private Label errorLabel;
    @FXML
    private Label myLabel;

    /** Inicialización de la vista */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        java.util.List<Valoracion> valoracions = DataBase.getCurrentDB().getAllRatings();

        for (Valoracion valoracion : valoracions) {
            myListView.getItems().add(valoracion);
        }

        // Para mostrar el nombre de cada valoración en la ListView.
        myListView.setCellFactory(new Callback<>() {
            @Override
            public ListCell<Valoracion> call(ListView<Valoracion> valoracionListView) {
                return new ListCell<Valoracion>() {
                    @Override
                    protected void updateItem(Valoracion valoracion, boolean b) {
                        super.updateItem(valoracion, b);
                        if (valoracion != null) {
                            setText(valoracion.getVisitante().getNome());
                        }
                    }
                };
            }
        });

        myListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Valoracion>() {
            @Override
            public void changed(ObservableValue<? extends Valoracion> observableValue, Valoracion s, Valoracion t1) {

                String descripcion = "Fecha: " + t1.getData() + "\n" +
                        "Puntuación: " + t1.getPuntuacion() + "\u2B50\n" +
                        "Descripción: " + t1.getDescricion();
                myLabel.setText(descripcion);
            }
        });
    }

    /**
     * Cambio a la escena de Escribir una reseña (solo para usuarios registrados)
     */
    public void switchToNewRating() {
        if (DataBase.getCurrentDB().getUser()!=null) {
            SceneManager.getSceneManager().switchScene("./rating/newRating/NewRating.fxml");
        } else {
            errorLabel.setVisible(true);
        }
    }
}
