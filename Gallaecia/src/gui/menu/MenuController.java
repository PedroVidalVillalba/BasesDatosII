package gui.menu;

import baseDatos.DataBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class MenuController {
	@FXML
	public Menu menuIniciarSesion;
	@FXML
	private MenuBar menuBar;//NOTA: no basta con poner @FXML, necesitas en el fxml poner "fx:id="menuBar"

	private void switchScene(String FXMLFileName) throws IOException {
		Parent content = FXMLLoader.load(getClass().getResource(FXMLFileName));
		VBox root = (VBox) menuBar.getScene().getRoot();
		root.getChildren().set(1, content);
	}

	public void switchToLogin(MouseEvent event) throws IOException {
		if (DataBase.getCurrentDB().getUser() == null) {
			switchScene("../login/Login.fxml");
		} else {
			switchScene("../login/LoginSuccessful.fxml");
		}
	}

	public void switchToPrincipal(MouseEvent event) throws IOException {
		switchScene("../principal/Principal.fxml");
	}

	public void switchToRide(ActionEvent event) throws IOException {
		switchScene("../ride/Ride.fxml");
	}

	public void switchToRestaurant(ActionEvent event) throws IOException {
		switchScene("../restaurant/Restaurant.fxml");
	}
	public void switchToEspectaculo(ActionEvent event) throws IOException {
		switchScene("../espectaculo/Espectaculo.fxml");
	}
	public void switchToRating(MouseEvent event) throws IOException {
		switchScene("../general/Rating.fxml");
	}
    public void switchToChat(MouseEvent event) throws IOException {
        switchScene("../chatGPT/Chat.fxml");
    }
}
