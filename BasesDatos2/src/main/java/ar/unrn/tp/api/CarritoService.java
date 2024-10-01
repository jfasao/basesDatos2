package ar.unrn.tp.api;

import java.util.List;

import ar.unrn.tp.modelo.Carrito;
import ar.unrn.tp.modelo.Producto;


public interface CarritoService {
	
	// 
	void crearCarrito(Carrito carrito);
	//
	void modificarCarrito(Carrito carrito);
	

	
	void eliminarCarrito(Long idCarrito);

	 //Devuelve las tarjetas de un Banco específico
	 List<Producto> listarProductos(Long idCarrito);
	 
	


}
