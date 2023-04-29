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

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		User user = DataBase.getCurrentDB().getUser();
		if (user != null) {
			greetingsLabel.setText("Bienvenido " + user.getNome() + ".");
		}
	}

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

	public void logout() {
		DataBase.getCurrentDB().logout();
		SceneManager.getSceneManager().refreshMenu();
		logoutLabel.setVisible(true);
	}

	public void signUp() {
		SceneManager.getSceneManager().switchScene("./signUp/SignUp.fxml");
	}

}
