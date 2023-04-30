package gui.espectaculo.nuevoEspectaculo;

import baseDatos.DataBase;
import gui.SceneManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Callback;
import modelo.Espectaculo;
import modelo.Zona;

import java.net.URL;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.ResourceBundle;


/**
 * Clase controlador del patrón Modelo-Vista-Controlador. Tiene asociada una vista del mismo nombre
 * Controla la vista de nuevos espectáculos
 */
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
    private TextArea descripcion;
    @FXML
    private TextField tematica;
    @FXML
    private Label errorLabel;

    /** Inicialización de la vista */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
        listaZonas.setCellFactory(new Callback<>() {
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

    /**
     * Método que intenta añadir un nuevo espectáculo a la base de datos
     */
    public void anhadirEspectaculo() {
        if (nombre.getText().isBlank() || descripcion.getText().isBlank() || horaFin.getValue().before(horaInicio.getValue())) {
            //nombre.getStyleClass().add("error-text-field");
            errorLabel.setVisible(true);
            return;
        }
        nombre.getStyleClass().remove("error-text-field");
        if (DataBase.getCurrentDB().getUser()!=null) {
                Espectaculo espectaculo = new Espectaculo(nombre.getText(), horaInicio.getValue(), horaFin.getValue(), descripcion.getText(),tematica.getText() ,listaZonas.getSelectionModel().getSelectedItem());

                    try {
                        DataBase.getCurrentDB().insertarEspectaculo(espectaculo);
                        SceneManager.getSceneManager().switchScene("./espectaculo/reserva/ReservaExito.fxml");
                    } catch (SQLException e){
                        errorLabel.setVisible(true);
                    }


        }
    }
}
