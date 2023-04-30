package baseDatos;

import modelo.AtraccionFamiliar;
import modelo.AtraccionSoAdultos;
import modelo.Zona;
import modelo.Atraccion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Interfaz de acceso a los datos relacionados con las atracciones, encapsula la lógica de acceso a los datos para que
 * las capas superiores de la aplicación puedan interactuar con ellos de manera más sencilla y segura.
 */
public class AtraccionSoAdultosDAO extends AbstractDAO{
    public AtraccionSoAdultosDAO() {}
    public AtraccionSoAdultosDAO (Connection conexion){
        super.setConexion(conexion);
    }

    /**
     * Método para obtener todas las atracciones para adultos.
     * @return
     * @throws SQLException
     */
    public java.util.List<AtraccionSoAdultos> getAllAtraccionSoAdultos(){

        java.util.List<AtraccionSoAdultos> resultado = new java.util.ArrayList<>();
        AtraccionSoAdultos atraccionactual;
        Connection con;

        PreparedStatement stm = null;
        ResultSet rs;

        con = this.getConexion();

        String consulta = "SELECT * " +
                "FROM atraccionssoadultos a;";

        try{

            stm = con.prepareStatement(consulta);
            rs = stm.executeQuery();

            while (rs.next()){
                atraccionactual = new AtraccionSoAdultos(
                        rs.getString("nome"),
                        rs.getInt("idademin")
                );
                resultado.add(atraccionactual);
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return resultado;

    }



    public void borrarAtraccionSoAdultos(AtraccionSoAdultos atraccion) throws SQLException {

        Connection con = this.getConexion();
        PreparedStatement stm = null;
        String consulta = "DELETE FROM atraccionssoadultos WHERE nome = ?;";

        try {

            stm = con.prepareStatement(consulta);
            stm.setString(1, atraccion.getAtraccion());
            stm.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}