package gui;

import gui.menu.MenuController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
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

	public void switchScene(String FXMLFileName) {
		try {
			Parent content = FXMLLoader.load(getClass().getResource(FXMLFileName));
			this.root.getChildren().set(1, content);
		} catch (IOException exception) {
			System.err.println("Error: ");
			exception.printStackTrace();
		}
	}

	public void refreshMenu() {
		menuController.refresh();
	}
}
