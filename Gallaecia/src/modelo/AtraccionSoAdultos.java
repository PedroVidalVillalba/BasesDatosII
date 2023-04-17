package modelo;

import java.util.Objects;

public class AtraccionSoAdultos {
	private Atraccion atraccion;
	private Integer idadeMin;

	public AtraccionSoAdultos(Atraccion atraccion, Integer idadeMin) {
		if(atraccion!=null){
			this.atraccion = atraccion;
		}
		if(idadeMin!=null){
			this.idadeMin = idadeMin;
		}
	}

	public Atraccion getAtraccion() {
		return atraccion;
	}

	public Integer getIdadeMin() {
		return idadeMin;
	}


	public void setAtraccion(Atraccion atraccion) {
		if(atraccion!=null){
			this.atraccion = atraccion;
		}
	}

	public void setIdadeMin(Integer idadeMin) {
		if(idadeMin!=null){
			this.idadeMin = idadeMin;
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof AtraccionSoAdultos)) return false;
		AtraccionSoAdultos that = (AtraccionSoAdultos) o;
		return atraccion.equals(that.atraccion);
	}

	@Override
	public int hashCode() {
		return Objects.hash(atraccion);
	}
}
