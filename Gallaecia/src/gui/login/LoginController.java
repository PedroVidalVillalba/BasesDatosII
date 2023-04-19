package gui.login;

import baseDatos.DataBase;
import gui.SceneManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;
import modelo.User;

import java.io.File;
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

	@FXML
	private MediaView mediaView;

	private File file;
	private Media media;
	private MediaPlayer mediaPlayer;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		User user = DataBase.getCurrentDB().getUser();
		if (user != null) {
			greetingsText.setText("Bienvenido " + user.getNome() + ".\nPuede cerrar sesión en cualquier momento pulsando el botón de abajo.");
		}

//		File file = new File("src/gui/principal/carrusel.mp4");
//		media = new Media(file.toURI().toString());
//		mediaPlayer = new MediaPlayer(media);
//		mediaView.setMediaPlayer(mediaPlayer);
//		mediaPlayer.play();
	}

	public void login() {
		String username = usernameField.getText();
		String password = passwordField.getText();

		boolean success = DataBase.getCurrentDB().login(username, password);

		if (success) {
			SceneManager.getSceneManager().switchScene("./login/LoginSuccessful.fxml");
			SceneManager.getSceneManager().refreshMenu();
		}
		else {
			resultText.setVisible(true);
		}
	}

	public void logout() {
		DataBase.getCurrentDB().logout();
		SceneManager.getSceneManager().refreshMenu();
		logoutText.setVisible(true);
	}

	public void signUp() {
		SceneManager.getSceneManager().switchScene("./signUp/SignUp.fxml");
	}

}
