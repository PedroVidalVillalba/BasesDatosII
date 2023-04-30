package modelo;

import java.sql.Date;
import java.util.Objects;

/**
 * Clase modelo del patrón Modelo-Vista-Controlador
 * Representa a la tabla Visitantes
 */
public class Visitante {
	private String dni;
	private String nome;
	private String nacionalidade;
	private String telefono;
	private Date dataNacemento;
	private Integer altura;
	private Medio medio;

	public Visitante(String dni, String nome, String nacionalidade, String telefono, Date dataNacemento, Integer altura, Medio medio) {
		if(dni!=null){
			this.dni = dni;
		}
		if(nome!=null){
			this.nome = nome;
		}
		if(nacionalidade!=null){
			this.nacionalidade = nacionalidade;
		}
		if(telefono!=null){
			this.telefono = telefono;
		}
		if(dataNacemento!=null){
			this.dataNacemento = dataNacemento;
		}
		if(altura!=null){
			this.altura = altura;
		}
		if(medio!=null){
			this.medio = medio;
		}
	}

	public String getDni() {
		return dni;
	}

	public String getNome() {
		return nome;
	}

	public String getNacionalidade() {
		return nacionalidade;
	}

	public String getTelefono() {
		return telefono;
	}

	public Date getDataNacemento() {
		return dataNacemento;
	}

	public Integer getAltura() {
		return altura;
	}

	public Medio getMedio() {
		return medio;
	}

	public void setDni(String dni) {
		if(dni!=null){
			this.dni = dni;
		}
	}

	public void setNome(String nome) {
		if(nome!=null){
			this.nome = nome;
		}
	}

	public void setNacionalidade(String nacionalidade) {
		if(nacionalidade!=null){
			this.nacionalidade = nacionalidade;
		}
	}

	public void setTelefono(String telefono) {
		if(telefono!=null){
			this.telefono = telefono;
		}
	}

	public void setDataNacemento(Date dataNacemento) {
		if(dataNacemento!=null){
			this.dataNacemento = dataNacemento;
		}
	}

	public void setAltura(Integer altura) {
		if(altura!=null){
			this.altura = altura;
		}
	}

	public void setMedio(Medio medio) {
		if(medio!=null){
			this.medio = medio;
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Visitante)) return false;
		Visitante visitante = (Visitante) o;
		return dni.equals(visitante.dni);
	}

	@Override
	public int hashCode() {
		return Objects.hash(dni);
	}
}
