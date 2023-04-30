package baseDatos;

import modelo.Asistir;
import modelo.Espectaculo;
import modelo.Zona;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AsistirDAO extends AbstractDAO {

    public AsistirDAO() {
    }

    public AsistirDAO(Connection conexion) {
        super.setConexion(conexion);
    }

    /**
     * Método para obtener todas los asistir.
     *
     * @return
     * @throws SQLException
     */
    public java.util.List<Asistir> getAllAsistir() {

        java.util.List<Asistir> resultado = new java.util.ArrayList<>();
        Asistir asistirActual;
        Connection con;

        PreparedStatement stm = null;
        ResultSet rs;

        con = this.getConexion();

        String consulta = "SELECT *" +
                "FROM asistir a ";


        try {

            stm = con.prepareStatement(consulta);
            rs = stm.executeQuery();

            while (rs.next()) {
                asistirActual = new Asistir(
                        rs.getString("visitante"),
                        rs.getString("espectaculo"),
                        rs.getTime("horaInicio"),
                        rs.getDate("fecha")
                );

                resultado.add(asistirActual);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return resultado;
    }

    public void borrarAsistir(Asistir asistir) throws SQLException {

        Connection con = this.getConexion();
        PreparedStatement stm = null;
        String consulta = "DELETE FROM asistir WHERE visitante = ? AND espectaculo = ? AND horainicio = ? AND fecha = ?;";

        try {

            stm = con.prepareStatement(consulta);
            stm.setString(1, asistir.getVisitante());
            stm.setString(2, asistir.getEspectaculo());
            stm.setTime(3, asistir.getHoraInicio());
            stm.setDate(4, asistir.getFecha());
            stm.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}