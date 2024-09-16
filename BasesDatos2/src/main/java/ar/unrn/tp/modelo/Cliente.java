package ar.unrn.tp.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	
	
	public Map<TarjetaCredito,Integer> descuentosVigentesTarjetas(LocalDate fecha){
		Map<TarjetaCredito,Integer> salida= new HashMap<TarjetaCredito, Integer>();
		for (TarjetaCredito tarjetaCredito : tarjetasCredito) {
			salida.put(tarjetaCredito,tarjetaCredito.descuentoVigente(fecha));
		}
		
		return salida;
	}
	
	//constructors
	public Cliente() {
		// TODO Auto-generated constructor stub
	}

	public Cliente(String nombre, String apellido, String email, String dni, List<TarjetaCredito> tarjetasCredito) {
		super();
		 if (nombre == null || nombre.trim().isEmpty()) {
		        throw new IllegalArgumentException("El nombre no puede ser nulo o vacío.");
		    }
		    if (apellido == null || apellido.trim().isEmpty()) {
		        throw new IllegalArgumentException("El apellido no puede ser nulo o vacío.");
		    }
		    if (dni == null || dni.trim().isEmpty()) {
		        throw new IllegalArgumentException("El DNI no puede ser nulo o vacío.");
		    }
		    if (email == null || !email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
		        throw new IllegalArgumentException("El email es inválido.");
		    }
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.dni = dni;
		this.tarjetasCredito = tarjetasCredito;
	}

	
	
	
	//getters and setters
	private String getNombre() {
		return nombre;
	}

	private void setNombre(String nombre) {
		this.nombre = nombre;
	}

	private String getApellido() {
		return apellido;
	}

	private void setApellido(String apellido) {
		this.apellido = apellido;
	}

	private String getEmail() {
		return email;
	}

	private void setEmail(String email) {
		this.email = email;
	}

	private String getDni() {
		return dni;
	}

	private void setDni(String dni) {
		this.dni = dni;
	}

	protected List<TarjetaCredito> getTarjetasCredito() {
		return tarjetasCredito;
	}

	protected void setTarjetasCredito(List<TarjetaCredito> tarjetasCredito) {
		this.tarjetasCredito = tarjetasCredito;
	}

	
	
	
}
