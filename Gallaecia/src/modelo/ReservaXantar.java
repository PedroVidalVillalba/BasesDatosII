package modelo;

import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

/**
 * Clase modelo del patrón Modelo-Vista-Controlador
 * Representa a la tabla Xantar
 */
public class ReservaXantar {
    private String nombre;
    private String hostalaria;
    private Time horaInicio;
    private Date fecha;

    public ReservaXantar(String nombre, String hostalaria, Time horaInicio, Date fecha) {
        this.nombre = nombre;
        this.hostalaria = hostalaria;
        this.horaInicio = horaInicio;
        this.fecha=fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getHostalaria() {
        return hostalaria;
    }

    public void setHostalaria(String hostalaria) {
        this.hostalaria = hostalaria;
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
        ReservaXantar reserva = (ReservaXantar) o;
        return Objects.equals(nombre, reserva.nombre) && Objects.equals(hostalaria, reserva.hostalaria) && Objects.equals(horaInicio, reserva.horaInicio) && Objects.equals(fecha, reserva.fecha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, hostalaria, horaInicio, fecha);
    }
}
