package gui.ride;

import java.net.URL;
import java.util.ResourceBundle;

import baseDatos.DataBase;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

import javafx.util.Callback;
import modelo.Atraccion;

public class RideController implements Initializable {

    @FXML
    private ListView<Atraccion> myListView;
    @FXML
    private Label myLabel;

    private java.util.List<Atraccion> atraccions;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        atraccions = DataBase.getCurrentDB().getAllRides();

        for (Atraccion atraccion : atraccions) {
            myListView.getItems().add(atraccion);
        }

        // Para mostrar el nombre de cada atracción en la ListView.
        myListView.setCellFactory(new Callback<ListView<Atraccion>, ListCell<Atraccion>>() {
            @Override
            public ListCell<Atraccion> call(ListView<Atraccion> atraccionListView) {
                return new ListCell<Atraccion>() {
                    @Override
                    protected void updateItem(Atraccion atraccion, boolean b) {
                        super.updateItem(atraccion, b);
                        if (atraccion != null) {
                            setText(atraccion.getNome());
                        }
                    }
                };
            }
        });

        myListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Atraccion>() {
            @Override
            public void changed(ObservableValue<? extends Atraccion> observableValue, Atraccion s, Atraccion t1) {

                String descripcion = "Descripción: " + t1.getDescricion() + ".\n" +
                                     "Aforo: " + t1.getAforo() + "\n" +
                                     "Altura mínima: " + t1.getAlturaMin() + "\n\n" +
                                     "Ubicación: " + t1.getZona().getNome() + ".\n" +
                                     "Latitud: " + t1.getZona().getCoordenadaX() + "\n" +
                                     "Longitud: " + t1.getZona().getCoordenadaY();

                myLabel.setText(descripcion);
            }

            });
}
}