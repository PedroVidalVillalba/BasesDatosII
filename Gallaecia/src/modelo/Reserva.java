package modelo;

import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

public class Reserva {
    private String nombre;
    private String hostalaria;
    private String horaInicio;
    private String horaFin;
    private Date fecha;

    public Reserva(String nombre, String hostalaria, String horaInicio, String horaFin, Date fecha) {
        this.nombre = nombre;
        this.hostalaria = hostalaria;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
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

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
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
        Reserva reserva = (Reserva) o;
        return Objects.equals(nombre, reserva.nombre) && Objects.equals(hostalaria, reserva.hostalaria) && Objects.equals(horaInicio, reserva.horaInicio) && Objects.equals(fecha, reserva.fecha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, hostalaria, horaInicio, fecha);
    }
}
