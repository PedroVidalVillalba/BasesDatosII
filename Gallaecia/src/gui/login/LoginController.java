package gui.login;

import baseDatos.DataBase;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class LoginController {
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField usernameField;
    @FXML
    private Button loginButton;
    @FXML
    private Button signUpButton;
    @FXML
    private Text resultText;

    public void login() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        boolean success = DataBase.getCurrentDB().login(username, password);

        if (success) {
            resultText.setText("Sesión iniciada con éxito");
            resultText.setFill(Color.GREEN);
        }
        else {
            resultText.setText("Fallo en el inicio de sesión");
            resultText.setFill(Color.RED);
        }

        resultText.setVisible(true);
    }
}
