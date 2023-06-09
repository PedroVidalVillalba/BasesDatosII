package baseDatos;

import modelo.Zona;
import modelo.Atraccion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Interfaz de acceso a los datos relacionados con las atracciones, encapsula la lógica de acceso a los datos para que
 * las capas superiores de la aplicación puedan interactuar con ellos de manera más sencilla y segura.
 * Extiende a la clase AbstractDAO
 */
public class RideDAO extends AbstractDAO{
	public RideDAO() {}
	public RideDAO (Connection conexion){
		super.setConexion(conexion);
	}

	/**
	 * Método para obtener todas las atracciones.
	 * @return Lista con todas las atracciones.
	 * @throws SQLException
	 */
	public java.util.List<Atraccion> getAllRides(){

		java.util.List<Atraccion> resultado = new java.util.ArrayList<>();
		Atraccion atraccionactual;
		Connection con;

		PreparedStatement stm = null;
		ResultSet rs;

		con = this.getConexion();

		String consulta = "SELECT a.nome, a.aforo, a.alturamin, a.customantemento, a.descricion, " +
				"z.nome as nomezona, z.extension, z.coordenadax, z.coordenaday " +
				"FROM atraccions a " +
				"JOIN zonas z ON a.zona = z.nome;";

		try{

			stm = con.prepareStatement(consulta);
			rs = stm.executeQuery();

			while (rs.next()){
				atraccionactual = new Atraccion(
						rs.getString("nome"),
						rs.getInt("aforo"),
						rs.getInt("alturamin"),
						rs.getFloat("customantemento"),
						rs.getString("descricion"),
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
			System.out.println(e.getMessage());
		}

		return resultado;

	}

	/**
	 * Método para añadir una atracción por un administrador
	 * @param atraccion Atracción a insertar
	 * @throws SQLException
	 */
	public void insertRide(Atraccion atraccion) throws SQLException {

		Connection con = this.getConexion();
		PreparedStatement stm = null;
		String consulta = "INSERT INTO atraccions (nome, aforo, alturamin, customantemento, descricion, zona) VALUES (?, ?, ?, ?, ?, ?);";

		try {

			stm = con.prepareStatement(consulta);
			stm.setString(1, atraccion.getNome());
			stm.setInt(2, atraccion.getAforo());
			stm.setInt(3, atraccion.getAlturaMin());
			stm.setFloat(4, atraccion.getCustoMantemento());
			stm.setString(5, atraccion.getDescricion());
			stm.setString(6, atraccion.getZona().getNome());
			stm.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Método para actualizar los datos de una atracción por un administrador
	 * @param atraccion Atracción a modificar
	 * @throws SQLException
	 */
	public void updateRide(Atraccion atraccion) throws SQLException {

		Connection con = this.getConexion();
		PreparedStatement stm = null;
		String consulta = "UPDATE atraccions SET aforo = ?, alturamin = ?, customantemento = ?, descricion = ?, zona = ? WHERE nome = ?;";

		try {

			stm = con.prepareStatement(consulta);
			stm.setInt(1, atraccion.getAforo());
			stm.setInt(2, atraccion.getAlturaMin());
			stm.setFloat(3, atraccion.getCustoMantemento());
			stm.setString(4, atraccion.getDescricion());
			stm.setString(5, atraccion.getZona().getNome());
			stm.setString(6, atraccion.getNome());
			stm.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Método para eliminar una atracción por un administrador
	 * @param nombre Nombre de la atracción a eliminar
	 * @throws SQLException
	 */
	public void deleteRide(String nombre) throws SQLException {

		Connection con = this.getConexion();
		PreparedStatement stm = null;
		String consulta = "DELETE FROM atraccions WHERE nome = ?;";

		try {

			stm = con.prepareStatement(consulta);
			stm.setString(1, nombre);
			stm.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

}
