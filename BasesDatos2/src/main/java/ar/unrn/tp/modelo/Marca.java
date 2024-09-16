package ar.unrn.tp.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
*
* Materia: Bases de Datos 2
* 
* @author Facundo Alcalde
* @version 1.0
*
*/
@Entity
public class Marca {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String descripcion;
	private String nacionalidad;
	
	@OneToMany(mappedBy = "marca", cascade = CascadeType.ALL)
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

	

	protected List<DescuentoMarca> getDescuentos() {
		return Descuentos;
	}

	protected void setDescuentos(List<DescuentoMarca> historialDescuentos) {
		this.Descuentos = historialDescuentos;
	}

	protected Long getId() {
		return id;
	}

	protected void setId(Long id) {
		this.id = id;
	}
	
	
	
	
	

}
