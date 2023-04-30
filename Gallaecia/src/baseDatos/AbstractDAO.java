package baseDatos;

import java.sql.Connection;

/**
 * Clase abstracta, padre de todas las clases que utilizan el patrón DAO, el cual permite
 * ocultar los detalles de implementación al cliente.
 */
public abstract class AbstractDAO {
	private Connection conexion;

	protected Connection getConexion() {
		return this.conexion;
	}

	protected void setConexion(Connection conexion) {
		this.conexion = conexion;
	}
}
