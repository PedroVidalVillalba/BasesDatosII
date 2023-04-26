package baseDatos;

import modelo.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Clase fachada para interactuar con la base de datos.
 * Sigue el patrón singleton
 */
public class DataBase {
	private static DataBase currentDB;
	private Connection connection;
	private RideDAO rideDAO;
	private RestaurantDAO restaurantDAO;
	private EspectaculoDAO espectaculoDAO;
	private UserDAO userDAO;
	private ReservasDAO reservasDAO;
	private ZonaDAO zonaDAO;
	private RatingDAO ratingDAO;
	private List<AbstractDAO> DAOList;
	private UserType userType;
	private User user;

	private DataBase(UserType userType) {
		this.DAOList = new ArrayList<>();

		/* Inicializar los DAOs */
		this.userDAO = new UserDAO();
		this.DAOList.add(userDAO);
		this.rideDAO = new RideDAO();
		this.DAOList.add(rideDAO);
		this.restaurantDAO = new RestaurantDAO();
		this.DAOList.add(restaurantDAO);
		this.espectaculoDAO = new EspectaculoDAO();
		this.DAOList.add(espectaculoDAO);
		this.reservasDAO = new ReservasDAO();
		this.DAOList.add(reservasDAO);
		this.zonaDAO = new ZonaDAO();
		this.DAOList.add(zonaDAO);
		this.ratingDAO = new RatingDAO();
		this.DAOList.add(ratingDAO);

		/* Establecer la conexión */
		this.establishConnection(UserType.Guest);
	}

	public void establishConnection(UserType userType) {
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

			if (this.connection != null) {
				DataBase.closeCurrentDB();
			}
			this.connection = DriverManager.getConnection("jdbc:" + manager + "://" +
							configuration.getProperty("server") + ":" +
							configuration.getProperty("port") + "/" +
							configuration.getProperty("dataBase"),
					user);

			for (AbstractDAO dao : this.DAOList) {
				dao.setConexion(this.connection);
			}

		} catch(IOException | SQLException exception) {
			System.err.println(exception.getMessage());
		}
	}

	public static DataBase getCurrentDB() {
		if (DataBase.currentDB == null) {
			DataBase.currentDB = new DataBase(UserType.Guest);
		}
		return DataBase.currentDB;
	}

	public static void closeCurrentDB() {
		try {
			if (currentDB != null && currentDB.connection != null) {
				currentDB.connection.close();
			}
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
			this.establishConnection(user.getUserType());
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

	public List<User> getAllUsers() {
		return userDAO.getAllUsers();
	}

	/**
	 * Método de RideDAO.
	 * @return
	 */
	public List<Atraccion> getAllRides(){
		return rideDAO.getAllRides();
	}

	public List<Hostalaria> getAllRestaurants() throws SQLException {
		return restaurantDAO.getAllRestaurants();
	}

	public List<Espectaculo> getAllEspectaculos(){
		return espectaculoDAO.getAllEspectaculos();
	}

	public List<Valoracion> getAllRatings(){
		return ratingDAO.getAllRatings();
	}

	public void newRating(String descricion, int puntuacion){ ratingDAO.newRating(descricion, puntuacion); }

	public List<Reserva> getAllReservas() {
		return reservasDAO.getAllReservas();
	}

	public void insertarReserva(Reserva reserva) throws SQLException{
		restaurantDAO.insertarReserva(reserva);
	}

	public void borrarReserva(Reserva reserva) {
		reservasDAO.borrarReserva(reserva);
	}

	public void insertarEspectaculo(Espectaculo espectaculo) throws SQLException{
		espectaculoDAO.insertarEspectaculo(espectaculo);
	}

	public void deleteUser(String username) { userDAO.deleteUserByUsername(username);}



	public List<Zona> getAllZones(){return zonaDAO.getAllZones();}
}


