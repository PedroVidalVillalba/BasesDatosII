package gui.menu;

import baseDatos.DataBase;
import baseDatos.UserType;
import gui.SceneManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.image.ImageView;
import modelo.User;

import java.io.FileInputStream;
import java.net.URL;
import java.util.ResourceBundle;

import static baseDatos.UserType.Admin;
import static baseDatos.UserType.Guest;

/**
 * Clase controlador del patrón Modelo-Vista-Controlador. Tiene asociada una vista del mismo nombre
 * Controla la vista del menú principal
 */
public class MenuController implements Initializable {
	@FXML
	private Menu adminMenu;
	@FXML
	private Label loginText;

	/** Inicialización de la vista */
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		SceneManager.getSceneManager().setMenuController(this);
		this.refresh();
	}

	/**
	 * Cambio a la escena de inicio de sesión
	 */
	public void switchToLogin() {
		if (DataBase.getCurrentDB().getUser() == null) {
			SceneManager.getSceneManager().switchScene("./login/Login.fxml");
		} else {
			SceneManager.getSceneManager().switchScene("./login/Logout.fxml");
		}
	}

	/**
	 * Cambio a la escena principal
	 */
	public void switchToPrincipal() {
		SceneManager.getSceneManager().switchScene("./principal/Principal.fxml");
	}

	/**
	 * Cambio a la escena de atracciones
	 */
	public void switchToRide() {
		SceneManager.getSceneManager().switchScene("./ride/Ride.fxml");
	}

	/**
	 * Cambio a la escena de restaurantes
	 */
	public void switchToRestaurant() {
		SceneManager.getSceneManager().switchScene("./restaurant/Restaurant.fxml");
	}

	/**
	 * Cambio a la escena de espectáculos
	 */
	public void switchToEspectaculo() {
		SceneManager.getSceneManager().switchScene("./espectaculo/Espectaculo.fxml");
	}

	/**
	 * Cambio a la escena de valoraciones
	 */
	public void switchToRating() {
		SceneManager.getSceneManager().switchScene("./rating/Rating.fxml");
	}

	/**
	 * Cambio a la escena de chatGPT
	 */
    public void switchToChat() {
        SceneManager.getSceneManager().switchScene("./chatGPT/Chat.fxml");
    }

	/**
	 * Cambio a la escena de administración
	 */
	public void switchToAdmin() {
		SceneManager.getSceneManager().switchScene("./admin/Admin.fxml");
	}

	/**
	 * Comprueba si el usuario está o no registrado para adecuar el menú a ello
	 */
	public void refresh() {
		adminMenu.setVisible(DataBase.getCurrentDB().getUserType() == Admin);
		loginText.setText(DataBase.getCurrentDB().getUser() == null ? "Iniciar sesión" : "Cerrar sesión");
	}

}
