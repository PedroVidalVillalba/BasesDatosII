package gui.restaurant;


import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ResourceBundle;

import baseDatos.DataBase;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import modelo.Hostalaria;
import modelo.Reserva;

public class RestaurantController implements Initializable {

	@FXML
	private ListView<Hostalaria> myListView;
	@FXML
	private Label myLabel;

	private java.util.List<Hostalaria> hostalarias;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {

		hostalarias = DataBase.getCurrentDB().getAllRestaurants();

		for (Hostalaria h : hostalarias) {
			myListView.getItems().add(h);
		}

		// Para mostrar el nombre de cada atracción en la ListView.
		myListView.setCellFactory(new Callback<ListView<Hostalaria>, ListCell<Hostalaria>>() {
			@Override
			public ListCell<Hostalaria> call(ListView<Hostalaria> atraccionListView) {
				return new ListCell<Hostalaria>() {
					@Override
					protected void updateItem(Hostalaria h, boolean b) {
						super.updateItem(h, b);
						if (h != null) {
							setText(h.getNomeEstablecemento());
						}
					}
				};
			}
		});

		myListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Hostalaria>() {
			@Override
			public void changed(ObservableValue<? extends Hostalaria> observableValue, Hostalaria s, Hostalaria t1) {

				String descripcion = "Aforo: " + t1.getAforo() + "\n" +
						"Hora apertura: " + t1.getHoraInicio() + "\n\n" +
						"Hora peche: " + t1.getHoraFin() + "\n\n" +
						"Ubicación: " + t1.getZona().getNome() + ".\n" +
						"Latitud: " + t1.getZona().getCoordenadaX() + "\n" +
						"Longitud: " + t1.getZona().getCoordenadaY();

				myLabel.setText(descripcion);
			}

		});
	}

	@FXML
	private MenuBar menuBar;//NOTA

	private void switchScene(String FXMLFileName) throws IOException {
		try {
			Parent content = FXMLLoader.load(getClass().getResource(FXMLFileName));
			VBox root = (VBox) myLabel.getScene().getRoot();
			root.getChildren().set(1, content);
		} catch (IOException exception) {
			System.err.println(exception.getMessage());
		}
	}

	public void switchToNuevaReserva(ActionEvent event) throws IOException {
		switchScene("../reserva/Reserva.fxml");
	}

	public void switchToEliminarReserva(ActionEvent event) throws IOException {
		switchScene("../gestionReserva/gestionReserva.fxml");
	}


}
