package gui.admin;

import baseDatos.DataBase;
import gui.SceneManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import modelo.User;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AdminController implements Initializable {
	@FXML
	private AnchorPane pane;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		VBox root = SceneManager.getSceneManager().getRoot();
		HBox content = new HBox(pane, new Pane());
		root.getChildren().set(1, content);
	}

	public void adminAsistir() {
		SceneManager.getSceneManager().switchAdminTable("./admin/asistir/AdminAsistir.fxml");
	}

	public void adminAtraccions() {
		SceneManager.getSceneManager().switchAdminTable("./admin/atraccions/AdminAtraccions.fxml");
	}

	public void adminAtraccionsFamiliares() {
		SceneManager.getSceneManager().switchAdminTable("./admin/atraccions/AdminAtraccionsFamiliares.fxml");
	}

	public void adminAtraccionsAdultos() {
		SceneManager.getSceneManager().switchAdminTable("./admin/atraccions/AdminAtraccionsAdultos.fxml");
	}

	public void adminDJ() {
		SceneManager.getSceneManager().switchAdminTable("./admin/dj/AdminDJ.fxml");
	}

	public void adminEspectaculos() {
		SceneManager.getSceneManager().switchAdminTable("./admin/espectaculos/AdminEspectaculos.fxml");
	}

	public void adminHostalaria() {
		SceneManager.getSceneManager().switchAdminTable("./admin/hostalaria/AdminHostalaria.fxml");
	}

	public void adminHostaleiros() {
		SceneManager.getSceneManager().switchAdminTable("./admin/hostaleiros/AdminHostaleiros.fxml");
	}

	public void adminIr() {
		SceneManager.getSceneManager().switchAdminTable("./admin/atraccions/AdminIr.fxml");
	}

	public void adminMedios() {
		SceneManager.getSceneManager().switchAdminTable("./admin/medios/AdminMedios.fxml");
	}

	public void adminMusica() {
		SceneManager.getSceneManager().switchAdminTable("./admin/musica/AdminMusica.fxml");
	}

	public void adminReproducir() {
		SceneManager.getSceneManager().switchAdminTable("./admin/reproducir/AdminReproducir.fxml");
	}

	public void adminSistemasDeAudio() {
		SceneManager.getSceneManager().switchAdminTable("./admin/sistemasdeaudio/AdminSistemasDeAudio.fxml");
	}

	public void adminTraballadoresParque() {
		SceneManager.getSceneManager().switchAdminTable("./admin/traballadoresparque/AdminTraballadoresParque.fxml");
	}

	public void adminUsers() {
		SceneManager.getSceneManager().switchAdminTable("./admin/users/AdminUsers.fxml");
	}

	public void adminVisitantes() {
		SceneManager.getSceneManager().switchAdminTable("./admin/visitantes/AdminVisitantes.fxml");
	}

	public void adminXantar() {
		SceneManager.getSceneManager().switchAdminTable("./admin/xantar/AdminXantar.fxml");
	}

	public void adminZonas() {
		SceneManager.getSceneManager().switchAdminTable("./admin/zonas/AdminZonas.fxml");
	}
}
