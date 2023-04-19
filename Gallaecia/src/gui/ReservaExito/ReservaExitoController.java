package gui.ReservaExito;

import gui.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;

public class ReservaExitoController{
    public void switchToRestaurant(ActionEvent event) throws IOException {
        SceneManager.getSceneManager().switchScene("./restaurant/Restaurant.fxml");
    }

}
