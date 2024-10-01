package ar.unrn.tp.modelo;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
/**
*
* Materia: Bases de Datos 2
* 
* @author Facundo Alcalde
* @version 1.0
*
*/
@Entity
public class DescuentoMarca extends Descuento {
	
	@ManyToOne
	@JoinColumn(name="marca_id")
	private Marca marca;
	
	//constructors
	public DescuentoMarca() {
		// TODO Auto-generated constructor stub
	}

	public DescuentoMarca(LocalDate fechaInicio, LocalDate fechaFin, int porcentajeDescuento, Marca marca) {
		super(fechaInicio, fechaFin, porcentajeDescuento);
		this.marca=marca;
	}

	//getters and setters
	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	
	@Override
	public boolean equals(Object o) {
	    if (this == o) return true;
	    if (o == null || getClass() != o.getClass()) return false;

	    DescuentoMarca that = (DescuentoMarca) o;

	    if (porcentajeDescuento != that.porcentajeDescuento) return false;
	    if (!id.equals(that.id)) return false;
	    if (!fechaInicio.equals(that.fechaInicio)) return false;
	    if (!fechaFin.equals(that.fechaFin)) return false;
	    return marca.equals(that.marca);
	}

	@Override
	public int hashCode() {
	    int result = id.hashCode();
	    result = 31 * result + fechaInicio.hashCode();
	    result = 31 * result + fechaFin.hashCode();
	    result = 31 * result + porcentajeDescuento;
	    result = 31 * result + marca.hashCode();
	    return result;
	}
}
