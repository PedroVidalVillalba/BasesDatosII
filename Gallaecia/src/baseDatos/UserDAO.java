package baseDatos;

import modelo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends AbstractDAO {
    public UserDAO(Connection connection) {
        super.setConexion(connection);
    }

    public User login(String username, String password) {
        List<User> result = new ArrayList<>();
        User user = null;
        Connection connection;

        connection = this.getConexion();

        String query =  "select id_user, dni, nome, username, password, is_admin " +
                        "from Users " +
                        "where username = '" + username +"' " +
                        "  and password = '" + new Password(username, password) + "'";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                user = new User(resultSet.getInt("id_user"),
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
}
