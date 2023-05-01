package modelo;

import java.util.Objects;

/**
 * Clase modelo del patrón Modelo-Vista-Controlador
 * Representa a la tabla AtraccionesFamiliares
 */
public class AtraccionFamiliar {
	private String atraccion;
	private Integer idadeRecomendada;

	public AtraccionFamiliar(String atraccion, Integer idadeRecomendada) {
		if(atraccion!=null){
			this.atraccion = atraccion;
		}
		if(idadeRecomendada!=null){
			this.idadeRecomendada = idadeRecomendada;
		}
	}

	public String getAtraccion() {
		return atraccion;
	}

	public Integer getIdadeRecomendada() {
		return idadeRecomendada;
	}


	public void setAtraccion(String atraccion) {
		if(atraccion!=null){
			this.atraccion = atraccion;
		}
	}

	public void setIdadeMin(Integer idadeRecomendada) {
		if(idadeRecomendada!=null){
			this.idadeRecomendada = idadeRecomendada;
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof AtraccionFamiliar)) return false;
		AtraccionFamiliar that = (AtraccionFamiliar) o;
		return atraccion.equals(that.atraccion);
	}

	@Override
	public int hashCode() {
		return Objects.hash(atraccion);
	}
}
