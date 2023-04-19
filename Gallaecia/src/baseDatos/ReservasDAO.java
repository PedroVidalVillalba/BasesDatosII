package baseDatos;

import javafx.collections.ObservableList;
import modelo.Hostalaria;
import modelo.Reserva;
import modelo.Zona;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ReservasDAO extends AbstractDAO{
    public ReservasDAO() {}
    public ReservasDAO (Connection conexion){
        super.setConexion(conexion);
    }
    public List<Reserva> getAllReservas() {
        java.util.List<Reserva> resultado = new java.util.ArrayList<Reserva>();
        Reserva atraccionactual;
        Connection con;

        PreparedStatement stm = null;
        ResultSet rs;

        con = this.getConexion();

        String consulta = "SELECT * FROM reservashostalaria a ";

        try{

            stm = con.prepareStatement(consulta);
            rs = stm.executeQuery();

            while (rs.next()){
                atraccionactual = new Reserva(
                        rs.getString("nombrePersona"),
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

    public Connection getConnection() {
        return this.getConexion();
    }

    public void borrarReserva(Reserva reserva){
        Connection con;
        PreparedStatement stmLibro=null;

        con=super.getConexion();

        try {
            stmLibro=con.prepareStatement("delete from reservashostalaria where nombrePersona = ? AND hostalaria = ? AND fecha = ? AND horaInicio = ?;");
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
