package modelo;

import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

public class Asistir {
    private String visitante;
    private String espectaculo;
    private Time horaInicio;
    private Date fecha;

    public Asistir(String visitante, String espectaculo, Time horaInicio, Date fecha){
        if(visitante != null){
            this.visitante=visitante;
        }
        if(espectaculo != null){
            this.espectaculo=espectaculo;
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

    public String getEspectaculo() {
        return espectaculo;
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

    public void setEspectaculo(String espectaculo) {
        if(espectaculo != null){
            this.espectaculo = espectaculo;
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

    public String toStringListView(){
        return this.getVisitante() + " - " + this.getEspectaculo();
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Asistir)) return false;
        Asistir asistir = (Asistir) o;
        return (visitante.equals(asistir.visitante) && espectaculo.equals(asistir.espectaculo));
    }

    @Override
    public int hashCode() {
        return Objects.hash(visitante);
    }
}
