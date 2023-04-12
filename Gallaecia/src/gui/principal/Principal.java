package gui.principal;

import baseDatos.UserType;
import javafx.application.Application;
import baseDatos.DataBase;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;

public class Principal extends Application {
    /**
     * Es necesario crear un constructor vacío para que funcionen los métodos de Application
     */
    public Principal() {}

    public Principal(String[] args) {
        new DataBase(UserType.Guest);
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {

        //FXMLLoader loader = new FXMLLoader(this.getClass().getResource("Principal.fxml"));
        Parent content = FXMLLoader.load(getClass().getResource("Principal.fxml"));
        Parent menu = FXMLLoader.load(getClass().getResource("../menu/Menu.fxml"));

        Parent root = new VBox(menu, content);

        Scene scene = new Scene(root);  //Necesita un nodo (hay muchos tipos)

       // String css = this.getClass().getResource("Principal.css").toExternalForm();
       // scene.getStylesheets().add(css);

        stage.setScene(scene);//Seteamos la escena jjj
        stage.setTitle("Gallaecia");
        stage.show();//Mostramos el escenario

        //new Login(stage); //Necesitamos interfaz login
    }

}
