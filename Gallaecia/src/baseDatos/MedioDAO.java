package baseDatos;

import modelo.Medio;
import modelo.Zona;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Interfaz de acceso a los datos relacionados con los medios de acceso al parque, encapsula la lógica de acceso a los datos para que
 * las capas superiores de la aplicación puedan interactuar con ellos de manera más sencilla y segura.
 * Extiende a la clase AbstractDAO
 */
public class MedioDAO extends AbstractDAO {
    public MedioDAO(){}
    public MedioDAO (Connection conexion){
        super.setConexion(conexion);
    }

    /**
     * Método para obtener todos los medios.
     * @return Lista con todos los medios.
     * @throws SQLException
     */
    public java.util.List<Medio> getAllMedios(){

        java.util.List<Medio> resultado = new java.util.ArrayList<>();
        Medio medioactual;
        Connection con;

        PreparedStatement stm = null;
        ResultSet rs;

        con = this.getConexion();

        String consulta = "SELECT *" +
                "FROM medios;";
        try{

            stm = con.prepareStatement(consulta);
            rs = stm.executeQuery();

            while (rs.next()){
                medioactual = new Medio(
                        rs.getString("nomeMedio"),
                        rs.getString("tipo"),
                        rs.getFloat("prezo"),
                        rs.getInt("capacidade"),
                        rs.getFloat("velocidade")
                );
                resultado.add(medioactual);
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return resultado;

    }

    /**
     * Método para eliminar un medio por un administrador
     * @param medio Medio a eliminar
     * @throws SQLException
     */
    public void borrarMedio(Medio medio){
        Connection con = this.getConexion();
        PreparedStatement stm = null;
        String consulta = "DELETE FROM medios WHERE nomeMedio = ?;";

        try {
            stm = con.prepareStatement(consulta);
            stm.setString(1, medio.getNomeMedio());
            stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
