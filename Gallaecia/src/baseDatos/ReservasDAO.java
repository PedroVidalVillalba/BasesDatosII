package baseDatos;

import modelo.Hostalaria;
import modelo.Reserva;
import modelo.Zona;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ReservasDAO extends AbstractDAO{
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

        String consulta = "SELECT a.nombrePersona, a.hostalaria, a.horaInicio, a.horaFin, a.fecha"+
                "FROM reservashostalaria a ";

        try{

            stm = con.prepareStatement(consulta);
            rs = stm.executeQuery();

            while (rs.next()){
                atraccionactual = new Reserva(
                        rs.getString("nombrePersona"),
                        rs.getString("hostalaria"),
                        rs.getString("horaInicio"),
                        rs.getString("horaFin"),
                        rs.getDate("fecha")
                );
                resultado.add(atraccionactual);
            }
        } catch (SQLException e){
            System.err.println(e.getMessage());
        }

        return resultado;
    }
}
