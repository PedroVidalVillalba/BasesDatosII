package gui.reservaExitoAsistir;

import gui.SceneManager;
import javafx.event.ActionEvent;

import java.io.IOException;

public class ReservaExitoController{
    public void switchToRestaurant(ActionEvent event) throws IOException {
        SceneManager.getSceneManager().switchScene("./espectaculo/Espectaculo.fxml");
    }

}
