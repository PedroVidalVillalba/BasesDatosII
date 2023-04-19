package baseDatos;

import modelo.User;
import modelo.Visitante;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends AbstractDAO {
	public UserDAO() {}
	public UserDAO(Connection connection) {
		super.setConexion(connection);
	}

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
			preparedStatement.setDate(5, (visitante.getDataNacemento() == null ? null : Date.valueOf(visitante.getDataNacemento())));
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
}
