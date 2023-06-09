package gui.principal;

import baseDatos.UserType;
import gui.SceneManager;
import javafx.application.Application;
import baseDatos.DataBase;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import modelo.Atraccion;

/**
 * Clase principal de la aplicación. Crea el "escenario" sobre el que se muestran las diferentes "escenas" del programa
 */
public class Principal extends Application {
	/**
	 * Es necesario crear un constructor vacío para que funcionen los métodos de Application
	 */
	public Principal() {}

	/** Inicialización de la aplicación */
	public Principal(String[] args) {
		launch(args);
	}
	@Override
	public void start(Stage stage) throws Exception {

		//FXMLLoader loader = new FXMLLoader(this.getClass().getResource("Principal.fxml"));
		Parent content = FXMLLoader.load(getClass().getResource("Principal.fxml"));
		Parent menu = FXMLLoader.load(getClass().getResource("../menu/Menu.fxml"));

		VBox root = new VBox(menu, content);
		VBox.setVgrow(content, Priority.ALWAYS);	// Hacer que el contenido ocupe el espacio vertical disponible

		SceneManager.getSceneManager().setRoot(root);

		Scene scene = new Scene(root);  //Necesita un nodo (hay muchos tipos)

		// Cerrar la conexión al salir
		stage.setOnCloseRequest(event -> {DataBase.closeCurrentDB(); System.exit(0);});

		stage.setScene(scene);//Seteamos la escena jjj
		stage.setTitle("Gallaecia");
		stage.setMaximized(true);
		stage.show();//Mostramos el escenario
	}
}
