package ar.unrn.tp.modelo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
*
* Materia: Bases de Datos 2
* 
* @author Facundo Alcalde
* @version 1.0
*
*/

public class Venta {

	private LocalDateTime fechaHora;
	private Cliente cliente;
	private List<Producto> productos = new ArrayList<Producto>();
	private TarjetaCredito tarjetaPago;
	private BigDecimal montoTotal;
	private BigDecimal montoTotalConDescuentos;
	
	private String observaciones;
	
	
	//constructors
	public Venta() {
		// TODO Auto-generated constructor stub
	}




	public Venta(Cliente cliente,LocalDateTime fechaHora, List<Producto> productos, TarjetaCredito tarjetaPago, BigDecimal montoTotal,
			BigDecimal montoTotalConDescuentos, String obsrvaciones) {
		super();
		this.fechaHora = fechaHora;
		this.productos = productos;
		this.tarjetaPago = tarjetaPago;
		this.montoTotal = montoTotal;
		this.montoTotalConDescuentos = montoTotalConDescuentos;
		this.observaciones = obsrvaciones;
	}


	//getters and setters
	public LocalDateTime getFechaHora() {
		return fechaHora;
	}


	public void setFechaHora(LocalDateTime fechaHora) {
		this.fechaHora = fechaHora;
	}


	public List<Producto> getProductos() {
		return productos;
	}


	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}


	public TarjetaCredito getTarjetaPago() {
		return tarjetaPago;
	}


	public void setTarjetaPago(TarjetaCredito tarjetaPago) {
		this.tarjetaPago = tarjetaPago;
	}




	protected BigDecimal getMontoTotal() {
		return montoTotal;
	}




	protected void setMontoTotal(BigDecimal montoTotal) {
		this.montoTotal = montoTotal;
	}




	protected BigDecimal getMontoTotalConDescuentos() {
		return montoTotalConDescuentos;
	}




	protected void setMontoTotalConDescuentos(BigDecimal montoTotalConDescuentos) {
		this.montoTotalConDescuentos = montoTotalConDescuentos;
	}




	protected String getObservaciones() {
		return observaciones;
	}




	protected void setObservaciones(String obsrvaciones) {
		this.observaciones = obsrvaciones;
	}




	protected Cliente getCliente() {
		return cliente;
	}




	protected void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}


	
	
	
	

}
