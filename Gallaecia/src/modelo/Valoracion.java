package modelo;

import java.util.Objects;
import java.sql.Date;

/**
 * Clase modelo del patrón Modelo-Vista-Controlador
 * Representa a la tabla Valoracions
 */
public class Valoracion {
    private String identificador;
    private Date data;
    private String descricion;
    private Integer puntuacion;
    private Visitante visitante;

    public Valoracion(String identificador, Date data, String descricion, Integer puntuacion, Visitante visitante) {
        if(identificador!=null){
            this.identificador = identificador;
        }
        if(data!=null){
            this.data=data;
        }
        if(descricion!=null){
            this.descricion = descricion;
        }
        if(puntuacion!=null){
            this.puntuacion = puntuacion;
        }
        if(visitante!=null){
            this.visitante = visitante;
        }
    }

    public String getIdentificador() {
        return identificador;
    }

    public Date getData(){
        return data;
    }

    public String getDescricion() {
        return descricion;
    }

    public Integer getPuntuacion() {
        return puntuacion;
    }

    public Visitante getVisitante() {
        return visitante;
    }

    public void setIdentificador(String identificador) {
        if(identificador!=null){
            this.identificador = identificador;
        }
    }

    public void setData(Date data) {
        if(data!=null){
            this.data=data;
        }
    }

    public void setDescricion(String descricion) {
        if(descricion!=null){
            this.descricion = descricion;
        }
    }

    public void setPuntuacion(Integer puntuacion) {
        if(puntuacion!=null){
            this.puntuacion = puntuacion;
        }
    }

    public void setVisitante(Visitante visitante) {
        if(visitante!=null){
            this.visitante = visitante;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Valoracion)) return false;
        Valoracion that = (Valoracion) o;
        return identificador.equals(that.identificador);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identificador);
    }
}
