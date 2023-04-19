package gui.restaurant;


import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ResourceBundle;

import baseDatos.DataBase;
import gui.SceneManager;
import gui.reserva.ReservaController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Callback;
import modelo.Hostalaria;
import modelo.Reserva;


public class RestaurantController implements Initializable {

	public static Hostalaria restauranteElegido;

	@FXML
	private Text errorMensaje;

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
						"Hora cierre: " + t1.getHoraFin() + "\n\n" +
						"Ubicación: " + t1.getZona().getNome() + ".\n" +
						"Latitud: " + t1.getZona().getCoordenadaX() + "\n" +
						"Longitud: " + t1.getZona().getCoordenadaY();

				myLabel.setText(descripcion);
			}

		});
	}


	public void switchToNuevaReserva(ActionEvent event) throws IOException {
		if (DataBase.getCurrentDB().getUser()!=null) {
			if (myListView.getSelectionModel().getSelectedItem()!=null) {
				restauranteElegido = myListView.getSelectionModel().getSelectedItem();
			}
			SceneManager.getSceneManager().switchScene("./reserva/Reserva.fxml");
		} else {
			errorMensaje.setVisible(true);
		}
	}

	public void switchToEliminarReserva(ActionEvent event) throws IOException {
		if (DataBase.getCurrentDB().getUser()!=null) {
			SceneManager.getSceneManager().switchScene("./gestionReserva/gestionReserva.fxml");
		} else {
			errorMensaje.setVisible(true);
		}

	}


}
