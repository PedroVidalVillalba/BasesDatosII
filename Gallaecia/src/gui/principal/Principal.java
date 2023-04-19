package gui.principal;

import baseDatos.UserType;
import gui.SceneManager;
import javafx.application.Application;
import baseDatos.DataBase;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import modelo.Atraccion;

public class Principal extends Application {
	/**
	 * Es necesario crear un constructor vacío para que funcionen los métodos de Application
	 */
	public Principal() {}

	public Principal(String[] args) {
		launch(args);
	}
	@Override
	public void start(Stage stage) throws Exception {

		//FXMLLoader loader = new FXMLLoader(this.getClass().getResource("Principal.fxml"));
		Parent content = FXMLLoader.load(getClass().getResource("Principal.fxml"));
		Parent menu = FXMLLoader.load(getClass().getResource("../menu/Menu.fxml"));

		VBox root = new VBox(menu, content);

		SceneManager.getSceneManager().setRoot(root);

		Scene scene = new Scene(root);  //Necesita un nodo (hay muchos tipos)

		// Cerrar la conexión al salir
		stage.setOnCloseRequest(event -> {DataBase.closeCurrentDB(); System.exit(0);});

		stage.setScene(scene);//Seteamos la escena jjj
		stage.setTitle("Gallaecia");
		stage.show();//Mostramos el escenario
	}
}
