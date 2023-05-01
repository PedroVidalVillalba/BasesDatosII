package baseDatos;

import modelo.Ir;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IrDAO extends AbstractDAO{

    public IrDAO() {
    }

    public IrDAO(Connection conexion) {
        super.setConexion(conexion);
    }

    /**
     * Método para obtener todos los pasajeros de las atracciones.
     *
     * @return
     * @throws SQLException
     */
    public java.util.List<Ir> getAllIr() {

        java.util.List<Ir> resultado = new java.util.ArrayList<>();
        Ir irActual;
        Connection con;

        PreparedStatement stm = null;
        ResultSet rs;

        con = this.getConexion();

        String consulta = "SELECT *" +
                "FROM ir";


        try {

            stm = con.prepareStatement(consulta);
            rs = stm.executeQuery();

            while (rs.next()) {
                irActual = new Ir(
                        rs.getString("visitante"),
                        rs.getString("atraccion"),
                        rs.getTime("horaInicio"),
                        rs.getDate("fecha")
                );

                resultado.add(irActual);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return resultado;
    }

    public void borrarIr(Ir ir) throws SQLException {

        Connection con = this.getConexion();
        PreparedStatement stm = null;
        String consulta = "DELETE FROM ir WHERE visitante = ? AND atraccion = ? AND horainicio = ? AND fecha = ?;";

        try {

            stm = con.prepareStatement(consulta);
            stm.setString(1, ir.getVisitante());
            stm.setString(2, ir.getAtraccion());
            stm.setTime(3, ir.getHoraInicio());
            stm.setDate(4, ir.getFecha());
            stm.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
