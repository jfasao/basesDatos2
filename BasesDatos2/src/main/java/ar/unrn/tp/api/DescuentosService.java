package ar.unrn.tp.api;

import java.time.LocalDate;


public interface DescuentosService {

	
		// validar que las fechas no se superpongan descuentos por tarjeta-banco
		void crearDescuentoSobreTotal( LocalDate fechaDesde, LocalDate fechaHasta, int porcentaje, Long IdBanco);
		
		
		
		 // validar que las fechas no se superpongan
		void crearDescuento( LocalDate fechaDesde, LocalDate fechaHasta, int porcentaje, Long idMarca);
	
		 
		
	
}
