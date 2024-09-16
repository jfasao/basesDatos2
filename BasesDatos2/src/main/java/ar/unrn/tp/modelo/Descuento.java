package ar.unrn.tp.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;


/**
*
* Materia: Bases de Datos 2
* 
* @author Facundo Alcalde
* @version 1.0
*
*/

public class Descuento {
	
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
	
	
	
	
	
}
