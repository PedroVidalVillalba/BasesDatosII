package gui.admin.hostalaria;

import baseDatos.DataBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;
import modelo.Hostalaria;
import modelo.Zona;

import java.net.URL;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ResourceBundle;

public class AdminHostalariaController implements Initializable {
    @FXML
    TableView<Hostalaria> restaurantTable;

    @FXML
    TableColumn<Hostalaria, String> nomeColumn;
    @FXML
    TableColumn<Hostalaria, Integer> aforoColumn;
    @FXML
    TableColumn<Hostalaria, Time> horainicioColumn;
    @FXML
    TableColumn<Hostalaria, Time> horafinColumn;
    @FXML
    TableColumn<Hostalaria, Zona> zonaColumn;

    private ObservableList<Hostalaria> restaurantList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nomeEstablecemento"));
        aforoColumn.setCellValueFactory(new PropertyValueFactory<>("aforo"));
        horainicioColumn.setCellValueFactory(new PropertyValueFactory<>("horaInicio"));
        horafinColumn.setCellValueFactory(new PropertyValueFactory<>("horaFin"));
        zonaColumn.setCellValueFactory(new PropertyValueFactory<>("zona"));

        // Habilitar la edición en la tabla.
        restaurantTable.setEditable(true);

        aforoColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        aforoColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Hostalaria, Integer>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Hostalaria, Integer> hostalariaIntegerCellEditEvent) {
                Hostalaria hostalaria  = hostalariaIntegerCellEditEvent.getRowValue();
                hostalaria.setAforo(hostalariaIntegerCellEditEvent.getNewValue());
                DataBase.getCurrentDB().updateRetaurant(hostalaria);
            }
        });

        try {
            restaurantList = FXCollections.observableList(DataBase.getCurrentDB().getAllRestaurants());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        restaurantTable.setItems(restaurantList);
    }
}
