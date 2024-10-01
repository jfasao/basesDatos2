package ar.unrn.tp.api;

import java.util.List;

import ar.unrn.tp.modelo.Marca;
import ar.unrn.tp.modelo.DescuentoMarca;


public interface MarcaService {
	
	// 
	void crearMarca(Marca Marca);
	//
	void modificarMarca(Marca Marca);
	
	
	void eliminarMarca(long IdMarca);


	 
	//Devuelve los descuentos del Marca
	 List< DescuentoMarca> descuentos (long idMarca);
	 


}
