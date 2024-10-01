package ar.unrn.tp.api;

import java.util.List;

import ar.unrn.tp.modelo.DescuentoBanco;



public interface DescuentoBancoService {
	
	// 
	void crearDescuentoBanco(DescuentoBanco descuentoBanco);
	//
	void modificarDescuentoBanco(DescuentoBanco descuentoBanco);
	
	void eliminarDescuentoBanco(long idDescuentoBanco);

	List<DescuentoBanco> listarDescuentoBancoPorBanco(long idBanco);


}
