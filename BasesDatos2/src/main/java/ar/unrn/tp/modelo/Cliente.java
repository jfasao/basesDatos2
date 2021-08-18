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

public class Cliente {

	private String nombre;
	private String apellido;
	private String email;
	private String dni;
	
	private List<TarjetaCredito> tarjetasCredito = new ArrayList<TarjetaCredito> ();
	
	//constructors
	public Cliente() {
		// TODO Auto-generated constructor stub
	}

	public Cliente(String nombre, String apellido, String email, String dni, List<TarjetaCredito> tarjetasCredito) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.dni = dni;
		this.tarjetasCredito = tarjetasCredito;
	}

	
	//getters and setters
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public List<TarjetaCredito> getTarjetasCredito() {
		return tarjetasCredito;
	}

	public void setTarjetasCredito(List<TarjetaCredito> tarjetasCredito) {
		this.tarjetasCredito = tarjetasCredito;
	}

	
	
	
}
