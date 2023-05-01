package baseDatos;

import modelo.Reproducir;

import java.sql.*;

public class ReproducirDAO extends AbstractDAO{

    public ReproducirDAO() {
    }

    public ReproducirDAO(Connection conexion) {
        super.setConexion(conexion);
    }

    public java.util.List<Reproducir> getAllReproducir() {

        java.util.List<Reproducir> resultado = new java.util.ArrayList<>();
        Reproducir reproducirirActual;
        Connection con;

        PreparedStatement stm = null;
        ResultSet rs;

        con = this.getConexion();

        String consulta = "SELECT *" +
                "FROM reproducir";

        try {

            stm = con.prepareStatement(consulta);
            rs = stm.executeQuery();

            while (rs.next()) {
                reproducirirActual = new Reproducir(
                        rs.getDate("data"),
                        rs.getString("musica"),
                        rs.getString("sistema")
                );

                resultado.add(reproducirirActual);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return resultado;
    }

    public void borrarReproducir(Reproducir reproducir) throws SQLException {

        Connection con = this.getConexion();
        PreparedStatement stm = null;
        String consulta = "DELETE FROM reproducirir WHERE data = ? AND musica = ? AND sistema = ?;";

        try {

            stm = con.prepareStatement(consulta);
            stm.setDate(1, (Date) reproducir.getData());
            stm.setString(2, reproducir.getMusica());
            stm.setString(3, reproducir.getSistema());
            stm.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
