package ar.unrn.tp.modelo;

import java.math.BigDecimal;
import java.util.Date;

/**
*
* Materia: Bases de Datos 2
* 
* @author Facundo Alcalde
* @version 1.0
*
*/

public class Precio {
	
	private Date fechaInicio;
	private Date fechaFin;
	private BigDecimal valor;
	

	//constructors
	public Precio() {
		// TODO Auto-generated constructor stub
	}


	public Precio(Date fechaInicio, Date fechaFin, BigDecimal valor) {
		super();
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.valor = valor;
	}

	//getters and setters
	public Date getFechaInicio() {
		return fechaInicio;
	}


	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}


	public Date getFechaFin() {
		return fechaFin;
	}


	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}


	public BigDecimal getValor() {
		return valor;
	}


	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
	
	
	

}
