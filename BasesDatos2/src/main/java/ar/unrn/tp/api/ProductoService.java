package ar.unrn.tp.api;

import java.math.BigDecimal;
import java.util.List;


import ar.unrn.tp.modelo.Precio;
import ar.unrn.tp.modelo.Producto;


public interface ProductoService {
	
	
	
	//validar que sea una categoría existente y que codigo no se repita
	void crearProducto(String codigo, String descripcion, Long idCategoria, Long idMarca, BigDecimal  precio);
	
	
	//validar que sea un producto existente
	void modificarProducto(Long idProducto, String codigo, String descripcion, Long idCategoria, Long idMarca, BigDecimal  idPrecio);
	 
	
	//Devuelve todos los productos
	 List listarProductos();

	
	// 
	void crearProducto(Producto producto);
	//
	void modificarProducto(Producto producto);
	
     void eliminarProducto(long IdProducto);


	 List<Precio> listarPrecios(Long idProducto);
	 
	


}
