package baseDatos;

import modelo.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Interfaz de acceso a los datos relacionados con las atracciones, encapsula la lógica de acceso a los datos para que
 * las capas superiores de la aplicación puedan interactuar con ellos de manera más sencilla y segura.
 */
public class DjDAO extends AbstractDAO{
    public DjDAO() {}
    public DjDAO (Connection conexion){
        super.setConexion(conexion);
    }

    /**
     * Método para obtener todas los DJs.
     * @return
     * @throws SQLException
     */
    public java.util.List<DJ> getAllDj(){

        java.util.List<DJ> resultado = new java.util.ArrayList<>();
        DJ djActual;
        Connection con;

        PreparedStatement stm = null;
        ResultSet rs;

        con = this.getConexion();

        String consulta = "SELECT d.dni, d.nome, d.rua, d.numero, d.cp, d.localidade, d.salario, d.telefono, d.fechainicio, d.fechanacemento, d.formacion, d.sistema," +
                "s.identificador, s.funcion, s.descricion, s.zona," +
                "z.nome as nomezona, z.extension, z.coordenadax, z.coordenaday " +
                "FROM dj d, sistemasdeaudio s, zonas z " +
                "WHERE d.sistema = s.identificador AND s.zona = z.nome;";

        try{

            stm = con.prepareStatement(consulta);
            rs = stm.executeQuery();

            while (rs.next()){
                djActual = new DJ(
                        rs.getString("dni"),
                        rs.getString("nome"),
                        rs.getString("rua"),
                        rs.getInt("numero"),
                        rs.getInt("cp"),
                        rs.getString("localidade"),
                        rs.getFloat("salario"),
                        rs.getString("telefono"),
                        rs.getDate("fechainicio"),
                        rs.getDate("fechanacemento"),
                        rs.getString("formacion"),
                        new SistemaDeAudio(
                                rs.getString("identificador"),
                                rs.getString("funcion"),
                                rs.getString("descricion"),
                                new Zona(
                                        rs.getString("nomezona"),
                                        rs.getFloat("extension"),
                                        rs.getFloat("coordenadax"),
                                        rs.getFloat("coordenaday")

                                )
                        )
                );
                resultado.add(djActual);
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return resultado;

    }



    public void borrarDj(DJ dj) throws SQLException {

        Connection con = this.getConexion();
        PreparedStatement stm = null;
        String consulta = "DELETE FROM dj WHERE dni = ?;";

        try {

            stm = con.prepareStatement(consulta);
            stm.setString(1, dj.getDni());
            stm.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}