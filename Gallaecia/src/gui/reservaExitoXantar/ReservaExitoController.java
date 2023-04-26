package gui.reservaExitoXantar;

import gui.SceneManager;
import javafx.event.ActionEvent;

import java.io.IOException;

public class ReservaExitoController{
    public void switchToRestaurant(ActionEvent event) throws IOException {
        SceneManager.getSceneManager().switchScene("./restaurant/Restaurant.fxml");
    }

}
