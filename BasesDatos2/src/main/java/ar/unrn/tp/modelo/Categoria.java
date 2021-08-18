package ar.unrn.tp.modelo;

/**
 *
 * Materia: Bases de Datos 2
 * 
 * @author Facundo Alcalde
 * @version 1.0
 *
 */
public class Categoria {

	private String descripcion;
	
	
	
	//constructors
	public Categoria() {
		// TODO Auto-generated constructor stub
	}

	
	public Categoria(String descripcion) {
		super();
		this.descripcion = descripcion;
	}


	//getters and setters
	public String getDescripcion() {
			return descripcion;
		}
		
	public void setDescripcion(String descripcion) {
			this.descripcion = descripcion;
		}
	


	

}
