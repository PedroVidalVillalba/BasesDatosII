package baseDatos;

import modelo.*;

import java.sql.*;
import java.time.LocalDate;

/**
 * Interfaz de acceso a los datos relacionados con las valoraciones, encapsula la lógica de acceso a los datos para que
 * las capas superiores de la aplicación puedan interactuar con ellos de manera más sencilla y segura.
 */
public class RatingDAO extends AbstractDAO {
    public RatingDAO() {}
    public RatingDAO(Connection conexion){
        super.setConexion(conexion);
    }

    /**
     * Método para obtener todas las valoraciones.
     * @return
     * @throws SQLException
     */
    public java.util.List<Valoracion> getAllRatings(){

        java.util.List<Valoracion> resultado = new java.util.ArrayList<>();
        Valoracion valoracionactual;
        Connection con;

        PreparedStatement stm = null;
        ResultSet rs;

        con = this.getConexion();

        String consulta = "SELECT v.identificador, v.data, v.descricion, v.puntuacion, vis.dni, vis.nome, " +
                "vis.nacionalidade, vis. telefono, vis.dataNacemento, vis.altura, m.nomeMedio, " +
                "m.tipo, m.prezo, m.capacidade, m.velocidade " +
                "FROM valoracions v, visitantes vis, medios m " +
                "where v.visitante = vis.dni and vis.medioTransporte = m.nomeMedio";

        try{

            stm = con.prepareStatement(consulta);
            rs = stm.executeQuery();

            while (rs.next()){
                valoracionactual = new Valoracion(
                        rs.getString("identificador"),
                        rs.getDate("data"),
                        rs.getString("descricion"),
                        rs.getInt("puntuacion"),
                        new Visitante(
                                rs.getString("dni"),
                                rs.getString("nome"),
                                rs.getString("nacionalidade"),
                                rs.getString("telefono"),
                                rs.getDate("dataNacemento"),
                                rs.getInt("altura"),
                                new Medio(
                                        rs.getString("nomeMedio"),
                                        rs.getString("tipo"),
                                        rs.getFloat("prezo"),
                                        rs.getInt("capacidade"),
                                        rs.getFloat("velocidade")
                                )
                        )
                );
                resultado.add(valoracionactual);
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return resultado;

    }

    public void newRating(String descricion, int puntuacion){
        Connection connection = this.getConexion();


        String query = "INSERT INTO valoracions(data, descricion, puntuacion, visitante) " +
                "VALUES(?,?,?,?);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            //preparedStatement.setString(1, rateId());
            preparedStatement.setDate(1, Date.valueOf(LocalDate.now()));
            preparedStatement.setString(2, descricion);
            preparedStatement.setInt(3, puntuacion);
            preparedStatement.setString(4, DataBase.getCurrentDB().getUser().getDni());

            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            System.err.println(exception.getMessage());
        }

    }

/*    public String rateId(){
        Connection connection = this.getConexion();
        PreparedStatement stm = null;
        ResultSet rs;
        String query = "SELECT identificador " +
                "FROM valoracions " +
                "WHERE identificador >= ALL(SELECT identificador FROM valoracions);";
        String resultado = null;

        try{

            stm = connection.prepareStatement(query);
            rs = stm.executeQuery();

            while (rs.next()){
                resultado = rs.getString("identificador");
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }

        if(resultado != null) return Integer.toString( Integer.parseInt(resultado) + 1 );
        return "00000";
    }*/

}
