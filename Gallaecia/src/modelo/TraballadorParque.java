package modelo;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Clase modelo del patrón Modelo-Vista-Controlador
 * Representa a la tabla TraballadoresParque
 */
public class TraballadorParque {
	private String dni;
	private String nome;
	private String rua;
	private Integer numero;
	private Integer cp;
	private String localidade;
	private Float salario;
	private String telefono;
	private LocalDate dataInicio;
	private LocalDate dataNacemento;
	private String formacion;
	private Atraccion atraccion;
	private Espectaculo espectaculo;

	public TraballadorParque(String dni, String nome, String rua, Integer numero, Integer cp, String localidade, Float salario, String telefono, LocalDate dataInicio, LocalDate dataNacemento, String formacion, Atraccion atraccion, Espectaculo espectaculo) {
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
		if(atraccion!=null){
			this.atraccion = atraccion;
		}
		if(espectaculo!=null){
			this.espectaculo = espectaculo;
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

	public LocalDate getDataInicio() {
		return dataInicio;
	}

	public LocalDate getDataNacemento() {
		return dataNacemento;
	}

	public String getFormacion() {
		return formacion;
	}

	public Atraccion getAtraccion() {
		return atraccion;
	}

	public Espectaculo getEspectaculo() {
		return espectaculo;
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

	public void setDataInicio(LocalDate dataInicio) {
		if(dataInicio!=null){
			this.dataInicio = dataInicio;
		}
	}

	public void setDataNacemento(LocalDate dataNacemento) {
		if(dataNacemento!=null){
			this.dataNacemento = dataNacemento;
		}
	}

	public void setFormacion(String formacion) {
		if(formacion!=null){
			this.formacion = formacion;
		}
	}
	public void setAtraccion(Atraccion atraccion) {
		if(atraccion!=null){
			this.atraccion = atraccion;
		}
	}

	public void setEspectaculo(Espectaculo espectaculo) {
		if(espectaculo!=null){
			this.espectaculo = espectaculo;
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof TraballadorParque)) return false;
		TraballadorParque that = (TraballadorParque) o;
		return dni.equals(that.dni);
	}

	@Override
	public int hashCode() {
		return Objects.hash(dni);
	}
}
