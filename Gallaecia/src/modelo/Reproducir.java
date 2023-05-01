package modelo;

import java.util.Date;
import java.util.Objects;

public class Reproducir {
    private Date data;
    private String musica;
    private String sistema;

    public Reproducir(Date data, String musica, String sistema) {
        if (data != null){
            this.data = data;
        }
        if (musica != null) {
            this.musica = musica;
        }
        if (sistema != null){
            this.sistema = sistema;
        }
    }

    public Date getData() {
        return data;
    }

    public String getMusica() {
        return musica;
    }

    public String getSistema() {
        return sistema;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public void setMusica(String musica) {
        this.musica = musica;
    }

    public void setSistema(String sistema) {
        this.sistema = sistema;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reproducir that = (Reproducir) o;
        return Objects.equals(data, that.data) && Objects.equals(musica, that.musica) && Objects.equals(sistema, that.sistema);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data, musica, sistema);
    }
}
