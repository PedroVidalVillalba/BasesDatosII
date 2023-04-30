package gui.ride;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import baseDatos.DataBase;
import gui.SceneManager;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

import javafx.scene.text.Text;
import javafx.util.Callback;
import modelo.Atraccion;

/**
 * Clase controlador del patrón Modelo-Vista-Controlador. Tiene asociada una vista del mismo nombre
 * Controla la vista de atracciones
 */
public class RideController implements Initializable {

	public static Atraccion atraccionElegida;

	@FXML
	private ListView<Atraccion> myListView;
	@FXML
	private Label myLabel;

	@FXML
	private Label errorMensaje;
	@FXML
	private Label errorNull;

	/** Inicialización de la vista */
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {

		java.util.List<Atraccion> atraccions = DataBase.getCurrentDB().getAllRides();

		for (Atraccion atraccion : atraccions) {
			myListView.getItems().add(atraccion);
		}

		// Para mostrar el nombre de cada atracción en la ListView.
		myListView.setCellFactory(new Callback<>() {
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

	/**
	 * Cambio de escena a Nueva reserva (solo para usuarios registrados)
	 */
	public void switchToNuevaReserva() {
		if (DataBase.getCurrentDB().getUser()!=null) {
			if (myListView.getSelectionModel().getSelectedItem() != null) {
				atraccionElegida = myListView.getSelectionModel().getSelectedItem();
				SceneManager.getSceneManager().switchScene("./ride/reserva/Reserva.fxml");
			} else {
				errorNull.setVisible(true);
			}
		} else {
			errorMensaje.setVisible(true);
		}
	}

	/**
	 * Cambio de escena a Eliminar reserva (solo para usuarios registrados)
	 */
	public void switchToEliminarReserva() {
		if (DataBase.getCurrentDB().getUser() != null) {
			SceneManager.getSceneManager().switchScene("./ride/gestionReserva/GestionReserva.fxml");
		} else {
			errorMensaje.setVisible(true);
		}

	}
}