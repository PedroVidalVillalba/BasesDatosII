package baseDatos;

import modelo.AtraccionFamiliar;
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
public class AtraccionsFamiliaresDAO extends AbstractDAO{
    public AtraccionsFamiliaresDAO() {}
    public AtraccionsFamiliaresDAO (Connection conexion){
        super.setConexion(conexion);
    }

    /**
     * Método para obtener todas las atracciones familiares.
     * @return
     * @throws SQLException
     */
    public java.util.List<AtraccionFamiliar> getAllAtraccionsFamiliares(){

        java.util.List<AtraccionFamiliar> resultado = new java.util.ArrayList<>();
        AtraccionFamiliar atraccionactual;
        Connection con;

        PreparedStatement stm = null;
        ResultSet rs;

        con = this.getConexion();

        String consulta = "SELECT * " +
                "FROM atraccionsfamiliares a;";

        try{

            stm = con.prepareStatement(consulta);
            rs = stm.executeQuery();

            while (rs.next()){
                atraccionactual = new AtraccionFamiliar(
                        rs.getString("nome"),
                        rs.getInt("idaderecomendada")
                );
                resultado.add(atraccionactual);
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return resultado;

    }



    public void borrarAtraccionFamiliar(AtraccionFamiliar atraccion) throws SQLException {

        Connection con = this.getConexion();
        PreparedStatement stm = null;
        String consulta = "DELETE FROM atraccionsfamiliares WHERE nome = ?;";

        try {

            stm = con.prepareStatement(consulta);
            stm.setString(1, atraccion.getAtraccion());
            stm.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}