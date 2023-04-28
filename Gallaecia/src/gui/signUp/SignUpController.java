package gui.signUp;

import baseDatos.DataBase;
import gui.SceneManager;
import gui.menu.MenuController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import modelo.User;
import modelo.Visitante;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.sql.Date;

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
	private Text errorText;

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

	public void signUp() {
		String nome = nameField.getText();
		String dni = dniField.getText();
		String telefono = phoneField.getText();
		Integer altura;
		Date dataNacemento = Date.valueOf(birthDatePicker.getValue());
		String nacionalidade = nationalityComboBox.getValue();

		String username = userField.getText();
		String password = passwordField.getText();
		String password1 = passwordField1.getText();

		errorText.setVisible(false);

		if (username.isBlank() || password.isBlank() || password1.isBlank()) {
			errorText.setText("Debes rellenar los campos de usuario y contraseña");
			errorText.setVisible(true);
			return;
		}

		if (!password.equals(password1)) {
			errorText.setText("Las contraseñas no coinciden");
			errorText.setVisible(true);
			return;
		}

		if (nome.isBlank() || dni.isBlank()) {
			errorText.setText("Debes rellenar los campos de nombre y DNI");
			errorText.setVisible(true);
			return;
		}

		try {
			altura = Integer.valueOf(heightField.getText());
			if (altura <= 0) {
				errorText.setText("La altura tiene que ser un número entero positivo");
				errorText.setVisible(true);
				return;
			}
		} catch (NumberFormatException exception) {
			errorText.setText("La altura debe ser un número");
			errorText.setVisible(true);
			return;
		}

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
			errorText.setText("El usuario ya está registrado o el visitante ya tiene un usuario asociado");
			errorText.setVisible(true);
		}
	}
}
