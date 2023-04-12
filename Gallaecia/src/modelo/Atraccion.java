package modelo;

import java.util.Objects;

public class Atraccion {
    private String nome;
    private Integer aforo;
    private Integer alturaMin;
    private Float custoMantemento;
    private String descricion;
    private Zona zona;

    public Atraccion(String nome, Integer aforo, Integer alturaMin, Float custoMantemento, String descricion, Zona zona) {
        if(nome!=null){
            this.nome = nome;
        }
        if(aforo!=null){
            this.aforo = aforo;
        }
        if(alturaMin!=null){
            this.alturaMin = alturaMin;
        }
        if(custoMantemento!=null){
            this.custoMantemento = custoMantemento;
        }
        if(descricion!=null){
            this.descricion = descricion;
        }
        if(zona!=null){
            this.zona = zona;
        }
    }

    public String getNome() {
        return nome;
    }

    public Integer getAforo() {
        return aforo;
    }

    public Integer getAlturaMin() {
        return alturaMin;
    }

    public Float getCustoMantemento() {
        return custoMantemento;
    }

    public String getDescricion() {
        return descricion;
    }

    public Zona getZona() {
        return zona;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setAforo(Integer aforo) {
        this.aforo = aforo;
    }

    public void setAlturaMin(Integer alturaMin) {
        this.alturaMin = alturaMin;
    }

    public void setCustoMantemento(Float custoMantemento) {
        this.custoMantemento = custoMantemento;
    }

    public void setDescricion(String descricion) {
        this.descricion = descricion;
    }

    public void setZona(Zona zona) {
        this.zona = zona;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Atraccion)) return false;
        Atraccion atraccion = (Atraccion) o;
        return nome.equals(atraccion.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }
}
