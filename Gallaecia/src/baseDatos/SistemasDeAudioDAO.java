package baseDatos;

import modelo.Atraccion;
import modelo.Espectaculo;
import modelo.SistemaDeAudio;
import modelo.Zona;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Interfaz de acceso a los datos relacionados con los sistemas de audio, encapsula la lógica de acceso a los datos para que
 * las capas superiores de la aplicación puedan interactuar con ellos de manera más sencilla y segura.
 * Extiende a la clase AbstractDAO
 */
public class SistemasDeAudioDAO extends AbstractDAO{
    public SistemasDeAudioDAO(){}
    public SistemasDeAudioDAO (Connection conexion){
        super.setConexion(conexion);
    }

    /**
     * Método para obtener todos los sistemas de audio.
     * @return Lista con todos los sistemas de audio.
     * @throws SQLException
     */
    public java.util.List<SistemaDeAudio> getAllSistemas(){

        java.util.List<SistemaDeAudio> resultado = new java.util.ArrayList<>();
        SistemaDeAudio sistemaactual;
        Connection con;

        PreparedStatement stm = null;
        ResultSet rs;

        con = this.getConexion();

        String consulta = "SELECT s.identificador, s.funcion, s.descricion, " +
                "z.nome as nomezona, z.extension, z.coordenadax, z.coordenaday " +
                "FROM sistemasdeaudio s " +
                "JOIN zonas z ON s.zona = z.nome;";

        try{

            stm = con.prepareStatement(consulta);
            rs = stm.executeQuery();

            while (rs.next()){
                sistemaactual = new SistemaDeAudio(
                        rs.getString("identificador"),
                        rs.getString("funcion"),
                        rs.getString("descricion"),
                        new Zona(
                                rs.getString("nomezona"),
                                rs.getFloat("extension"),
                                rs.getFloat("coordenadax"),
                                rs.getFloat("coordenaday")
                        )
                );
                resultado.add(sistemaactual);
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return resultado;

    }

    /**
     * Método para eliminar un sistema de audio por un administrador
     * @param sistema Sistema de audio a eliminar
     * @throws SQLException
     */
    public void borrarSistema(SistemaDeAudio sistema){
        Connection con = this.getConexion();
        PreparedStatement stm = null;
        String consulta = "DELETE FROM sistemasdeaudio WHERE identificador = ?;";

        try {
            stm = con.prepareStatement(consulta);
            stm.setString(1, sistema.getIdentificador());
            stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
