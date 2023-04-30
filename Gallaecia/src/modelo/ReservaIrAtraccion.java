package modelo;

import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

/**
 * Clase modelo del patrón Modelo-Vista-Controlador
 * Representa a la tabla Ir
 */
public class ReservaIrAtraccion {
    private String nombre;
    private String atraccion;
    private Time horaInicio;
    private Date fecha;

    public ReservaIrAtraccion(String nombre, String hostalaria, Time horaInicio, Date fecha) {
        this.nombre = nombre;
        this.atraccion = hostalaria;
        this.horaInicio = horaInicio;
        this.fecha=fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAtraccion() {
        return atraccion;
    }

    public void setAtraccion(String hostalaria) {
        this.atraccion = hostalaria;
    }

    public Time getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Time horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReservaIrAtraccion reserva = (ReservaIrAtraccion) o;
        return Objects.equals(nombre, reserva.nombre) && Objects.equals(atraccion, reserva.atraccion) && Objects.equals(horaInicio, reserva.horaInicio) && Objects.equals(fecha, reserva.fecha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, atraccion, horaInicio, fecha);
    }
}
