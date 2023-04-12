package gui.principal;

import javafx.application.Application;
import baseDatos.BaseDatos;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Principal extends Application {
    /**
     * Es necesario crear un constructor vacío para que funcionen los métodos de Application
     */
    public Principal() {}

    public Principal(String[] args) {
        new BaseDatos();
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        //FXMLLoader loader = new FXMLLoader(this.getClass().getResource("Principal.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("Principal.fxml"));

        Scene scene = new Scene(root);//Necesita un nodo (hay muchos tipos)

        String css = this.getClass().getResource("Principal.css").toExternalForm();
        scene.getStylesheets().add(css);

        stage.setScene(scene);//Seteamos la escena jjj
        stage.setTitle("Gallaecia");
        stage.show();//Mostramos el escenario

        //new Login(stage); //Necesitamos interfaz login
    }

}
