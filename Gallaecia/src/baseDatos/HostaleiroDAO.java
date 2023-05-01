package baseDatos;

import modelo.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HostaleiroDAO extends AbstractDAO{

    public HostaleiroDAO() {}
    public HostaleiroDAO (Connection conexion){
        super.setConexion(conexion);
    }

    /**
     * Método para obtener todas los hosteleros.
     * @return Lista con todos los hosteleros.
     * @throws SQLException
     */
    public java.util.List<Hostaleiro> getAllHostaleiros(){

        java.util.List<Hostaleiro> resultado = new java.util.ArrayList<>();
        Hostaleiro hostaleiroactual;
        Connection con;

        PreparedStatement stm = null;
        ResultSet rs;

        con = this.getConexion();

        String consulta = "SELECT dni, nome, rua, numero, cp, localidade, salario, telefono, datainicio, datanacemento, formacion, nomeestablecemento " +
                "FROM hostaleiros;";

        try{

            stm = con.prepareStatement(consulta);
            rs = stm.executeQuery();

            while (rs.next()){
                hostaleiroactual = new Hostaleiro(
                        rs.getString("dni"),
                        rs.getString("nome"),
                        rs.getString("rua"),
                        rs.getInt("numero"),
                        rs.getInt("cp"),
                        rs.getString("localidade"),
                        rs.getFloat("salario"),
                        rs.getString("telefono"),
                        rs.getDate("datainicio").toLocalDate(),
                        rs.getDate("datanacemento").toLocalDate(),
                        rs.getString("formacion"),
                        null
                );
                resultado.add(hostaleiroactual);
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return resultado;

    }

    /**
     * Método para eliminar un hostelero por un administrador
     * @param hostaleiro Hostaleiro a eliminar
     * @throws SQLException
     */
    public void borrarHostaleiro(Hostaleiro hostaleiro){
        Connection con = this.getConexion();
        PreparedStatement stm = null;
        String consulta = "DELETE FROM hostaleiros WHERE dni = ?;";

        try {
            stm = con.prepareStatement(consulta);
            stm.setString(1, hostaleiro.getDni());
            stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
