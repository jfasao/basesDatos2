package ar.unrn.tp.api;

import java.util.List;

import ar.unrn.tp.modelo.DescuentoMarca;


public interface DescuentoMarcaService {
	
		
		// 
		void crearDescuentoMarca(DescuentoMarca descuentoMarca);
		//
		void modificarDescuentoMarca(DescuentoMarca descuentoMarca);
		
		void eliminarDescuentoMarca(long idDescuentoMarca);

		List<DescuentoMarca> listarDescuentoMarcaPorMarca(long idMarca);



}
