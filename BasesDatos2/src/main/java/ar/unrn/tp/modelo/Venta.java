package ar.unrn.tp.modelo;

import java.math.BigDecimal;
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

	private Date fechaHora;
	private List<Producto> productos = new ArrayList<Producto>();
	private TarjetaCredito tarjetaPago;
	private BigDecimal monto;
	
	
	//constructors
	public Venta() {
		// TODO Auto-generated constructor stub
	}


	public Venta(Date fechaHora, List<Producto> productos, TarjetaCredito tarjetaPago, BigDecimal monto) {
		super();
		this.fechaHora = fechaHora;
		this.productos = productos;
		this.tarjetaPago = tarjetaPago;
		this.monto = monto;
	}

	//getters and setters
	public Date getFechaHora() {
		return fechaHora;
	}


	public void setFechaHora(Date fechaHora) {
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


	public BigDecimal getMonto() {
		return monto;
	}


	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}
	
	
	
	

}
