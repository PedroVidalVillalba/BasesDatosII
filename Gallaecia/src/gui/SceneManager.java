package gui;

import gui.menu.MenuController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.HashMap;

public class SceneManager {
	private static SceneManager sceneManager;

	private VBox root;

	private MenuController menuController;

	public static SceneManager getSceneManager() {
		if (sceneManager == null) {
			sceneManager = new SceneManager();
		}
		return sceneManager;
	}

	public void setMenuController(MenuController menuController) {
		this.menuController = menuController;
	}

	public void setRoot(VBox root) {
		this.root = root;
	}
	public VBox getRoot() {
		return this.root;
	}

	public void switchScene(String FXMLFileName) {
		try {
			Parent content = FXMLLoader.load(getClass().getResource(FXMLFileName));
			VBox.setVgrow(content, Priority.ALWAYS);
			this.root.getChildren().set(1, content);
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}

	public void switchAdminTable(String FXMLFileName) {
		try {
			Parent tableView = FXMLLoader.load(getClass().getResource(FXMLFileName));
			HBox content = (HBox) this.root.getChildren().get(1);
			content.getChildren().set(1, tableView);
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}
	public void refreshMenu() {
		menuController.refresh();
	}

}
