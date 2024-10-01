package ar.unrn.tp.api;

import java.util.List;

import ar.unrn.tp.modelo.TarjetaCredito;
import ar.unrn.tp.modelo.TipoTarjeta;


public interface TipoTarjetaService {
	
	// 
	void crearTipoTarjeta(TipoTarjeta TipoTarjeta);
	//
	void modificarTipoTarjeta(TipoTarjeta TipoTarjeta);
	
	 void eliminarTipoTarjeta(long IdTipoTarjeta);

	List<TarjetaCredito> listarTarjetasCredito(long idTipoTarjeta);

}
