package gui.espectaculo;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import baseDatos.DataBase;
import gui.SceneManager;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

import javafx.scene.text.Text;
import javafx.util.Callback;
import modelo.Espectaculo;

/**
 * Clase controlador del patrón Modelo-Vista-Controlador. Tiene asociadas dos vistas, una para invitados y otra para administración
 * Controla la vista de administración de atracciones
 */
public class EspectaculoController implements Initializable {

    public static Espectaculo espectaculoElegido;

    @FXML
    private ListView<Espectaculo> myListView;
    @FXML
    private Label myLabel;
    @FXML
    private Button nuevoEspectaculo;
    @FXML
    private Label errorMensaje;
    @FXML
    private Label errorNull;

    private java.util.List<Espectaculo> espectaculos;

    /** Inicialización de la vista */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        espectaculos = DataBase.getCurrentDB().getAllEspectaculos();

        for (Espectaculo espectaculo : espectaculos) {
            myListView.getItems().add(espectaculo);
        }

        // Para mostrar el nombre de cada espectáculo en la ListView.
        myListView.setCellFactory(new Callback<ListView<Espectaculo>, ListCell<Espectaculo>>() {
            @Override
            public ListCell<Espectaculo> call(ListView<Espectaculo> espectaculoListView) {
                return new ListCell<Espectaculo>() {
                    @Override
                    protected void updateItem(Espectaculo espectaculo, boolean b) {
                        super.updateItem(espectaculo, b);
                        if (espectaculo != null) {
                            setText(espectaculo.getNome());
                        }
                    }
                };
            }
        });

        myListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Espectaculo>() {
            @Override
            public void changed(ObservableValue<? extends Espectaculo> observableValue, Espectaculo s, Espectaculo t1) {

                String descripcion = "Temática: " + t1.getTematica() + ".\n" +
                        "Descripción: " + t1.getDescricion() + ".\n" +
                        "Hora apertura: " + t1.getHoraInicio() + "\n\n" +
                        "Hora cierre: " + t1.getHoraFin() + "\n\n" +
                        "Ubicación: " + t1.getZona().getNome() + ".\n" +
                        "Latitud: " + t1.getZona().getCoordenadaX() + "\n" +
                        "Longitud: " + t1.getZona().getCoordenadaY();

                myLabel.setText(descripcion);
            }

        });
    }

    /**
     * Cambio a la vista de añadir espectáculos (solo para administradores)
     */
    public void nuevoEspectaculo(){
        SceneManager.getSceneManager().switchScene("./nuevoEspectaculo/NuevoEspectaculo.fxml");
    }

    /**
     * Cambio a la escena de hacer una nueva reserva (solo para usuarios registrados)
     * @param event Click en el botón "Hacer una reserva"
     * @throws IOException
     */
    public void switchToNuevaReserva(javafx.event.ActionEvent event) throws IOException {
        if (DataBase.getCurrentDB().getUser()!=null) {
            if (myListView.getSelectionModel().getSelectedItem()!=null) {
                espectaculoElegido = myListView.getSelectionModel().getSelectedItem();
                SceneManager.getSceneManager().switchScene("./reservaAsistir/Reserva.fxml");
            } else {
                errorNull.setVisible(true);
            }
        } else {
            errorMensaje.setVisible(true);
        }
    }

    /**
     * Cambio a la escena de Eliminar reserva (solo para usuarios registrados)
     * @param event Click en el botón "Eliminar una reserva"
     * @throws IOException
     */
    public void switchToEliminarReserva(ActionEvent event) throws IOException {
        if (DataBase.getCurrentDB().getUser()!=null) {
            SceneManager.getSceneManager().switchScene("./gestionReservaAsistir/GestionReserva.fxml");
        } else {
            errorMensaje.setVisible(true);
        }

    }


}
