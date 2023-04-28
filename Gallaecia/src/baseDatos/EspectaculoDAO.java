package baseDatos;
import modelo.Espectaculo;
import modelo.Zona;
import modelo.Atraccion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Interfaz de acceso a los datos relacionados con los espectáculos, encapsula la lógica de acceso a los datos para que
 * las capas superiores de la aplicación puedan interactuar con ellos de manera más sencilla y segura.
 */

public class EspectaculoDAO extends AbstractDAO{

    public EspectaculoDAO() {}
    public EspectaculoDAO (Connection conexion){
        super.setConexion(conexion);
    }

    /**
     * Método para obtener todas las atracciones.
     * @return
     * @throws SQLException
     */
    public java.util.List<Espectaculo> getAllEspectaculos() {

        java.util.List<Espectaculo> resultado = new java.util.ArrayList<>();
        Espectaculo espectaculoActual;
        Connection con;

        PreparedStatement stm = null;
        ResultSet rs;

        con = this.getConexion();

        String consulta = "SELECT e.nome, e.horaInicio, e.horaFin, e.tematica, e.descricion, " +
                "z.nome as nomezona, z.extension, z.coordenadax, z.coordenaday " +
                "FROM espectaculos e " +
                "JOIN zonas z ON e.zona = z.nome";

        try {

            stm = con.prepareStatement(consulta);
            rs = stm.executeQuery();

            while (rs.next()) {
                espectaculoActual = new Espectaculo(
                        rs.getString("nome"),
                        rs.getTime("horaInicio"),
                        rs.getTime("horaFin"),
                        rs.getString("tematica"),
                        rs.getString("descricion"),
                        new Zona(
                                rs.getString("nomezona"),
                                rs.getFloat("extension"),
                                rs.getFloat("coordenadax"),
                                rs.getFloat("coordenaday")
                        )
                );
                resultado.add(espectaculoActual);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return resultado;
    }
    public void insertarEspectaculo(Espectaculo espectaculo) throws SQLException {

            Connection con = this.getConexion();
            PreparedStatement stmEspectaculo=null;
            stmEspectaculo=con.prepareStatement("insert into Espectaculos values (?,?,?,?,?,?)");
            try{
            stmEspectaculo.setString(1, espectaculo.getNome());
            stmEspectaculo.setTime(2, espectaculo.getHoraInicio());
            stmEspectaculo.setTime(3, espectaculo.getHoraFin());
            stmEspectaculo.setString(4,espectaculo.getTematica());
            stmEspectaculo.setString(5,espectaculo.getDescricion());
            stmEspectaculo.setString(6,espectaculo.getZona().getNome());
            stmEspectaculo.executeUpdate();

            } catch (SQLException e){
                System.out.println(e.getMessage());
            } finally {
                try {stmEspectaculo.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
            }

    }
}
