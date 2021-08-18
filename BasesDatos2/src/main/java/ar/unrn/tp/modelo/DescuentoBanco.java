package ar.unrn.tp.modelo;

import java.util.Date;

/**
*
* Materia: Bases de Datos 2
* 
* @author Facundo Alcalde
* @version 1.0
*
*/

public class DescuentoBanco extends Descuento {

	private Banco banco;
	
	//cosntructors
	public DescuentoBanco() {
		// TODO Auto-generated constructor stub
	}

	public DescuentoBanco(Date fechaInicio, Date fechaFin, Integer porcentajeDescuento,Banco banco) {
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
