package gui.ReservaExito;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;

public class ReservaExitoController{

    @FXML
    private Text myLabel;

    private void switchScene(String FXMLFileName) throws IOException {
        try {
            Parent content = FXMLLoader.load(getClass().getResource(FXMLFileName));
            VBox root = (VBox) myLabel.getScene().getRoot();
            root.getChildren().set(1, content);
        } catch (IOException exception) {
            System.err.println(exception.getMessage());
        }
    }

    public void switchToRestaurant(ActionEvent event) throws IOException {
        switchScene("../restaurant/Restaurant.fxml");
    }

}
