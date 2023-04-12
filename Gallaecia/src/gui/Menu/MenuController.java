package gui.Menu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {
    @FXML
    private MenuBar menuBar;//NOTA: no basta con poner @FXML, necesitas en el fxml poner " fx:id="menuBar" "

    private Stage stage;
    private Scene scene;
    private Pane root;

    //Ride = Atracción



    public void switchToLogin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../login/Login.fxml"));//Tenemos que indicar el paquete, si no, no encuentra la referencia
        stage = (Stage) menuBar.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToRegister(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../login/Register.fxml"));
        stage = (Stage) menuBar.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public void switchToRide(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../general/Ride.fxml"));
        stage = (Stage) menuBar.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToRestaurant(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../general/Restaurant.fxml"));
        stage = (Stage) menuBar.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToRating(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../general/Rating.fxml"));
        stage = (Stage) menuBar.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToShow(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../general/Show.fxml"));
        stage = (Stage) menuBar.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
