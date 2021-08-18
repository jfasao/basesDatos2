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

public class Descuento {
	
	private Date fechaInicio;
	private Date fechaFin;
	private Integer porcentajeDescuento;
	
	
	//constructors
	public Descuento() {
		// TODO Auto-generated constructor stub
	}


	public Descuento(Date fechaInicio, Date fechaFin, Integer porcentajeDescuento) {
		super();
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.porcentajeDescuento = porcentajeDescuento;
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


	public Integer getPorcentajeDescuento() {
		return porcentajeDescuento;
	}


	public void setPorcentajeDescuento(Integer porcentajeDescuento) {
		this.porcentajeDescuento = porcentajeDescuento;
	}
	
	
	
	
	
}
