package ar.unrn.tp.api;

import java.util.List;

import ar.unrn.tp.modelo.Precio;


public interface PrecioService {
	
	// 
	void crearPrecio(Precio Precio);
	//
	void modificarPrecio(Precio Precio);
	
   void eliminarPrecio(long IdPrecio);

	


}
