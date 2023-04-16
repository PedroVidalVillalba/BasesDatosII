package gui.login;

import baseDatos.DataBase;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import modelo.User;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
	@FXML
	private PasswordField passwordField;
	@FXML
	private TextField usernameField;
	@FXML
	private Button loginButton;
	@FXML
	private Button logoutButton;
	@FXML
	private Button signUpButton;
	@FXML
	private Text resultText;
	@FXML
	private Text greetingsText;
	@FXML
	private Text logoutText;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		User user = DataBase.getCurrentDB().getUser();
		if (user != null) {
			greetingsText.setText("Bienvenido " + user.getNome() + ".\nPuede cerrar sesión en cualquier momento pulsando el botón de abajo.");
		}
	}

	private void switchScene(String FXMLFileName) {
		try {
			Parent content = FXMLLoader.load(getClass().getResource(FXMLFileName));
			VBox root = (VBox) loginButton.getScene().getRoot();
			root.getChildren().set(1, content);
		} catch (IOException exception) {
			System.err.println(exception.getMessage());
		}
	}

	public void login() {
		String username = usernameField.getText();
		String password = passwordField.getText();

		boolean success = DataBase.getCurrentDB().login(username, password);

		if (success) {
			switchScene("../login/LoginSuccessful.fxml");
		}
		else {
			resultText.setVisible(true);
		}
	}

	public void logout() {
		DataBase.getCurrentDB().logout();
		logoutText.setVisible(true);
	}

	public void signUp() {
		switchScene("../signUp/SignUp.fxml");
	}

}
