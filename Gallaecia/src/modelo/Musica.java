package modelo;

import java.util.Objects;

/**
 * Clase modelo del patrón Modelo-Vista-Controlador
 * Representa a la tabla Musica
 */
public class Musica {
	private String codigoCancion;
	private String nome;
	private String clasificacion;
	private Integer popularidade;
	private String artista;
	private String album;

	public Musica(String codigoCancion, String nome, String clasificacion, Integer popularidade, String artista, String album) {
		if(codigoCancion!=null){
			this.codigoCancion = codigoCancion;
		}
		if(nome!=null){
			this.nome = nome;
		}
		if(clasificacion!=null){
			this.clasificacion = clasificacion;
		}
		if(popularidade!=null){
			this.popularidade = popularidade;
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

	public Integer getPopularidade() {
		return popularidade;
	}

	public String getArtista() {
		return artista;
	}

	public String getAlbum() {
		return album;
	}

	public void setCodigoCancion(String codigoCancion) {
		if(codigoCancion!=null){
			this.codigoCancion = codigoCancion;
		}
	}

	public void setNome(String nome) {
		if(nome!=null){
			this.nome = nome;
		}
	}

	public void setClasificacion(String clasificacion) {
		if(clasificacion!=null){
			this.clasificacion = clasificacion;
		}
	}

	public void setPopularidade(Integer popularidade) {
		if(popularidade!=null){
			this.popularidade = popularidade;
		}
	}

	public void setArtista(String artista) {
		if(artista!=null){
			this.artista = artista;
		}
	}

	public void setAlbum(String album) {
		if(album!=null){
			this.album = album;
		}
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
