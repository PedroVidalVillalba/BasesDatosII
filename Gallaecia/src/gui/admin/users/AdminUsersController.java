package gui.admin.users;

import baseDatos.DataBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.User;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AdminUsersController implements Initializable {
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

	private ObservableList<User> userList;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		dniColumn.setCellValueFactory(new PropertyValueFactory<>("dni"));
		nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
		usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
		isAdminColumn.setCellValueFactory(new PropertyValueFactory<>("isAdmin"));
		userList = FXCollections.observableList(DataBase.getCurrentDB().getAllUsers());

		userTable.setItems(userList);
	}

	/**
	 * Removes the selected users from the database and the observable list.
	 * This method is triggered when the "Eliminar usuario" button is clicked.
	 */
	public void deleteUser(ActionEvent event) throws SQLException {
		// Get the selected users from the table view
		User selectedUser = userTable.getSelectionModel().getSelectedItem();

		// Only delete the users if one is selected
		if (selectedUser != null) {
			// Remove the users from the database
			DataBase.getCurrentDB().deleteUser(selectedUser.getUsername());

			// Remove the users from the observable list
			userList.remove(selectedUser);
		}
	}
}
