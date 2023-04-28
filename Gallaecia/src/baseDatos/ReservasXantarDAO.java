package baseDatos;

import modelo.ReservaXantar;
import modelo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ReservasXantarDAO extends AbstractDAO{
    public ReservasXantarDAO() {}
    public ReservasXantarDAO(Connection conexion){
        super.setConexion(conexion);
    }
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
