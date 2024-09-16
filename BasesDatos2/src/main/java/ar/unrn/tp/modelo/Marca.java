package ar.unrn.tp.modelo;

import java.util.ArrayList;
import java.util.List;

/**
*
* Materia: Bases de Datos 2
* 
* @author Facundo Alcalde
* @version 1.0
*
*/
public class Marca {

	private String descripcion;
	private String nacionalidad;
	private DescuentoMarca descuento;
	private List<DescuentoMarca> Descuentos= new ArrayList<DescuentoMarca>();
	
	//constructors
	public Marca() {
		// TODO Auto-generated constructor stub
	}

	public Marca(String descripcion, String nacionalidad) {
		super();
		this.descripcion = descripcion;
		this.nacionalidad = nacionalidad;
	}

	//getters and setters
	protected String getDescripcion() {
		return descripcion;
	}

	protected void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	protected String getNacionalidad() {
		return nacionalidad;
	}

	protected void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	protected DescuentoMarca getDescuento() {
		return descuento;
	}

	protected void setDescuento(DescuentoMarca descuento) {
		this.descuento = descuento;
	}

	protected List<DescuentoMarca> getDescuentos() {
		return Descuentos;
	}

	protected void setDescuentos(List<DescuentoMarca> historialDescuentos) {
		this.Descuentos = historialDescuentos;
	}
	
	
	
	
	

}
