package baseDatos;

import modelo.Zona;
import modelo.Hostalaria;
import modelo.Reserva;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Interfaz de acceso a los datos relacionados con las atracciones, encapsula la lógica de acceso a los datos para que
 * las capas superiores de la aplicación puedan interactuar con ellos de manera más sencilla y segura.
 */
public class RestaurantDAO extends AbstractDAO{

	public RestaurantDAO (Connection conexion){
		super.setConexion(conexion);
	}

	/**
	 * Método para obtener todas las atracciones.
	 * @return
	 * @throws SQLException
	 */
	public java.util.List<Hostalaria> getAllRestaurants(){

		java.util.List<Hostalaria> resultado = new java.util.ArrayList<Hostalaria>();
		Hostalaria atraccionactual;
		Connection con;

		PreparedStatement stm = null;
		ResultSet rs;

		con = this.getConexion();

		String consulta = "SELECT a.nomeEstablecemento, a.aforo, a.horaInicio, a.horaFin, " +
				"z.nome as nomezona, z.extension, z.coordenadax, z.coordenaday " +
				"FROM hostalaria a " +
				"JOIN zonas z ON a.zona = z.nome";

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

		return resultado;

	}

	public void insertarReserva(Reserva reserva) throws SQLException{
		Connection con = this.getConexion();
		PreparedStatement stmLibro=null;
			stmLibro=con.prepareStatement("insert into reservashostalaria values (?,?,?,?,?)");
			stmLibro.setString(1, reserva.getNombre());
			stmLibro.setString(2, reserva.getHostalaria());
			stmLibro.setString(3, reserva.getHoraInicio());
			stmLibro.setString(4, reserva.getHoraFin());
			stmLibro.setDate(5,reserva.getFecha());
			stmLibro.executeUpdate();


	}
}
