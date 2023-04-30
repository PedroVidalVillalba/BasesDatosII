package baseDatos;

import modelo.ReservaAsistir;
import modelo.ReservaXantar;
import modelo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Interfaz de acceso a los datos relacionados con las reservas de espectáculos, encapsula la lógica de acceso a los datos para que
 * las capas superiores de la aplicación puedan interactuar con ellos de manera más sencilla y segura.
 * Extiende a la clase AbstractDAO
 */
public class ReservasAsistirDAO extends AbstractDAO{
    public ReservasAsistirDAO() {}
    public ReservasAsistirDAO(Connection conexion){
        super.setConexion(conexion);
    }

    /**
     * Método para obtener todas las reservas de espectáculos.
     * @return Lista con todas las reservas de espectáculos.
     * @throws SQLException
     */
    public List<ReservaAsistir> getAllReservas() {
        List<ReservaAsistir> resultado = new java.util.ArrayList<ReservaAsistir>();
        ReservaAsistir atraccionactual;
        Connection con;

        PreparedStatement stm = null;
        ResultSet rs;

        con = this.getConexion();

        String consulta = "SELECT * FROM asistir a ";

        try{

            stm = con.prepareStatement(consulta);
            rs = stm.executeQuery();

            while (rs.next()){
                atraccionactual = new ReservaAsistir(
                        rs.getString("visitante"),
                        rs.getString("espectaculo"),
                        rs.getTime("horaInicio"),
                        rs.getDate("fecha")
                );
                resultado.add(atraccionactual);
            }
        } catch (SQLException e){
            System.err.println(e.getMessage());
        }

        return resultado;
    }

    /**
     * Método para obtener todas las reservas de espectáculos de un usuario concreto.
     * @param user Usuario concreto
     * @return Lista con todas las reservas de espectáculos del usuario
     * @throws SQLException
     */
    public List<ReservaAsistir> getAllReservasDNI(User user) {
        List<ReservaAsistir> resultado = new java.util.ArrayList<ReservaAsistir>();
        ReservaAsistir atraccionactual;
        Connection con;

        PreparedStatement stm = null;
        ResultSet rs;

        con = this.getConexion();

        String consulta = "SELECT * FROM asistir a where visitante = ?";

        try{

            stm = con.prepareStatement(consulta);
            stm.setString(1, user.getDni());
            rs = stm.executeQuery();

            while (rs.next()){
                atraccionactual = new ReservaAsistir(
                        rs.getString("visitante"),
                        rs.getString("espectaculo"),
                        rs.getTime("horaInicio"),
                        rs.getDate("fecha")
                );
                resultado.add(atraccionactual);
            }
        } catch (SQLException e){
            System.err.println(e.getMessage());
        }

        return resultado;
    }

    /**
     * Método para añadir una reserva de espectáculo por un usuario registrado
     * @param reserva Reserva de espectáculo concreta
     * @throws SQLException
     */
    public void insertarReservaAsistir(ReservaAsistir reserva) throws SQLException{
        Connection con = this.getConexion();
        PreparedStatement stmLibro=null;
        stmLibro=con.prepareStatement("insert into asistir values (?,?,?,?)");
        stmLibro.setString(1, reserva.getNombre());
        stmLibro.setString(2, reserva.getEspectaculo());
        stmLibro.setTime(3, reserva.getHoraInicio());
        stmLibro.setDate(4,reserva.getFecha());
        stmLibro.executeUpdate();


    }

    public Connection getConnection() {
        return this.getConexion();
    }

    /**
     * Método para eliminar una reserva de espectáculo por un usuario registrado
     * @param reserva Reserva de espectáculo concreta
     * @throws SQLException
     */
    public void borrarReservaAsistir(ReservaAsistir reserva){
        Connection con;
        PreparedStatement stmLibro=null;

        con=super.getConexion();

        try {
            stmLibro=con.prepareStatement("delete from asistir where visitante = ? AND espectaculo = ? AND fecha = ? AND horaInicio = ?;");
            stmLibro.setString(1, reserva.getNombre());
            stmLibro.setString(2, reserva.getEspectaculo());
            stmLibro.setDate(3, reserva.getFecha());
            stmLibro.setTime(4, reserva.getHoraInicio());
            stmLibro.executeUpdate();

        } catch (SQLException e){
            System.out.println(e.getMessage());
        } finally {
            try {stmLibro.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
    }


}
