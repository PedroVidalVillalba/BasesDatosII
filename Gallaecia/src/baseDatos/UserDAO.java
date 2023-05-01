package baseDatos;

import modelo.User;
import modelo.Visitante;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Interfaz de acceso a los datos relacionados con los usuarios, encapsula la lógica de acceso a los datos para que
 * las capas superiores de la aplicación puedan interactuar con ellos de manera más sencilla y segura.
 * Extiende a la clase AbstractDAO
 */
public class UserDAO extends AbstractDAO {
	public UserDAO() {}
	public UserDAO(Connection connection) {
		super.setConexion(connection);
	}

	/**
	 * Método que permite iniciar sesión a un usuario
	 * @param username Nombre de usuario
	 * @param password Contraseña del usuario
	 * @return
	 */
	public User login(String username, String password) {
		List<User> result = new ArrayList<>();
		User user = null;
		Connection connection = this.getConexion();

		String query =  "select dni, nome, username, password, is_admin " +
				"from Users " +
				"where username = ? " +
				"  and password = ?;";

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, new Password(username, password).toString());
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				user = new User(
						resultSet.getString("dni"),
						resultSet.getString("nome"),
						resultSet.getString("username"),
						resultSet.getString("password"),
						resultSet.getBoolean("is_admin"));
				result.add(user);
			}
			if (result.size() > 1) {
				System.err.println("ERROR: Hay más de un usuario y contraseña repetidos!");
				user = null;
			}
		} catch (SQLException exception) {
			System.err.println(exception.getMessage());
		}

		return user;
	}

	/**
	 * Método que permite el registro de nuevos usuarios en la aplicación
	 * @param visitante Datos generales del usuario
	 * @param user Datos de uso interno del usuario
	 * @return
	 */
	public boolean signUp(Visitante visitante, User user) {
		boolean success;
		Connection connection = this.getConexion();

		String query =
				"insert into Visitantes(dni, nome, nacionalidade, telefono, datanacemento, altura) " +
				"values(?, ?, ?, ?, ?, ?);" +
				"insert into Users(dni, nome, username, password, is_admin) " +
				"values(?, ?, ?, ?, ?);";

		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			connection.setAutoCommit(false);
			preparedStatement.setString(1, visitante.getDni());
			preparedStatement.setString(2, visitante.getNome());
			preparedStatement.setString(3, visitante.getNacionalidade());
			preparedStatement.setString(4, visitante.getTelefono());
			//preparedStatement.setDate(5, (visitante.getDataNacemento() == null ? null : Date.valueOf(visitante.getDataNacemento())));
			preparedStatement.setDate(5, (visitante.getDataNacemento() == null ? null : visitante.getDataNacemento()));
			preparedStatement.setInt(6, visitante.getAltura());
			// preparedStatement.setString(7, visitante.getMedio().toString()); // No implementado aún. Cuando se haga hay que cambiar la numeración de las siguientes
			preparedStatement.setString(7, user.getDni());
			preparedStatement.setString(8, user.getNome());
			preparedStatement.setString(9, user.getUsername());
			preparedStatement.setString(10, user.getPassword().toString());
			preparedStatement.setBoolean(11, user.isAdmin());
			preparedStatement.executeUpdate();
			connection.commit();
			success = true;
		} catch (SQLException exception) {
			try {
				System.err.println(exception.getMessage());
				connection.rollback();
			} catch (SQLException exception1) {
				System.err.println(exception1.getMessage());
			}
			success = false;
		} finally {
			try {
				connection.setAutoCommit(true);
			} catch (SQLException exception) {
				System.err.println(exception.getMessage());
			}
		}

		return success;
	}

	/**
	 * Método para obtener todos los usuarios registrados por un administrador
	 * @return Lista con todos los usuarios registrados
	 */
	public List<User> getAllUsers() {
		List<User> result = new ArrayList<>();
		User user = null;
		Connection connection = this.getConexion();

		String query = 	"select dni, nome, username, is_admin " +
						"from users;";

		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				user = new User(
						resultSet.getString("dni"),
						resultSet.getString("nome"),
						resultSet.getString("username"),
						resultSet.getBoolean("is_admin"));
				result.add(user);
			}
		} catch (SQLException exception) {
			System.err.println(exception.getMessage());
		}

		return result;
	}

	/**
	 * Método para eliminar a un usuario registrado por un administrador
	 * @param username Nombre de usuario a eliminar
	 */
	public void deleteUserByUsername(String username) {
		Connection con = this.getConexion();
		PreparedStatement stm = null;
		String consulta = "DELETE FROM users WHERE username = ?;";

		try {
			stm = con.prepareStatement(consulta);
			stm.setString(1, username);
			stm.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

}
