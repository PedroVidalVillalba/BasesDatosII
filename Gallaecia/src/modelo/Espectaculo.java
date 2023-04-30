package modelo;

import java.sql.Time;
import java.time.LocalTime;
import java.util.Objects;

/**
 * Clase modelo del patrón Modelo-Vista-Controlador
 * Representa a la tabla Espectaculos
 */
public class Espectaculo {
	private String nome;
	private Time horaInicio;
	private Time horaFin;
	private String tematica;
	private String descricion;
	private Zona zona;

	public Espectaculo(String nome, Time horaInicio, Time horaFin, String tematica, String descricion, Zona zona) {
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
		if(zona!=null){
			this.zona = zona;
		}
	}

	public String getNome() {
		return nome;
	}

	public Time getHoraInicio() {
		return horaInicio;
	}

	public Time getHoraFin() {
		return horaFin;
	}

	public String getTematica() {
		return tematica;
	}

	public String getDescricion() {
		return descricion;
	}

	public Zona getZona() {
		return zona;
	}

	public void setNome(String nome) {
		if(nome!=null){
			this.nome = nome;
		}
	}

	public void setHoraInicio(Time horaInicio) {
		if(horaInicio!=null){
			this.horaInicio = horaInicio;
		}
	}

	public void setHoraFin(Time horaFin) {
		if(horaFin!=null){
			this.horaFin = horaFin;
		}
	}

	public void setTematica(String tematica) {
		if(tematica!=null){
			this.tematica = tematica;
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
		if (!(o instanceof Espectaculo)) return false;
		Espectaculo that = (Espectaculo) o;
		return nome.equals(that.nome);
	}

	@Override
	public int hashCode() {
		return Objects.hash(nome);
	}
}
