package gui.reservaExitoAsistir;

import gui.SceneManager;
import javafx.event.ActionEvent;

import java.io.IOException;

/**
 * Clase controlador del patrón Modelo-Vista-Controlador. Tiene asociada una vista del mismo nombre
 * Controla la vista de reservas de espectáculos con éxito
 */
public class ReservaExitoController{
    /**
     * Cambio a la escena de Espectáculos
     * @param event Click en "Volver"
     * @throws IOException
     */
    public void switchToRestaurant(ActionEvent event) throws IOException {
        SceneManager.getSceneManager().switchScene("./espectaculo/Espectaculo.fxml");
    }

}
