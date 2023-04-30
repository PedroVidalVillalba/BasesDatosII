package baseDatos;

import modelo.ReservaXantar;
import modelo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Interfaz de acceso a los datos relacionados con las reservas de restaurantes, encapsula la lógica de acceso a los datos para que
 * las capas superiores de la aplicación puedan interactuar con ellos de manera más sencilla y segura.
 * Extiende a la clase AbstractDAO
 */
public class ReservasXantarDAO extends AbstractDAO{
    public ReservasXantarDAO() {}
    public ReservasXantarDAO(Connection conexion){
        super.setConexion(conexion);
    }

    /**
     * Método para obtener todas las reservas de restaurantes.
     * @return Lista con todas las reservas de restaurantes.
     * @throws SQLException
     */
    public List<ReservaXantar> getAllReservas() {
        java.util.List<ReservaXantar> resultado = new java.util.ArrayList<ReservaXantar>();
        ReservaXantar atraccionactual;
        Connection con;

        PreparedStatement stm = null;
        ResultSet rs;

        con = this.getConexion();

        String consulta = "SELECT * FROM xantar a ";

        try{

            stm = con.prepareStatement(consulta);
            rs = stm.executeQuery();

            while (rs.next()){
                atraccionactual = new ReservaXantar(
                        rs.getString("visitante"),
                        rs.getString("hostalaria"),
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
     * Método para obtener todas las reservas de restaurantes de un usuario concreto.
     * @param user Usuario concreto
     * @return Lista con todas las reservas de restaurantes del usuario
     * @throws SQLException
     */
    public List<ReservaXantar> getAllReservasDNI(User user) throws SQLException {
        java.util.List<ReservaXantar> resultado = new java.util.ArrayList<ReservaXantar>();
        ReservaXantar atraccionactual;
        Connection con;

        PreparedStatement stm = null;
        ResultSet rs;

        con = this.getConexion();

        PreparedStatement stmRes=null;
        stmRes=con.prepareStatement("SELECT * FROM xantar a WHERE visitante=?");
        stmRes.setString(1, user.getDni());

        try{

            rs = stmRes.executeQuery();

            while (rs.next()){
                atraccionactual = new ReservaXantar(
                        rs.getString("visitante"),
                        rs.getString("hostalaria"),
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
     * Método para añadir una reserva de restaurante por un usuario registrado
     * @param reserva Reserva de restaurante concreta
     * @throws SQLException
     */
    public void insertarReservaXantar(ReservaXantar reserva) throws SQLException{
        Connection con = this.getConexion();
        PreparedStatement stmLibro=null;
        stmLibro=con.prepareStatement("insert into xantar values (?,?,?,?)");
        stmLibro.setString(1, reserva.getNombre());
        stmLibro.setString(2, reserva.getHostalaria());
        stmLibro.setTime(3, reserva.getHoraInicio());
        stmLibro.setDate(4,reserva.getFecha());
        stmLibro.executeUpdate();


    }

    public Connection getConnection() {
        return this.getConexion();
    }

    /**
     * Método para eliminar una reserva de restaurante por un usuario registrado
     * @param reserva Reserva de restaurante concreta
     * @throws SQLException
     */
    public void borrarReservaXantar(ReservaXantar reserva){
        Connection con;
        PreparedStatement stmLibro=null;

        con=super.getConexion();

        try {
            stmLibro=con.prepareStatement("delete from xantar where visitante = ? AND hostalaria = ? AND fecha = ? AND horaInicio = ?;");
            stmLibro.setString(1, reserva.getNombre());
            stmLibro.setString(2, reserva.getHostalaria());
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
