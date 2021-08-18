package ar.unrn.tp.modelo;

/**
 *  Materia: Bases de Datos 2
 *  
 * @author Facundo Alcade
 * @version 1.0
 * 
 */

public class Banco  {
	
	private String Nombre;
	private String Codigo;
	private String Cuit;
	
	
	
	//constructors
	public Banco() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Banco(String nombre, String codigo, String cuit) {
		super();
		Nombre = nombre;
		Codigo = codigo;
		Cuit = cuit;
	}


	// getters and setters
	public String getNombre() {
		return Nombre;
	}
	
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public String getCodigo() {
		return Codigo;
	}
	public void setCodigo(String codigo) {
		Codigo = codigo;
	}
	public String getCuit() {
		return Cuit;
	}
	public void setCuit(String cuit) {
		Cuit = cuit;
	}
	
	

}
