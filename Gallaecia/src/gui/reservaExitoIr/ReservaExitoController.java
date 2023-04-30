package gui.reservaExitoIr;

import gui.SceneManager;
import javafx.event.ActionEvent;

import java.io.IOException;

/**
 * Clase controlador del patrón Modelo-Vista-Controlador. Tiene asociada una vista del mismo nombre
 * Controla la vista de reservas de atracciones con éxito
 */
public class ReservaExitoController{
    /**
     * Cambio a la escena de Atracciones
     * @param event Click en "Volver"
     * @throws IOException
     */
    public void switchToRestaurant(ActionEvent event) throws IOException {
        SceneManager.getSceneManager().switchScene("./ride/Ride.fxml");
    }

}
