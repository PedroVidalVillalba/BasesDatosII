package gui.principal;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import java.io.IOException;
/**
 * Clase para cambiar de escena
 *
 */
public class SceneController {

    private Stage stage;
    private Scene scene;
    private Pane root;

    //Ride = Atracción



    public void switchToLogin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToRide(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Ride.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToRestaurant(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Restaurant.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToRating(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Rating.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToShow(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Show.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
