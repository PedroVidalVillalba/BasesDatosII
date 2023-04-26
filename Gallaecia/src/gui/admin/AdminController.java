package gui.admin;

import baseDatos.DataBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.User;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AdminController implements Initializable {
	@FXML
	TableView<User> userTable;

	@FXML
	TableColumn<User, String> dniColumn;
	@FXML
	TableColumn<User, String> nomeColumn;
	@FXML
	TableColumn<User, String> usernameColumn;
	@FXML
	TableColumn<User, Boolean> isAdminColumn;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		dniColumn.setCellValueFactory(new PropertyValueFactory<>("dni"));
		nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
		usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
		isAdminColumn.setCellValueFactory(new PropertyValueFactory<>("isAdmin"));

		ObservableList<User> userList = FXCollections.observableList(DataBase.getCurrentDB().getAllUsers());

		userTable.setItems(userList);
	}
}
