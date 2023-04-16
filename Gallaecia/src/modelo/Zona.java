package modelo;

import java.util.Objects;

public class Zona {
	private String nome;
	private Float extension;
	private Float coordenadaX;
	private Float coordenadaY;

	public Zona(String nome, Float extension, Float coordenadaX, Float coordenadaY) {
		if(nome!=null){
			this.nome = nome;
		}
		if(extension!=null){
			this.extension = extension;
		}
		if(coordenadaX!=null){
			this.coordenadaX = coordenadaX;
		}
		if(coordenadaY!=null){
			this.coordenadaY = coordenadaY;
		}
	}

	public String getNome() {
		return nome;
	}

	public Float getExtension() {
		return extension;
	}

	public Float getCoordenadaX() {
		return coordenadaX;
	}

	public Float getCoordenadaY() {
		return coordenadaY;
	}


	public void setNome(String nome) {
		if(nome!=null){
			this.nome = nome;
		}
	}

	public void setExtension(Float extension) {
		if(extension!=null){
			this.extension = extension;
		}
	}

	public void setCoordenadaX(Float coordenadaX) {
		if(coordenadaX!=null){
			this.coordenadaX = coordenadaX;
		}
	}

	public void setCoordenadaY(Float coordenadaY) {
		if(coordenadaY!=null){
			this.coordenadaY = coordenadaY;
		}
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Zona)) return false;
		Zona zona = (Zona) o;
		return nome.equals(zona.nome);
	}

	@Override
	public int hashCode() {
		return Objects.hash(nome);
	}
}
