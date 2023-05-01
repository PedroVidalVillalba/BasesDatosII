package modelo;

import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

public class Ir {

    private String visitante;
    private String atraccion;
    private Time horaInicio;
    private Date fecha;

    public Ir(String visitante, String atraccion, Time horaInicio, Date fecha) {
        if (visitante != null){
                this.visitante = visitante;
        }
        if(atraccion != null){
            this.atraccion=atraccion;
        }
        if(horaInicio != null){
            this.horaInicio=horaInicio;
        }
        if(fecha != null){
            this.fecha=fecha;
        }
    }

    public String getVisitante() {
        return visitante;
    }

    public String getAtraccion() {
        return atraccion;
    }

    public Time getHoraInicio() {
        return horaInicio;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setVisitante(String visitante) {
        if(visitante != null){
            this.visitante = visitante;
        }

    }

    public void setAtraccion(String atraccion) {
        if(atraccion != null){
            this.atraccion = atraccion;
        }

    }

    public void setHoraInicio(Time horaInicio) {
        if(horaInicio != null){
            this.horaInicio = horaInicio;
        }

    }

    public void setFecha(Date fecha) {
        if(fecha != null){
            this.fecha = fecha;
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ir ir = (Ir) o;
        return Objects.equals(visitante, ir.visitante) && Objects.equals(atraccion, ir.atraccion) && Objects.equals(horaInicio, ir.horaInicio) && Objects.equals(fecha, ir.fecha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(visitante, atraccion, horaInicio, fecha);
    }
}
