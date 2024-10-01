package ar.unrn.tp.api;

import java.math.BigDecimal;
import java.util.List;

import ar.unrn.tp.modelo.Producto;
import ar.unrn.tp.modelo.Venta;

public interface VentaService {
	
	
	//Crea una venta. El monto se calcula aplicando los descuentos a la fecha
	 // validaciones:
	// - debe ser un cliente existente
	// - la lista de productos no debe estar vacía
	 // - La tarjeta debe pertenecer al cliente
	void realizarVenta(Long idCliente, List<Long> productos, Long idTarjeta);
	//Devuelve el monto total aplicando los descuentos al día de la fecha
	 // validar que no llegue una lista vacía y la tarjeta exista
	BigDecimal calcularMonto(List<Long> productos, Long idTarjeta);
	 //Devuelve todas las ventas realizadas
	 List ventas(); 
	
	// 
	void crearVenta(Venta venta);
	//
	void modificarVenta(Venta venta);
	
	void eliminarVenta(long IdVenta);

	 //Devuelve las tarjetas de un Venta específico
	 List<Producto> listarProductos(Long idProducto);
	 
	


}
