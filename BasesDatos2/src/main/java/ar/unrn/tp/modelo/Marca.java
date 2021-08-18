package ar.unrn.tp.modelo;

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
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	
	
	
	

}
