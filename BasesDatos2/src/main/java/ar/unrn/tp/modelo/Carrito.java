package ar.unrn.tp.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ar.unrn.tp.modelo.Producto;
import ar.unrn.tp.modelo.Precio;


/**
*
* Materia: Bases de Datos 2
* 
* @author Facundo Alcalde
* @version 1.0
*
*/

public class Carrito {
	
	private Cliente cliente;
	private List<Producto> productos = new ArrayList<Producto>();

	

	public BigDecimal montoTotalSinDescuentos() {
		BigDecimal total = productos.stream()
        .map(producto -> producto.getPrecio().getValor()) // Mapea cada Producto al valor de su precio
        .reduce(BigDecimal.ZERO, BigDecimal::add); // Suma todos los BigDecimal

		return total;
		
	}
	
	public BigDecimal montoTotalConDescuentos() {
	    LocalDate fecha = LocalDate.now();
	    return productos.stream()
	                    .map(producto -> producto.calcularPrecioConDescuentoPorMarca(fecha)) // Mapea cada producto a su precio con descuento
	                    .reduce(BigDecimal.ZERO, BigDecimal::add); // Suma todos los precios con descuento
	}
	
	public Venta registrarVenta(TarjetaCredito tarjetaPago) {
		LocalDate fecha= LocalDate.now();
		boolean ventaExitosa=false;
		Venta ventaRegistrar=null;
		BigDecimal montoAPagar= calcularMontoDescuentoTarjeta(montoTotalConDescuentos(),tarjetaPago.getBanco().descuentoVigente(tarjetaPago.getTipoTarjeta(),fecha));
		//:TODO validar  y buscar tarjet a
		
		if (tarjetaPago.tarjetaValida(LocalDate.now())&&(tarjetaPago.getBanco().saldoTarjeta(tarjetaPago, montoAPagar))) {
			Venta venta = new Venta(this.cliente, LocalDateTime.now(), productos, tarjetaPago, montoTotalSinDescuentos(), montoAPagar, "compra registrada con exito");
			ventaExitosa=true;
		}
		
		return ventaRegistrar;
	}
	/* reemplaado por version lambda
	public BigDecimal montoTotalConDescuentos() {
		BigDecimal total =BigDecimal.ZERO;
		LocalDate fecha = LocalDate.now();
		for (Producto producto : productos) {
			total.add(producto.calcularPrecioConDescuentoPorMarca(fecha));
		}
		
		return total;
	}
	*/
	
	public Map<TarjetaCredito,BigDecimal> descuentosPorTarjeta() {
		 LocalDate fecha = LocalDate.now();
		 BigDecimal precio=montoTotalConDescuentos();
		 Map<TarjetaCredito,BigDecimal> salida=new HashMap<TarjetaCredito,BigDecimal>();
		 for (TarjetaCredito tarjeta : cliente.getTarjetasCredito() ) {
			salida.put(tarjeta, calcularMontoDescuentoTarjeta(precio,tarjeta.getBanco().descuentoVigente(tarjeta.getTipoTarjeta(),fecha)));
		}
				 
				 
		 return salida;
		
	}
	
	public boolean agregarProducto(Producto producto) {
		boolean salida = true;
		if (!productos.contains(producto)) {
			productos.add(producto);
		}else {
			salida=false;
		}
		
		
		return salida;
	}
	
	public boolean quitarProducto(Producto producto) {
		boolean salida =true;
		if (productos.contains(producto)) {
			productos.add(producto);
		}else {
			salida=false;
		}
		return salida;
	}
	
	
	public BigDecimal calcularMontoDescuentoTarjeta(BigDecimal precio, int porcentajeDescuento) {
		return precio.multiply(BigDecimal.valueOf((100-porcentajeDescuento)/100));
	}
	//constructors
		public Carrito() {
			// TODO Auto-generated constructor stub
		}

		public Carrito(Cliente cliente, List<Producto> productos) {
			super();
			this.cliente = cliente;
			this.productos = productos;
		}
	//getters and setters
	public Cliente getCliente() {
		return cliente;
	}

	private void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	private List<Producto> getProductos() {
		return productos;
	}

	private void setProductos(List<Producto> productos) {
		this.productos = productos;
	}
	
	
	
	
	

}
