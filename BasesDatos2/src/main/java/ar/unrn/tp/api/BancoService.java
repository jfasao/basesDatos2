package ar.unrn.tp.api;

import java.util.List;

import ar.unrn.tp.modelo.Banco;
import ar.unrn.tp.modelo.DescuentoBanco;
import ar.unrn.tp.modelo.TarjetaCredito;
import ar.unrn.tp.modelo.TipoTarjeta;

public interface BancoService {
	
	// 
	void crearBanco(Banco banco);
	//
	void modificarBanco(Banco banco);
	
	
	 //Devuelve las tarjetas de un Banco específico
	 List<TarjetaCredito> listarTarjetas(Long idBanco);
	 
	//Devuelve los descuentos del banco
	 List< DescuentoBanco> descuentos (long idBanco);
	 
	//Devuelve los descuentos del banco
		 List< DescuentoBanco> descuentosPorTipoTarjeta (TipoTarjeta tipotarjeta,long idBanco);


}
