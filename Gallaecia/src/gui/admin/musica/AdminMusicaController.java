package gui.admin.musica;

import baseDatos.DataBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.Musica;
import modelo.Musica;
import modelo.TraballadorParque;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Clase controlador del patrón Modelo-Vista-Controlador. Tiene asociada una vista del mismo nombre
 * Controla la vista de administración de la música y las canciones
 */
public class AdminMusicaController implements Initializable {
    @FXML
    TableView<Musica> musicaTable;

    @FXML
    TableColumn<Musica, String> codigoCancionColumn;
    @FXML
    TableColumn<Musica, String> nomeColumn;
    @FXML
    TableColumn<Musica, String> clasificacionColumn;
    @FXML
    TableColumn<Musica, Integer> popularidadeColumn;
    @FXML
    TableColumn<Musica, String> artistaColumn;
    @FXML
    TableColumn<Musica, String> albumColumn;

    private ObservableList<Musica> musicaList;

    /** Inicialización de la vista */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        codigoCancionColumn.setCellValueFactory(new PropertyValueFactory<>("codigoCancion"));
        nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
        clasificacionColumn.setCellValueFactory(new PropertyValueFactory<>("clasificacion"));
        popularidadeColumn.setCellValueFactory(new PropertyValueFactory<>("popularidade"));
        artistaColumn.setCellValueFactory(new PropertyValueFactory<>("artista"));
        albumColumn.setCellValueFactory(new PropertyValueFactory<>("album"));

        // Habilitar la edición en la tabla.
        musicaTable.setEditable(true);


        try {
            musicaList = FXCollections.observableList(DataBase.getCurrentDB().getAllMusica());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        musicaTable.setItems(musicaList);
    }

    /**
     * Removes the selected music from the database and the observable list.
     * This method is triggered when the "Eliminar música" button is clicked.
     */
    public void deleteMusica() throws SQLException {
        // Get the selected shows from the table view
        Musica selectedMusica= musicaTable.getSelectionModel().getSelectedItem();

        // Only delete the show if one is selected
        if (selectedMusica!= null) {
            // Remove the shows from the database
            DataBase.getCurrentDB().borrarMusica(selectedMusica);

            // Remove the shows from the observable list
            musicaList.remove(selectedMusica);
        }
    }
}
