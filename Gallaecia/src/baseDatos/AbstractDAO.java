package baseDatos;

import java.sql.Connection;
public abstract class AbstractDAO {
    private Connection conexion;

    protected Connection getConexion() {
        return this.conexion;
    }

    protected void setConexion(Connection conexion) {
        this.conexion = conexion;
    }
}
