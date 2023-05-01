package gui.admin;

import gui.SceneManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.*;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminController {

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

	public void adminValoracions() {
		SceneManager.getSceneManager().switchAdminTable("./admin/valoracions/AdminValoracions.fxml");
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
