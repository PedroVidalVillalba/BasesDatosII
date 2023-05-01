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
 * Sigue el patrón Singleton y Facade
 */
public class DataBase {
	private static DataBase currentDB;
	private Connection connection;
	private RideDAO rideDAO;
	private RestaurantDAO restaurantDAO;
	private EspectaculoDAO espectaculoDAO;
	private UserDAO userDAO;
	private ReservasXantarDAO reservasDAO;
	private ZonaDAO zonaDAO;

	private ReservasIrDAO reservasIrDA0;
	private ReservasAsistirDAO reservasAsistirDAO;
	private RatingDAO ratingDAO;
	private VisitantesDAO visitantesDAO;
	private AsistirDAO asistirDAO;
	private AtraccionsFamiliaresDAO atraccionsFamiliaresDAO;
	private AtraccionSoAdultosDAO atraccionSoAdultosDAO;
	private TraballadorParqueDAO traballadorParqueDAO;
	private DjDAO djDAO;
	private SistemasDeAudioDAO sistemasDeAudioDAO;
	private MusicaDAO musicaDAO;
	private MedioDAO medioDAO;
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
		this.reservasDAO = new ReservasXantarDAO();
		this.DAOList.add(reservasDAO);
		this.zonaDAO = new ZonaDAO();
		this.DAOList.add(zonaDAO);
		this.ratingDAO = new RatingDAO();
		this.DAOList.add(ratingDAO);
		this.reservasIrDA0 = new ReservasIrDAO();
		this.DAOList.add(reservasIrDA0);
		this.reservasAsistirDAO = new ReservasAsistirDAO();
		this.DAOList.add(reservasAsistirDAO);
		this.visitantesDAO = new VisitantesDAO();
		this.DAOList.add(visitantesDAO);
		this.asistirDAO = new AsistirDAO();
		this.DAOList.add(asistirDAO);
		this.atraccionsFamiliaresDAO = new AtraccionsFamiliaresDAO();
		this.DAOList.add(atraccionsFamiliaresDAO);
		this.atraccionSoAdultosDAO = new AtraccionSoAdultosDAO();
		this.DAOList.add(atraccionSoAdultosDAO);
		this.djDAO = new DjDAO();
		this.DAOList.add(djDAO);
		this.sistemasDeAudioDAO = new SistemasDeAudioDAO();
		this.DAOList.add(sistemasDeAudioDAO);
		this.traballadorParqueDAO = new TraballadorParqueDAO();
		this.DAOList.add(traballadorParqueDAO);
		this.musicaDAO = new MusicaDAO();
		this.DAOList.add(musicaDAO);
		this.medioDAO = new MedioDAO();
		this.DAOList.add(medioDAO);


		/* Establecer la conexión */
		this.establishConnection(UserType.Guest);
	}

	/**
	 * Establece la conexión con la base de datos mediante el driver JDBC
	 * @param userType Tipo de usuario. Puede tomar el valor Admin (administrador) o Guest (invitado)
	 */
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

	/**
	 * Crea la base de datos en caso de que no esté creada ya y se devuelve a sí misma. Es parte del patrón Singleton
	 * @return La propia base de datos
	 */
	public static DataBase getCurrentDB() {
		if (DataBase.currentDB == null) {
			DataBase.currentDB = new DataBase(UserType.Guest);
		}
		return DataBase.currentDB;
	}

	/**
	 * Cierra la conexión con la base de datos
	 */
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


	/**
	 * Comprueba si el usuario ha iniciado sesión correctamente y que los privilegios de la conexión coinciden con los del usuario
	 * @param username Nombre de usuario
	 * @param password Contraseña del usuario
	 * @return true si el usuario ha podido iniciar sesión y false en caso contrario
	 */
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

	public void deleteUser(String username) { userDAO.deleteUserByUsername(username);}


	/** Métodos de RideDAO */
	public List<Atraccion> getAllRides(){
		return rideDAO.getAllRides();
	}
	public void borrarAtraccion(Atraccion atraccion) throws SQLException {
		rideDAO.deleteRide(atraccion.getNome());
	}


	/** Métodos de RestaurantDAO */
	public List<Hostalaria> getAllRestaurants() throws SQLException {
		return restaurantDAO.getAllRestaurants();
	}

	public void updateRestaurant (Hostalaria hostalaria) { restaurantDAO.updateRestaurant(hostalaria);}


	/** Métodos de EspectaculoDAO */
	public List<Espectaculo> getAllEspectaculos(){
		return espectaculoDAO.getAllEspectaculos();
	}

	public void insertarEspectaculo(Espectaculo espectaculo) throws SQLException{
		espectaculoDAO.insertarEspectaculo(espectaculo);
	}

	public void borrarEspectaculo(Espectaculo espectaculo) {
		espectaculoDAO.borrarEspectaculo(espectaculo);
	}


	/** Métodos de RatingDAO */
	public List<Valoracion> getAllRatings(){
		return ratingDAO.getAllRatings();
	}

	public void newRating(String descricion, int puntuacion){ ratingDAO.newRating(descricion, puntuacion); }


	/** Métodos de ReservasXantarDAO */
	public List<ReservaXantar> getAllReservas() {
		return reservasDAO.getAllReservas();
	}

	public List<ReservaXantar> getAllReservasDNI(User user) throws SQLException {
		return reservasDAO.getAllReservasDNI(user);
	}

	public void insertarReservaXantar(ReservaXantar reserva) throws SQLException{
		reservasDAO.insertarReservaXantar(reserva);
	}

	public void borrarReservaXantar(ReservaXantar reserva) {
		reservasDAO.borrarReservaXantar(reserva);
	}


	/** Métodos de ReservasAsistirDAO */
	public List<ReservaAsistir> getAllReservasAsistir() {
		return reservasAsistirDAO.getAllReservas();
	}

	public List<ReservaAsistir> getAllReservasAsistirDNI(User user) {
		return reservasAsistirDAO.getAllReservasDNI(user);
	}

	public void insertarReservaAsistir(ReservaAsistir reserva) throws SQLException{
		reservasAsistirDAO.insertarReservaAsistir(reserva);
	}

	public void borrarReservaAsistir(ReservaAsistir reserva) {
		reservasAsistirDAO.borrarReservaAsistir(reserva);
	}


	/** Métodos de ReservasIrDAO */
	public List<ReservaIrAtraccion> getAllReservasIr() {
		return reservasIrDA0.getAllReservas();
	}

	public List<ReservaIrAtraccion> getAllReservasIr(User user) throws SQLException {
		return reservasIrDA0.getAllReservas(user);
	}

	public void insertarReservaIr(ReservaIrAtraccion reserva) throws SQLException{
		reservasIrDA0.insertarReservaIr(reserva);
	}

	public void borrarReservaIr(ReservaIrAtraccion reserva) {
		reservasIrDA0.borrarReservaIr(reserva);
	}


	/** Métodos de ZonaDAO */
	public List<Zona> getAllZones(){return zonaDAO.getAllZones();}
	public void borrarZona(Zona zona) throws SQLException {
		zonaDAO.borrarZona(zona);
	}


	/** Métodos de VisitantesDAO */
	public List<Visitante> getAllVisitantes(){return visitantesDAO.getAllVisitantes();}
	public void borrarVisitante(Visitante visitante) throws SQLException {
		visitantesDAO.borrarVisitante(visitante);
	}

	public List<Asistir> getAllAsistir(){return asistirDAO.getAllAsistir();}
	public void borrarAsistir(Asistir asistir) throws SQLException {
		asistirDAO.borrarAsistir(asistir);
	}
	public List<AtraccionFamiliar> getAllAtraccionsFamiliares(){return atraccionsFamiliaresDAO.getAllAtraccionsFamiliares();}
	public void borrarAtraccionsFamiliares(AtraccionFamiliar atraccion) throws SQLException {
		atraccionsFamiliaresDAO.borrarAtraccionFamiliar(atraccion);
	}

	public List<AtraccionSoAdultos> getAllAtraccionSoAdultos(){return atraccionSoAdultosDAO.getAllAtraccionSoAdultos();}
	public void borrarAtraccionSoAdultos(AtraccionSoAdultos atraccion) throws SQLException {
		atraccionSoAdultosDAO.borrarAtraccionSoAdultos(atraccion);
	}
	public List<DJ> getAllDj(){return djDAO.getAllDj();}
	public void borrarDj(DJ dj) throws SQLException {
		djDAO.borrarDj(dj);
	}
	/** Métodos de SistemasDeAudioDAO */
	public List<SistemaDeAudio> getAllSistemas() throws SQLException{
		return sistemasDeAudioDAO.getAllSistemas();
	}

	public void borrarSistema(SistemaDeAudio sistema) {
		sistemasDeAudioDAO.borrarSistema(sistema);
	}


	/** Métodos de TraballadorParqueDAO */
	public List<TraballadorParque> getAllWorkers() throws SQLException{
		return traballadorParqueDAO.getAllWorkers();
	}

	public void borrarTrabajador(TraballadorParque traballadorParque) {
		traballadorParqueDAO.borrarTrabajador(traballadorParque);
	}


	/** Métodos de MusicaDAO */
	public List<Musica> getAllMusica() throws SQLException{
		return musicaDAO.getAllMusic();
	}

	public void borrarMusica(Musica musica) {
		musicaDAO.borrarMusica(musica);
	}


	/** Métodos de MedioDAO */
	public List<Medio> getAllMedios() throws SQLException{
		return medioDAO.getAllMedios();
	}

	public void borrarMedio(Medio medio) {
		medioDAO.borrarMedio(medio);
	}
}


