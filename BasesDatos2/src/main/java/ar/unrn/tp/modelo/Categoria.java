package ar.unrn.tp.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * Materia: Bases de Datos 2
 * 
 * @author Facundo Alcalde
 * @version 1.0
 *
 */
@Entity
public class Categoria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

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
	private String getDescripcion() {
			return descripcion;
		}
		
	private void setDescripcion(String descripcion) {
			this.descripcion = descripcion;
		}


	protected Long getId() {
		return id;
	}


	protected void setId(Long id) {
		this.id = id;
	}
	


	

}
