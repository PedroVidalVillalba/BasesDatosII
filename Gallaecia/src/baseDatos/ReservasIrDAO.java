package baseDatos;

import modelo.ReservaIrAtraccion;
import modelo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ReservasIrDAO extends AbstractDAO{
    public ReservasIrDAO() {}
    public ReservasIrDAO(Connection conexion){
        super.setConexion(conexion);
    }
    public List<ReservaIrAtraccion> getAllReservas() {
        List<ReservaIrAtraccion> resultado = new java.util.ArrayList<ReservaIrAtraccion>();
        ReservaIrAtraccion atraccionactual;
        Connection con;

        PreparedStatement stm = null;
        ResultSet rs;

        con = this.getConexion();

        String consulta = "SELECT * FROM ir a ";

        try{

            stm = con.prepareStatement(consulta);
            rs = stm.executeQuery();

            while (rs.next()){
                atraccionactual = new ReservaIrAtraccion(
                        rs.getString("visitante"),
                        rs.getString("atraccion"),
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

    public List<ReservaIrAtraccion> getAllReservasDNI(User user) throws SQLException {
        List<ReservaIrAtraccion> resultado = new java.util.ArrayList<ReservaIrAtraccion>();
        ReservaIrAtraccion atraccionactual;
        Connection con;

        PreparedStatement stm = null;
        ResultSet rs;

        con = this.getConexion();

        PreparedStatement stmRes=null;
        stmRes = con.prepareStatement("SELECT * FROM ir a where visitante = ?");
        stmRes.setString(1, user.getDni());


        try{

            rs = stmRes.executeQuery();

            while (rs.next()){
                atraccionactual = new ReservaIrAtraccion(
                        rs.getString("visitante"),
                        rs.getString("atraccion"),
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

    public void insertarReservaIr(ReservaIrAtraccion reserva) throws SQLException{
        Connection con = this.getConexion();
        PreparedStatement stmLibro=null;
        stmLibro=con.prepareStatement("insert into ir values (?,?,?,?)");
        stmLibro.setString(1, reserva.getNombre());
        stmLibro.setString(2, reserva.getAtraccion());
        stmLibro.setTime(3, reserva.getHoraInicio());
        stmLibro.setDate(4,reserva.getFecha());
        stmLibro.executeUpdate();


    }

    public Connection getConnection() {
        return this.getConexion();
    }

    public void borrarReservaIr(ReservaIrAtraccion reserva){
        Connection con;
        PreparedStatement stmLibro=null;

        con=super.getConexion();

        try {
            stmLibro=con.prepareStatement("delete from ir where visitante = ? AND atraccion = ? AND fecha = ? AND horaInicio = ?;");
            stmLibro.setString(1, reserva.getNombre());
            stmLibro.setString(2, reserva.getAtraccion());
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
