package ar.unrn.tp.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;
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

	
}
