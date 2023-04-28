package gui.rating.newRating;

import baseDatos.DataBase;
import gui.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class NewRatingController implements Initializable {

    @FXML
    private TextArea descriptionField;
    @FXML
    private Slider scoreField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {




    }

    public void newRating(ActionEvent event) throws IOException {
        if (DataBase.getCurrentDB().getUser()!=null) {
            int score = (int)scoreField.getValue();
            if(score < 1 || score > 5) score = 5;
            DataBase.getCurrentDB().newRating(descriptionField.getText(), score);
        }

        SceneManager.getSceneManager().switchScene("./rating/Rating.fxml");
    }

    public void cancel(ActionEvent event) throws IOException {
        SceneManager.getSceneManager().switchScene("./rating/Rating.fxml");
    }


}
