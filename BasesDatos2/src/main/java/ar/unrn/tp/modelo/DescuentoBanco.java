package ar.unrn.tp.modelo;

import java.math.BigDecimal;
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
public class DescuentoBanco extends Descuento {

	@ManyToOne
	@JoinColumn(name="banco_id")
	private Banco banco;
	
	//cosntructors
	public DescuentoBanco() {
		// TODO Auto-generated constructor stub
	}

	public DescuentoBanco(LocalDate fechaInicio, LocalDate fechaFin, int porcentajeDescuento,Banco banco) {
		super(fechaInicio, fechaFin, porcentajeDescuento);
		this.banco = banco;
	}


	//getters and setters
	public Banco getBanco() {
		return banco;
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
	}
	
	
	
	@Override
	public boolean equals(Object o) {
	    if (this == o) return true;
	    if (o == null || getClass() != o.getClass()) return false;

	    DescuentoBanco that = (DescuentoBanco) o;

	    if (porcentajeDescuento != that.porcentajeDescuento) return false;
	    if (!id.equals(that.id)) return false;
	    if (!fechaInicio.equals(that.fechaInicio)) return false;
	    if (!fechaFin.equals(that.fechaFin)) return false;
	    return banco.equals(that.banco);
	}

	@Override
	public int hashCode() {
	    int result = id.hashCode();
	    result = 31 * result + fechaInicio.hashCode();
	    result = 31 * result + fechaFin.hashCode();
	    result = 31 * result + porcentajeDescuento;
	    result = 31 * result + banco.hashCode();
	    return result;
	}

	
	

}
