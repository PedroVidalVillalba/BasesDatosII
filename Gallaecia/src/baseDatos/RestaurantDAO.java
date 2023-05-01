package baseDatos;

import modelo.Zona;
import modelo.Hostalaria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Interfaz de acceso a los datos relacionados con los restaurantes, encapsula la lógica de acceso a los datos para que
 * las capas superiores de la aplicación puedan interactuar con ellos de manera más sencilla y segura.
 * Extiende a la clase AbstractDAO
 */
public class RestaurantDAO extends AbstractDAO{
	public RestaurantDAO() {}
	public RestaurantDAO (Connection conexion){
		super.setConexion(conexion);
	}

	/**
	 * Método para obtener todos los restaurantes.
	 * @return Lista con todos los restaurantes.
	 * @throws SQLException
	 */
	public java.util.List<Hostalaria> getAllRestaurants() throws SQLException {

		java.util.List<Hostalaria> resultado = new java.util.ArrayList<Hostalaria>();
		Hostalaria atraccionactual;
		Connection con = this.getConexion();
		PreparedStatement stm;
		ResultSet rs;

		String consulta = "SELECT a.nomeEstablecemento, a.aforo, a.horaInicio, a.horaFin, " +
				"z.nome as nomezona, z.extension, z.coordenadax, z.coordenaday " +
				"FROM hostalaria a " +
				"JOIN zonas z ON a.zona = z.nome";


		//System.out.println(con.isClosed());

		if (con!=null) {
			try{
				stm = con.prepareStatement(consulta);
				rs = stm.executeQuery();

				while (rs.next()){
					atraccionactual = new Hostalaria(
							rs.getString("nomeEstablecemento"),
							rs.getInt("aforo"),
							rs.getTime("horaInicio"),
							rs.getTime("horaFin"),
							new Zona(
									rs.getString("nomezona"),
									rs.getFloat("extension"),
									rs.getFloat("coordenadax"),
									rs.getFloat("coordenaday")
							)
					);
					resultado.add(atraccionactual);
				}
			} catch (SQLException e){
				System.err.println(e.getMessage());
			}
		}


		return resultado;

	}

	/**
	 * Método para actualizar los datos de un determinado restaurante por un administrador
	 * @param hostalaria Restaurante a modificar
	 */
	public void updateRestaurant(Hostalaria hostalaria) {

		Connection con = this.getConexion();
		PreparedStatement stm = null;
		String consulta = "UPDATE hostalaria SET aforo = ?, horainicio = ?, horafin = ?, zona = ? WHERE nomeestablecemento = ?;";

		try {

			stm = con.prepareStatement(consulta);
			stm.setInt(1, hostalaria.getAforo());
			stm.setTime(2, hostalaria.getHoraInicio());
			stm.setTime(3, hostalaria.getHoraFin());
			stm.setString(4, hostalaria.getZona().getNome());
			stm.setString(5, hostalaria.getNomeEstablecemento());
			stm.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void deleteRestaurant(String nomeEstablecemento) {

		Connection con = this.getConexion();
		PreparedStatement stm = null;
		String consulta = "DELETE FROM hostalaria WHERE nomeestablecemento = ?;";

		try {

			stm = con.prepareStatement(consulta);
			stm.setString(1, nomeEstablecemento);
			stm.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}
