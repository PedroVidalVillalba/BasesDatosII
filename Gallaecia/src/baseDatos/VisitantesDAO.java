package baseDatos;



import modelo.Espectaculo;
import modelo.Medio;
import modelo.Visitante;
import modelo.Zona;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Interfaz de acceso a los datos relacionados con las visitantes, encapsula la lógica de acceso a los datos para que
 * las capas superiores de la aplicación puedan interactuar con ellos de manera más sencilla y segura.
 * Extiende a la clase AbstractDAO
 */
public class VisitantesDAO extends AbstractDAO{
    public VisitantesDAO() {}
    public VisitantesDAO (Connection conexion){
        super.setConexion(conexion);
    }

    /**
     * Método para obtener todos los visitantes.
     * @return Lista con todos los visitantes.
     * @throws SQLException
     */
    public java.util.List<Visitante> getAllVisitantes(){

        java.util.List<Visitante> resultado = new java.util.ArrayList<>();
        Visitante visitanteActual;
        Connection con;

        PreparedStatement stm = null;
        ResultSet rs;

        con = this.getConexion();

        /* El tema de los medios de transporte no está implementado */
//        String consulta = "SELECT *" +
//                "FROM visitantes v " +
//                "JOIN medios m ON v.mediotransporte = m.nomemedio;";
        String consulta = "SELECT * " +
                "FROM visitantes;";

        try {

            stm = con.prepareStatement(consulta);
            rs = stm.executeQuery();

            while (rs.next()) {
                visitanteActual = new Visitante(
                        rs.getString("dni"),
                        rs.getString("nome"),
                        rs.getString("nacionalidade"),
                        rs.getString("telefono"),
                        rs.getDate("datanacemento"),
                        rs.getInt("altura"), null); /*,
                        new Medio(rs.getString("nomemedio"),
                                rs.getString("tipo"),
                                rs.getFloat("prezo"),
                                rs.getInt("capacidade"),
                                rs.getFloat("velocidade"))

                );*/

                resultado.add(visitanteActual);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return resultado;

    }

    /**
     * Método para eliminar a un visitante concreto por un administrador
     * @param visitante Visitante concreto a eliminar
     * @throws SQLException
     */
    public void borrarVisitante(Visitante visitante) throws SQLException {

        Connection con = this.getConexion();
        PreparedStatement stm = null;
        String consulta = "DELETE FROM visitantes WHERE nome = ?;";

        try {

            stm = con.prepareStatement(consulta);
            stm.setString(1, visitante.getNome());
            stm.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
