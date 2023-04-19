package baseDatos;

import modelo.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

public class DataBase {
	private static DataBase currentDB;
	private Connection connection;
	private RideDAO rideDAO;
	private RestaurantDAO restaurantDAO;
	private EspectaculoDAO espectaculoDAO;
	private UserDAO userDAO;
	private ReservasDAO reservasDAO;
	private UserType userType;
	private User user;

	public DataBase(UserType userType) {
		Properties configuration = new Properties();
		FileInputStream configurationFile;

		try {
			switch (userType) {
				case Admin:
					configurationFile = new FileInputStream("properties/DataBaseAdmin.properties");
					break;
				case Guest:
				default:
					configurationFile = new FileInputStream("properties/DataBaseAdmin.properties");
					break;
			}

			this.userType = userType;

			configuration.load(configurationFile);
			configurationFile.close();

			Properties user = new Properties();

			String manager = configuration.getProperty("manager");

			user.setProperty("user", configuration.getProperty("user"));
			user.setProperty("password", configuration.getProperty("password"));

			this.connection = DriverManager.getConnection("jdbc:" + manager + "://" +
							configuration.getProperty("server") + ":" +
							configuration.getProperty("port") + "/" +
							configuration.getProperty("dataBase"),
					user);

			this.userDAO = new UserDAO(this.connection);
			this.rideDAO = new RideDAO(this.connection);
			this.restaurantDAO = new RestaurantDAO(this.connection);
			this.espectaculoDAO = new EspectaculoDAO(this.connection);

			DataBase.currentDB = this;

		} catch(IOException | SQLException exception) {
			System.err.println(exception.getMessage());
		}
	}

	public static DataBase getCurrentDB() {
		return DataBase.currentDB;
	}

	public static void closeCurrentDB() {
		try {
			currentDB.connection.close();
		} catch (SQLException exception) {
			System.err.println(exception.getMessage());
		}
	}

	public UserType getUserType() {
		return this.userType;
	}

	public User getUser() {
		return this.user;
	}

	public boolean login(String username, String password) {
		this.user = userDAO.login(username, password);
		if (user == null) {
			return false;
		}
		else if (user.getUserType() != this.userType) {
			DataBase.closeCurrentDB();
			new DataBase(user.getUserType());
		}
		return true;
	}

	/** Métodos de UserDAO */
	public void logout() {
		this.user = null;
		this.userType = UserType.Guest;
	}

	public boolean signUp(Visitante visitante, User user) {
		return userDAO.signUp(visitante, user);
	}

	/**
	 * Método de RideDAO.
	 * @return
	 */
	public List<Atraccion> getAllRides(){
		return rideDAO.getAllRides();
	}

	public List<Hostalaria> getAllRestaurants(){
		return restaurantDAO.getAllRestaurants();
	}

	public List<Espectaculo> getAllEspectaculos(){
		return espectaculoDAO.getAllEspectaculos();
	}

	public List<Reserva> getAllReservas() {
		return reservasDAO.getAllReservas();
	}

	public void insertarReserva(Reserva reserva) throws SQLException{
		restaurantDAO.insertarReserva(reserva);
	}
}
