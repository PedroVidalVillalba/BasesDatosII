package gui.signUp;

import baseDatos.DataBase;
import gui.SceneManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import modelo.User;
import modelo.Visitante;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.sql.Date;

/**
 * Clase controlador del patrón Modelo-Vista-Controlador. Tiene asociada una vista del mismo nombre
 * Controla la vista de registrarse
 */
public class SignUpController implements Initializable {
	@FXML
	private TextField userField;
	@FXML
	private PasswordField passwordField;
	@FXML
	private PasswordField passwordField1;
	@FXML
	private TextField nameField;
	@FXML
	private TextField dniField;
	@FXML
	private TextField phoneField;
	@FXML
	private TextField heightField;
	@FXML
	private DatePicker birthDatePicker;
	@FXML
	private ComboBox<String> nationalityComboBox;
	@FXML
	private Label errorLabel;

	/** Inicialización de la vista */
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		String[] countries = Locale.getISOCountries();
		for (int i = 0; i < countries.length; i++) {
			Locale locale = new Locale("es", countries[i]);
			countries[i] = locale.getDisplayCountry();
		}
		Arrays.sort(countries);
		ObservableList<String> countryList = FXCollections.observableList(List.of(countries));
		nationalityComboBox.getItems().setAll(countryList);
	}

	/**
	 * Método que recoge todos los datos introducidos por el usuario e intenta registrar a un nuevo usuario en la base de datos
	 */
	public void signUp() {
		String nome = nameField.getText();
		String dni = dniField.getText();
		String telefono = phoneField.getText();
		Integer altura;
		Date dataNacemento = (birthDatePicker.getValue() == null ? null : Date.valueOf(birthDatePicker.getValue()));
		String nacionalidade = nationalityComboBox.getValue();

		String username = userField.getText();
		String password = passwordField.getText();
		String password1 = passwordField1.getText();

		errorLabel.setVisible(false);

		if (username.isBlank() || password.isBlank() || password1.isBlank()) {
			errorLabel.setText("Debes rellenar los campos de usuario y contraseña");
			errorLabel.setVisible(true);
			userField.getStyleClass().add("error-text-field");
			passwordField.getStyleClass().add("error-text-field");
			passwordField1.getStyleClass().add("error-text-field");
			return;
		} else {
			userField.getStyleClass().remove("error-text-field");
			passwordField.getStyleClass().remove("error-text-field");
			passwordField1.getStyleClass().remove("error-text-field");
		}

		if (!password.equals(password1)) {
			errorLabel.setText("Las contraseñas no coinciden");
			errorLabel.setVisible(true);
			passwordField.getStyleClass().add("error-text-field");
			passwordField1.getStyleClass().add("error-text-field");
			return;
		} else {
			passwordField.getStyleClass().remove("error-text-field");
			passwordField1.getStyleClass().remove("error-text-field");
		}

		if (nome.isBlank() || dni.isBlank()) {
			errorLabel.setText("Debes rellenar los campos de nombre y DNI");
			errorLabel.setVisible(true);
			nameField.getStyleClass().add("error-text-field");
			dniField.getStyleClass().add("error-text-field");
			return;
		} else {
			nameField.getStyleClass().remove("error-text-field");
			dniField.getStyleClass().remove("error-text-field");
		}

		try {
			altura = Integer.valueOf(heightField.getText());
			if (altura <= 0) {
				errorLabel.setText("La altura tiene que ser un número entero positivo");
				errorLabel.setVisible(true);
				heightField.getStyleClass().add("error-text-field");
				return;
			}
		} catch (NumberFormatException exception) {
			errorLabel.setText("La altura debe ser un número");
			errorLabel.setVisible(true);
			heightField.getStyleClass().add("error-text-field");
			return;
		}
		heightField.getStyleClass().remove("error-text-field");

		telefono = telefono.isBlank() ? null : telefono;

		Visitante visitante = new Visitante(dni, nome, nacionalidade, telefono, dataNacemento, altura, null);
		User user = new User(dni, nome, username, password, false);
		boolean success = DataBase.getCurrentDB().signUp(visitante, user);
		if (success) {
			DataBase.getCurrentDB().login(username, password);
			SceneManager.getSceneManager().switchScene("./login/Logout.fxml");
			SceneManager.getSceneManager().refreshMenu();
		}
		else {
			errorLabel.setText("El usuario ya está registrado o el visitante ya tiene un usuario asociado");
			errorLabel.setVisible(true);
		}
	}
}
