package ar.unrn.tp.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nombre;
	private String apellido;
	private String email;
	private String dni;
	
	@OneToMany(cascade = CascadeType.PERSIST)
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

	public Cliente(String nombre, String apellido, String email, String dni) {
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
		
	}

	
	public Cliente(String nombre, String apellido, String email, String dni, List<TarjetaCredito> tarjetas) {
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
		this.tarjetasCredito = tarjetas;
	}
	
	public void agregarTarjeta(TarjetaCredito tarjeta) {
		
		this.getTarjetasCredito().add(tarjeta);
	}
	
	public boolean perteneceTarjeta(TarjetaCredito tarjeta) {
		
		return this.getTarjetasCredito()
         .stream()
         .anyMatch(t -> t.equals(tarjeta));
		
	}
	
	//getters and setters
	private String getNombre() {
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	@Override
	public boolean equals(Object o) {
	    if (this == o) return true;
	    if (o == null || getClass() != o.getClass()) return false;

	    Cliente cliente = (Cliente) o;

	    if (!id.equals(cliente.id)) return false;
	    if (!dni.equals(cliente.dni)) return false;
	    if (!nombre.equals(cliente.nombre)) return false;
	    if (!apellido.equals(cliente.apellido)) return false;
	    return email.equals(cliente.email);
	}

	@Override
	public int hashCode() {
	    int result = id.hashCode();
	    result = 31 * result + dni.hashCode();
	    result = 31 * result + nombre.hashCode();
	    result = 31 * result + apellido.hashCode();
	    result = 31 * result + email.hashCode();
	    return result;
	}

	
	
}
