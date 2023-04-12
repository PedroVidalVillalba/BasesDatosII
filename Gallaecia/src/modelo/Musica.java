package modelo;

import java.util.Objects;

public class Musica {
    private String codigoCancion;
    private String nome;
    private String clasificacion;
    private Integer popularidad;
    private String artista;
    private String album;

    public Musica(String codigoCancion, String nome, String clasificacion, Integer popularidad, String artista, String album) {
        if(codigoCancion!=null){
            this.codigoCancion = codigoCancion;
        }
        if(nome!=null){
            this.nome = nome;
        }
        if(clasificacion!=null){
            this.clasificacion = clasificacion;
        }
        if(popularidad!=null){
            this.popularidad = popularidad;
        }
        if(artista!=null){
            this.artista = artista;
        }
        if(album!=null){
            this.album = album;
        }
    }

    public String getCodigoCancion() {
        return codigoCancion;
    }

    public String getNome() {
        return nome;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public Integer getPopularidad() {
        return popularidad;
    }

    public String getArtista() {
        return artista;
    }

    public String getAlbum() {
        return album;
    }

    public void setCodigoCancion(String codigoCancion) {
        this.codigoCancion = codigoCancion;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public void setPopularidad(Integer popularidad) {
        this.popularidad = popularidad;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Musica)) return false;
        Musica musica = (Musica) o;
        return codigoCancion.equals(musica.codigoCancion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigoCancion);
    }
}
