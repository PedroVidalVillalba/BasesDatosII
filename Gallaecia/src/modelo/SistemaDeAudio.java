package modelo;

import java.util.Objects;

/**
 * Clase modelo del patrón Modelo-Vista-Controlador
 * Representa a la tabla SistemasDeAudio
 */
public class SistemaDeAudio {
	private String identificador;
	private String funcion;
	private String descricion;
	private Zona zona;

	public SistemaDeAudio(String identificador, String funcion, String descricion, Zona zona) {
		if(identificador!=null){
			this.identificador = identificador;
		}
		if(funcion!=null){
			this.funcion = funcion;
		}
		if(descricion!=null){
			this.descricion = descricion;
		}
		if(zona!=null){
			this.zona = zona;
		}
	}

	public String getIdentificador() {
		return identificador;
	}

	public String getFuncion() {
		return funcion;
	}

	public String getDescricion() {
		return descricion;
	}

	public Zona getZona() {
		return zona;
	}

	public void setIdentificador(String identificador) {
		if(identificador!=null){
			this.identificador = identificador;
		}
	}

	public void setFuncion(String funcion) {
		if(funcion!=null){
			this.funcion = funcion;
		}
	}

	public void setDescricion(String descricion) {
		if(descricion!=null){
			this.descricion = descricion;
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
		if (!(o instanceof SistemaDeAudio)) return false;
		SistemaDeAudio that = (SistemaDeAudio) o;
		return identificador.equals(that.identificador);
	}

	@Override
	public int hashCode() {
		return Objects.hash(identificador);
	}
}
