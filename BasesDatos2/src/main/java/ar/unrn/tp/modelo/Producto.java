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

public class Producto {
	
	private String codigo;
	private String descripcion;
	
	private Categoria categoria;
	private Marca marca;
	
	private Precio precio;
	private List<Precio> historialPrecios = new ArrayList<Precio>();
	
	//cosntructors
	public Producto() {
		// TODO Auto-generated constructor stub
	}

	public Producto(String codigo, String descripcion, Categoria categoria, Marca marca, Precio precio,
			List<Precio> historialPrecios) {
		super();
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.categoria = categoria;
		this.marca = marca;
		this.precio = precio;
		this.historialPrecios = historialPrecios;
	}

	//getters and setters
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public Precio getPrecio() {
		return precio;
	}

	public void setPrecio(Precio precio) {
		this.precio = precio;
	}

	public List<Precio> getHistorialPrecios() {
		return historialPrecios;
	}

	public void setHistorialPrecios(List<Precio> historialPrecios) {
		this.historialPrecios = historialPrecios;
	}

	
	
}
