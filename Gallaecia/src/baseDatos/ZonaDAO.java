package baseDatos;


import modelo.Espectaculo;
import modelo.Zona;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ZonaDAO extends AbstractDAO{
    public ZonaDAO() {}
    public ZonaDAO (Connection conexion){
        super.setConexion(conexion);
    }

    /**
     * Método para obtener todas las zonas.
     * @return
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
}
