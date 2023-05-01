package baseDatos;


import modelo.DJ;
import modelo.Espectaculo;
import modelo.Zona;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Interfaz de acceso a los datos relacionados con las zonas del parque, encapsula la lógica de acceso a los datos para que
 * las capas superiores de la aplicación puedan interactuar con ellos de manera más sencilla y segura.
 * Extiende a la clase AbstractDAO
 */
public class ZonaDAO extends AbstractDAO{
    public ZonaDAO() {}
    public ZonaDAO (Connection conexion){
        super.setConexion(conexion);
    }

    /**
     * Método para obtener todas las zonas.
     * @return Lista con todas las zonas.
     * @throws SQLException
     */
    public java.util.List<Zona> getAllZones(){

        java.util.List<Zona> resultado = new java.util.ArrayList<>();
        Zona espectaculoActual;
        Connection con;

        PreparedStatement stm = null;
        ResultSet rs;

        con = this.getConexion();

        String consulta = "SELECT *" +
                        "FROM zonas z ";

        try {

            stm = con.prepareStatement(consulta);
            rs = stm.executeQuery();

            while (rs.next()) {
                espectaculoActual = new Zona(
                        rs.getString("nome"),
                        rs.getFloat("extension"),
                        rs.getFloat("coordenadax"),
                        rs.getFloat("coordenaday")

                        );

                resultado.add(espectaculoActual);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return resultado;

    }

    public void borrarZona(Zona zona) throws SQLException {

        Connection con = this.getConexion();
        PreparedStatement stm = null;
        String consulta = "DELETE FROM zona WHERE nome = ?;";

        try {

            stm = con.prepareStatement(consulta);
            stm.setString(1, zona.getNome());
            stm.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
