package baseDatos;

import modelo.Musica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Interfaz de acceso a los datos relacionados con la música y canciones, encapsula la lógica de acceso a los datos para que
 * las capas superiores de la aplicación puedan interactuar con ellos de manera más sencilla y segura.
 * Extiende a la clase AbstractDAO
 */
public class MusicaDAO extends AbstractDAO {
    public MusicaDAO (){}
    public MusicaDAO  (Connection conexion){
        super.setConexion(conexion);
    }

    /**
     * Método para obtener todos los trabajadores del parque.
     * @return Lista con todos los trbajadores del parque.
     * @throws SQLException
     */
    public java.util.List<Musica> getAllMusic(){

        java.util.List<Musica> resultado = new java.util.ArrayList<>();
        Musica musicaactual;
        Connection con;

        PreparedStatement stm = null;
        ResultSet rs;

        con = this.getConexion();

        String consulta = "SELECT * " +
                "FROM musica;";

        try{

            stm = con.prepareStatement(consulta);
            rs = stm.executeQuery();

            while (rs.next()){
                musicaactual = new Musica(
                        rs.getString("codigoCancion"),
                        rs.getString("nome"),
                        rs.getString("clasificacion"),
                        rs.getInt("popularidade"),
                        rs.getString("artista"),
                        rs.getString("album")
                );
                resultado.add(musicaactual);
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return resultado;

    }

    /**
     * Método para eliminar una canción por un administrador
     * @param musica Canción a eliminar
     * @throws SQLException
     */
    public void borrarMusica(Musica musica){
        Connection con = this.getConexion();
        PreparedStatement stm = null;
        String consulta = "DELETE FROM musica WHERE codigocancion = ?;";

        try {
            stm = con.prepareStatement(consulta);
            stm.setString(1, musica.getCodigoCancion());
            stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
