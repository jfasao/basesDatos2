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

public class DescuentoMarca extends Descuento {
	
	private Marca marca;
	
	//constructors
	public DescuentoMarca() {
		// TODO Auto-generated constructor stub
	}

	public DescuentoMarca(Date fechaInicio, Date fechaFin, Integer porcentajeDescuento, Marca marca) {
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

	
}
