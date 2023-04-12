package modelo;

import java.util.Objects;

public class Medio {
    private String nomeMedio;
    private String tipo;
    private Float prezo;
    private Integer capacidade;
    private Float velocidade;

    public Medio(String nomeMedio, String tipo, Float prezo, Integer capacidade, Float velocidade) {
        if(nomeMedio!=null){
            this.nomeMedio = nomeMedio;
        }
        if(tipo!=null){
            this.tipo = tipo;
        }
        if(prezo!=null){
            this.prezo = prezo;
        }
        if(capacidade!=null){
            this.capacidade = capacidade;
        }
        if(velocidade!=null){
            this.velocidade = velocidade;
        }
    }

    public String getNomeMedio() {
        return nomeMedio;
    }

    public String getTipo() {
        return tipo;
    }

    public Float getPrezo() {
        return prezo;
    }

    public Integer getCapacidade() {
        return capacidade;
    }

    public Float getVelocidade() {
        return velocidade;
    }

    public void setNomeMedio(String nomeMedio) {
        this.nomeMedio = nomeMedio;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setPrezo(Float prezo) {
        this.prezo = prezo;
    }

    public void setCapacidade(Integer capacidade) {
        this.capacidade = capacidade;
    }

    public void setVelocidade(Float velocidade) {
        this.velocidade = velocidade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Medio)) return false;
        Medio medio = (Medio) o;
        return nomeMedio.equals(medio.nomeMedio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomeMedio);
    }
}
