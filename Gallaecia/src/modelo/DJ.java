package modelo;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Clase modelo del patrón Modelo-Vista-Controlador
 * Representa a la tabla DJ
 */
public class DJ {
	private String dni;
	private String nome;
	private String rua;
	private Integer numero;
	private Integer cp;
	private String localidade;
	private Float salario;
	private String telefono;
	private Date dataInicio;
	private Date dataNacemento;
	private String formacion;
	private SistemaDeAudio sistema;

	public DJ(String dni, String nome, String rua, Integer numero, Integer cp, String localidade, Float salario, String telefono, Date dataInicio, Date dataNacemento, String formacion, SistemaDeAudio sistema) {
		if(dni!=null){
			this.dni = dni;
		}
		if(nome!=null){
			this.nome = nome;
		}
		if(rua!=null){
			this.rua = rua;
		}
		if(numero!=null){
			this.numero = numero;
		}
		if(cp!=null){
			this.cp = cp;
		}
		if(localidade!= null){
			this.localidade = localidade;
		}
		if(salario!=null){
			this.salario = salario;
		}
		if(telefono!=null){
			this.telefono = telefono;
		}
		if(dataInicio!=null){
			this.dataInicio = dataInicio;
		}
		if(dataNacemento!=null){
			this.dataNacemento = dataNacemento;
		}
		if(formacion!=null){
			this.formacion = formacion;
		}
		if(sistema!=null){
			this.sistema = sistema;
		}
	}

	public String getDni() {
		return dni;
	}

	public String getNome() {
		return nome;
	}

	public String getRua() {
		return rua;
	}

	public Integer getNumero() {
		return numero;
	}

	public Integer getCp() {
		return cp;
	}

	public String getLocalidade() {
		return localidade;
	}

	public Float getSalario() {
		return salario;
	}

	public String getTelefono(){
		return telefono;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public Date getDataNacemento() {
		return dataNacemento;
	}

	public String getFormacion() {
		return formacion;
	}

	public SistemaDeAudio getSistema() {
		return sistema;
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

	public void setRua(String rua) {
		if(rua!=null){
			this.rua = rua;
		}
	}

	public void setNumero(Integer numero) {
		if(numero!=null){
			this.numero = numero;
		}
	}

	public void setCp(Integer cp) {
		if(cp!=null){
			this.cp = cp;
		}
	}

	public void setLocalidade(String localidade) {
		if(localidade!=null){
			this.localidade = localidade;
		}
	}

	public void setSalario(Float salario) {
		if(salario!=null){
			this.salario = salario;
		}
	}

	public void setTelefono(String telefono) {
		if(telefono!=null){
			this.telefono = telefono;
		}
	}

	public void setDataInicio(Date dataInicio) {
		if(dataInicio!=null){
			this.dataInicio = dataInicio;
		}
	}

	public void setDataNacemento(Date dataNacemento) {
		if(dataNacemento!=null){
			this.dataNacemento = dataNacemento;
		}
	}

	public void setFormacion(String formacion) {
		if(formacion!=null){
			this.formacion = formacion;
		}
	}

	public void setSistema(SistemaDeAudio sistema) {
		if(sistema!=null){
			this.sistema = sistema;
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof DJ)) return false;
		DJ dj = (DJ) o;
		return dni.equals(dj.dni);
	}

	@Override
	public int hashCode() {
		return Objects.hash(dni);
	}
}
