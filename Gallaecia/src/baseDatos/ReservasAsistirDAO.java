package baseDatos;

import modelo.ReservaAsistir;
import modelo.ReservaXantar;
import modelo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ReservasAsistirDAO extends AbstractDAO{
    public ReservasAsistirDAO() {}
    public ReservasAsistirDAO(Connection conexion){
        super.setConexion(conexion);
    }
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
