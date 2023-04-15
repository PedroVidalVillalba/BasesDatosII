package modelo;

import java.sql.Time;
import java.time.LocalTime;
import java.util.Objects;

public class Hostalaria {

    private String nomeEstablecemento;
    private Integer aforo;
    private Time horaInicio;
    private Time horaFin;
    private Zona zona;

    public Hostalaria(String nomeEstablecemento, Integer aforo, Time horaInicio, Time horaFin, Zona zona) {
        if(nomeEstablecemento!=null){
            this.nomeEstablecemento = nomeEstablecemento;
        }
        if(aforo!=null){
            this.aforo = aforo;
        }
        if(horaInicio!=null){
            this.horaInicio = horaInicio;
        }
        if(horaFin!=null){
            this.horaFin = horaFin;
        }
        if(zona!=null){
            this.zona = zona;
        }
    }

    public String getNomeEstablecemento() {
        return nomeEstablecemento;
    }

    public Integer getAforo() {
        return aforo;
    }

    public Time getHoraInicio() {
        return horaInicio;
    }

    public Time getHoraFin() {
        return horaFin;
    }

    public Zona getZona() {
        return zona;
    }

    public void setNomeEstablecemento(String nomeEstablecemento) {
        if(nomeEstablecemento!=null){
            this.nomeEstablecemento = nomeEstablecemento;
        }
    }

    public void setAforo(Integer aforo) {
        if(aforo!=null){
            this.aforo = aforo;
        }
    }

    public void setHoraInicio(Time horaInicio) {
        if(horaInicio!=null){
            this.horaInicio = horaInicio;
        }
    }

    public void setHoraFin(Time horaFin) {
        if(horaFin!=null){
            this.horaFin = horaFin;
        }
    }

    public void setZona(Zona zona) {
        if(zona!=null){
            this.zona = zona;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hostalaria)) return false;
        Hostalaria that = (Hostalaria) o;
        return nomeEstablecemento.equals(that.nomeEstablecemento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomeEstablecemento);
    }
}
