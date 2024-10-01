package ar.unrn.tp.api;

import java.util.List;

import ar.unrn.tp.modelo.TarjetaCredito;


public interface TarjetaCreditoService {
	
	// 
	void crearTarjetaCredito(TarjetaCredito tarjetaCredito);
	//
	void modificarTarjetaCredito(TarjetaCredito tarjetaCredito);
	
    void eliminarTarjetaCredito(long IdTarjetaCredito);

	

}
