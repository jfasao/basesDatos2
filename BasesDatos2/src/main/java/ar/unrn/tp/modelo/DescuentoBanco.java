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
	
	
	
	
	
	

}
