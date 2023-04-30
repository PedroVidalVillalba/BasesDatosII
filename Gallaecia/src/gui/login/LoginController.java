package gui.login;

import baseDatos.DataBase;
import gui.SceneManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import modelo.User;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Clase controlador del patrón Modelo-Vista-Controlador. Tiene asociadas dos vistas, una para el inicio de sesión y otra para el cierre de sesión
 * Controla la vista de inicio de sesión
 */
public class LoginController implements Initializable {
	@FXML
	private PasswordField passwordField;
	@FXML
	private TextField usernameField;
	@FXML
	private Label errorLabel;
	@FXML
	private Label greetingsLabel;
	@FXML
	private Label logoutLabel;

	/** Inicialización de la vista */
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		User user = DataBase.getCurrentDB().getUser();
		if (user != null) {
			greetingsLabel.setText("Bienvenido " + user.getNome() + ".");
		}
	}

	/**
	 * Método que recoge los datos de usuario y contraseña escritos por el usuario y gestiona el inicio de sesión
	 */
	public void login() {
		String username = usernameField.getText();
		String password = passwordField.getText();

		boolean success = DataBase.getCurrentDB().login(username, password);

		if (success) {
			SceneManager.getSceneManager().switchScene("./login/Logout.fxml");
			SceneManager.getSceneManager().refreshMenu();
		}
		else {
			errorLabel.setVisible(true);
			usernameField.getStyleClass().add("error-text-field");
			passwordField.getStyleClass().add("error-text-field");
		}
	}

	/**
	 * Método que permite cerrar la sesión iniciada por un usuario
	 */
	public void logout() {
		DataBase.getCurrentDB().logout();
		SceneManager.getSceneManager().refreshMenu();
		logoutLabel.setVisible(true);
	}

	/**
	 * Método que permite el registro de nuevos usuarios. Cambia a la escena de Registrarse
	 */
	public void signUp() {
		SceneManager.getSceneManager().switchScene("./signUp/SignUp.fxml");
	}

}
