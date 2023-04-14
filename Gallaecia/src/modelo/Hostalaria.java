package modelo;

import java.time.LocalTime;
import java.util.Objects;

public class Hostalaria {

    private String nomeEstablecemento;
    private Integer aforo;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private Zona zona;

    public Hostalaria(String nomeEstablecemento, Integer aforo, LocalTime horaInicio, LocalTime horaFin, Zona zona) {
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

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public LocalTime getHoraFin() {
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

    public void setHoraInicio(LocalTime horaInicio) {
        if(horaInicio!=null){
            this.horaInicio = horaInicio;
        }
    }

    public void setHoraFin(LocalTime horaFin) {
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
