package gui.signUp;

import baseDatos.DataBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import modelo.User;
import modelo.Visitante;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

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

	private void switchScene(String FXMLFileName) {
		try {
			Parent content = FXMLLoader.load(getClass().getResource(FXMLFileName));
			VBox root = (VBox) userField.getScene().getRoot();
			root.getChildren().set(1, content);
		} catch (IOException exception) {
			System.err.println(exception.getMessage());
		}
	}

	public void signUp() {
		String nome = nameField.getText();
		String dni = dniField.getText();
		String telefono = phoneField.getText();
		Integer altura = Integer.decode(heightField.getText());
		LocalDate dataNacemento = birthDatePicker.getValue();
		String nacionalidade = nationalityComboBox.getValue();

		String username = userField.getText();
		String password = passwordField.getText();
		String password1 = passwordField1.getText();

		if (!password.equals(password1)) {
			// Do smt
		}

		Visitante visitante = new Visitante(dni, nome, nacionalidade, telefono, dataNacemento, altura, null);
		User user = new User(0, dni, nome, username, password, false);
		DataBase.getCurrentDB().signUp(visitante, user);
		DataBase.getCurrentDB().login(username, password);
		switchScene("../login/LoginSuccessful.fxml");
	}
}
