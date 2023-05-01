package baseDatos;

import modelo.TraballadorParque;
import modelo.Zona;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 * Interfaz de acceso a los datos relacionados con los trabajadores del parque, encapsula la lógica de acceso a los datos para que
 * las capas superiores de la aplicación puedan interactuar con ellos de manera más sencilla y segura.
 * Extiende a la clase AbstractDAO
 */
public class TraballadorParqueDAO extends AbstractDAO {
    public TraballadorParqueDAO(){}
    public TraballadorParqueDAO (Connection conexion){
        super.setConexion(conexion);
    }

    /**
     * Método para obtener todos los trabajadores del parque.
     * @return Lista con todos los trbajadores del parque.
     * @throws SQLException
     */
    public java.util.List<TraballadorParque> getAllWorkers(){

        java.util.List<TraballadorParque> resultado = new java.util.ArrayList<>();
        TraballadorParque trabajadoractual;
        Connection con;

        PreparedStatement stm = null;
        ResultSet rs;

        con = this.getConexion();

/*        String consulta = "SELECT t.dni, t.nome, t.rua, t.numero, t.cp t.localidade, " +
                "t.salario, t.telefono, t.datainicio, t.datanacemento, t.formacion " +
                "FROM traballadoresParque t;";*/
        String consulta = "SELECT * " +
                "FROM traballadoresparque;";

        try{

            stm = con.prepareStatement(consulta);
            rs = stm.executeQuery();

            while (rs.next()){
                trabajadoractual = new TraballadorParque(
                        rs.getString("dni"),
                        rs.getString("nome"),
                        rs.getString("rua"),
                        rs.getInt("numero"),
                        rs.getInt("cp"),
                        rs.getString("localidade"),
                        rs.getFloat("salario"),
                        rs.getString("telefono"),
                        rs.getDate("dataInicio").toLocalDate(),
                        rs.getDate("dataNacemento").toLocalDate(),
                        rs.getString("formacion"),
                        null, //Para no complicarnos y tener que añadir todos los datos de la atracción
                        null           //o del espectáculo en la consulta sql
                );
                resultado.add(trabajadoractual);
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return resultado;

    }

    /**
     * Método para eliminar un trabajador por un administrador
     * @param traballadorParque Trabajador a eliminar
     * @throws SQLException
     */
    public void borrarTrabajador(TraballadorParque traballadorParque){
        Connection con = this.getConexion();
        PreparedStatement stm = null;
        String consulta = "DELETE FROM traballadoresParque WHERE dni = ?;";

        try {
            stm = con.prepareStatement(consulta);
            stm.setString(1, traballadorParque.getDni());
            stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
