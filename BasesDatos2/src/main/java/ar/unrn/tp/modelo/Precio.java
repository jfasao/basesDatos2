package ar.unrn.tp.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;

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
public class Precio {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private LocalDate fechaInicio;
	private LocalDate fechaFin;
	private BigDecimal valor;
	

	//constructors
	public Precio() {
		// TODO Auto-generated constructor stub
	}


	public Precio(LocalDate fechaInicio, LocalDate fechaFin, BigDecimal valor) {
		super();
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.valor = valor;
	}

	public boolean precioVigente(Precio precio) {
		boolean salida=false;
		
		if (this.getFechaInicio().isBefore(precio.getFechaInicio()) && this.getFechaFin()==null) {
			this.setFechaFin(precio.getFechaInicio());
			salida=true;
		}
		return salida;
	}
	
	
	//getters and setters
	public LocalDate getFechaInicio() {
		return fechaInicio;
	}


	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}


	public LocalDate getFechaFin() {
		return fechaFin;
	}


	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}


	public BigDecimal getValor() {
		return valor;
	}


	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}


	protected Long getId() {
		return id;
	}


	protected void setId(Long id) {
		this.id = id;
	}
	
	
	
	

}
