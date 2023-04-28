package modelo;

import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

public class ReservaAsistir {
    private String nombre;
    private String espectaculo;
    private Time horaInicio;
    private Date fecha;

    public ReservaAsistir(String nombre, String espectaculo, Time horaInicio, Date fecha) {
        this.nombre = nombre;
        this.espectaculo = espectaculo;
        this.horaInicio = horaInicio;
        this.fecha=fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspectaculo() {
        return espectaculo;
    }

    public void setEspectaculo(String espectaculo) {
        this.espectaculo = espectaculo;
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
        ReservaAsistir reserva = (ReservaAsistir) o;
        return Objects.equals(nombre, reserva.nombre) && Objects.equals(espectaculo, reserva.espectaculo) && Objects.equals(horaInicio, reserva.horaInicio) && Objects.equals(fecha, reserva.fecha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, espectaculo, horaInicio, fecha);
    }
}
