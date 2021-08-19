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

public class Carrito {
	
	private Cliente cliente;
	private List<Producto> productos = new ArrayList<Producto>();

	//constructors
	public Carrito() {
		// TODO Auto-generated constructor stub
	}

	public Carrito(Cliente cliente, List<Producto> productos) {
		super();
		this.cliente = cliente;
		this.productos = productos;
	}

	//getters and setters
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}
	
	
	
	

}
