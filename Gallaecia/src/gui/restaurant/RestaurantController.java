package gui.restaurant;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import baseDatos.DataBase;
import gui.SceneManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

import javafx.scene.text.Text;
import javafx.util.Callback;
import modelo.Hostalaria;


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

		restauranteElegido = null;

		try {
			hostalarias = DataBase.getCurrentDB().getAllRestaurants();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

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
			SceneManager.getSceneManager().switchScene("./reservaXantar/Reserva.fxml");
		} else {
			errorMensaje.setVisible(true);
		}
	}

	public void switchToEliminarReserva(ActionEvent event) throws IOException {
		if (DataBase.getCurrentDB().getUser()!=null) {
			SceneManager.getSceneManager().switchScene("./gestionReservaXantar/GestionReserva.fxml");
		} else {
			errorMensaje.setVisible(true);
		}

	}


}
