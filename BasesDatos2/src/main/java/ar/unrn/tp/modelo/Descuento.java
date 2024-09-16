package ar.unrn.tp.modelo;


import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;


/**
*
* Materia: Bases de Datos 2
* 
* @author Facundo Alcalde
* @version 1.0
*
*/

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public  class Descuento  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private LocalDate fechaInicio;
	private LocalDate fechaFin;
	private int porcentajeDescuento;
	
	
	// verificar si el descuento es vigente
	public boolean descuentoVigente(LocalDate fecha) {
		
		 return fechaInicio != null && fechaFin != null &&
	               !fecha.isBefore(fechaInicio) && !fecha.isAfter(fechaFin);	
		
	}
	
	  // Verificar si dos descuentos se solapan
    public boolean descuentoSolapado(Descuento otroDescuento) {
        return (fechaInicio != null && fechaFin != null &&
                otroDescuento.fechaInicio != null && otroDescuento.fechaFin != null) &&
               (fechaInicio.isBefore(otroDescuento.fechaFin) && fechaFin.isAfter(otroDescuento.fechaInicio));
    }
	
	//constructors
	public Descuento() {
		// TODO Auto-generated constructor stub
	}


	public Descuento(LocalDate fechaInicio, LocalDate fechaFin, int porcentajeDescuento) {
		super();
		 if (fechaInicio == null || fechaFin == null) {
	            throw new IllegalArgumentException("Las fechas de inicio y fin no pueden ser nulas.");
	        }
	        if (fechaInicio.isAfter(fechaFin)) {
	            throw new IllegalArgumentException("La fecha de inicio no puede ser después de la fecha de fin.");
	        }
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.porcentajeDescuento = porcentajeDescuento;
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


	public int getPorcentajeDescuento() {
		return porcentajeDescuento;
	}


	public void setPorcentajeDescuento(int porcentajeDescuento) {
		this.porcentajeDescuento = porcentajeDescuento;
	}

	protected Long getId() {
		return id;
	}

	protected void setId(Long id) {
		this.id = id;
	}
	
	
	
	
	
}
