package modelo;

import java.time.LocalTime;
import java.util.Objects;

public class Espectaculo {
    private String nome;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private String tematica;
    private String descricion;
    private String ubicacion;
    private Zona zona;

    public Espectaculo(String nome, LocalTime horaInicio, LocalTime horaFin, String tematica, String descricion, String ubicacion, Zona zona) {
        if(nome!=null){
            this.nome = nome;
        }
        if(horaInicio!=null){
            this.horaInicio = horaInicio;
        }
        if(horaFin!=null){
            this.horaFin = horaFin;
        }
        if(tematica!=null){
            this.tematica = tematica;
        }
        if(descricion!=null){
            this.descricion = descricion;
        }
        if(ubicacion!=null){
            this.ubicacion = ubicacion;
        }
        if(zona!=null){
            this.zona = zona;
        }
    }

    public String getNome() {
        return nome;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }

    public String getTematica() {
        return tematica;
    }

    public String getDescricion() {
        return descricion;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public Zona getZona() {
        return zona;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public void setHoraFin(LocalTime horaFin) {
        this.horaFin = horaFin;
    }

    public void setTematica(String tematica) {
        this.tematica = tematica;
    }

    public void setDescricion(String descricion) {
        this.descricion = descricion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public void setZona(Zona zona) {
        this.zona = zona;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Espectaculo)) return false;
        Espectaculo that = (Espectaculo) o;
        return nome.equals(that.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }
}
