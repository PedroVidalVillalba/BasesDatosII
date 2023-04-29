package gui.nuevoEspectaculo;

import baseDatos.DataBase;
import gui.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.util.Callback;
import modelo.Espectaculo;
import modelo.Zona;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class NuevoEspectaculoController implements Initializable {

    @FXML
    private ListView<Zona> listaZonas;
    @FXML
    private ComboBox<Time> horaInicio;
    @FXML
    private ComboBox<Time> horaFin;
    @FXML
    private TextField nombre;
    @FXML
    private TextField descripcion;
    @FXML
    private TextField tematica;
    @FXML
    private Button myButton;
    @FXML
    private Text errorText;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //mensaje de error
        errorText.setVisible(false);
        //Indexar tiempos
        ArrayList<Time> tiempos = new ArrayList<>();
        for (int i=0; i<11; i++) {
            Time time = new Time(12+i,0,0);
            tiempos.add(i,time);
        }
        horaInicio.getItems().setAll(tiempos);
        horaFin.getItems().setAll(tiempos);

        //Indexar zonas en listView
        java.util.List<Zona> zonas = DataBase.getCurrentDB().getAllZones();

        for (Zona z : zonas) {
            listaZonas.getItems().add(z);
        }

        //Para mostrar el nombre de las zonas
        listaZonas.setCellFactory(new Callback<ListView<Zona>, ListCell<Zona>>() {
            @Override
            public ListCell<Zona> call(ListView<Zona> zonaListView) {
                return new ListCell<Zona>() {
                    @Override
                    protected void updateItem(Zona zona, boolean b) {
                        super.updateItem(zona, b);
                        if (zona != null) {
                            setText(zona.getNome());
                        }
                    }
                };
            }
        });


    }
    public void anhadirEspectaculo(ActionEvent event) throws IOException {
        if (DataBase.getCurrentDB().getUser()!=null) {
                Espectaculo espectaculo = new Espectaculo(nombre.getText(), horaInicio.getValue(), horaFin.getValue(), descripcion.getText(),tematica.getText() ,listaZonas.getSelectionModel().getSelectedItem());
                try {
                    DataBase.getCurrentDB().insertarEspectaculo(espectaculo);
                    SceneManager.getSceneManager().switchScene("./reservaExitoIr/ReservaExito.fxml");
                } catch (SQLException e){
                    errorText.setVisible(true);
                }

        }


    }
}