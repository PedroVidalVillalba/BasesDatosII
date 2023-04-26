package gui.menu;

import baseDatos.DataBase;
import baseDatos.UserType;
import gui.SceneManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {
	@FXML
	private Menu adminMenu;
	@FXML
	private Label loginText;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		SceneManager.getSceneManager().setMenuController(this);
		this.refresh();
	}

	public void switchToLogin() {
		if (DataBase.getCurrentDB().getUser() == null) {
			SceneManager.getSceneManager().switchScene("./login/Login.fxml");
		} else {
			SceneManager.getSceneManager().switchScene("./login/LoginSuccessful.fxml");
		}
	}

	public void switchToPrincipal() {
		SceneManager.getSceneManager().switchScene("./principal/Principal.fxml");
	}

	public void switchToRide() {
		SceneManager.getSceneManager().switchScene("./ride/Ride.fxml");
	}

	public void switchToRestaurant() {
		SceneManager.getSceneManager().switchScene("./restaurant/Restaurant.fxml");
	}
	public void switchToEspectaculo() {
		SceneManager.getSceneManager().switchScene("./espectaculo/Espectaculo.fxml");
	}
	public void switchToRating() {
		SceneManager.getSceneManager().switchScene("./rating/Rating.fxml");
	}
    public void switchToChat() {
        SceneManager.getSceneManager().switchScene("./chatGPT/Chat.fxml");
    }

	public void switchToAdmin() {
		SceneManager.getSceneManager().switchScene("./admin/Admin.fxml");
	}

	public void refresh() {
		adminMenu.setVisible(DataBase.getCurrentDB().getUserType() == UserType.Admin);
		loginText.setText(DataBase.getCurrentDB().getUser() == null ? "Iniciar sesión" : "Cerrar sesión");
	}

}
