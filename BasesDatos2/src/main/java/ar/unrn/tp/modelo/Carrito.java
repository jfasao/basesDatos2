package ar.unrn.tp.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;




/**
*
* Materia: Bases de Datos 2
* 
* @author Facundo Alcalde
* @version 1.1
*
*/
@Entity
public class Carrito {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="cliente_id")
	private Cliente cliente;
	
	@ManyToMany
	@JoinTable(
	        name = "carrito_producto", // Nombre de la tabla intermedia
	        joinColumns = @JoinColumn(name = "carrito_id"), // Clave foránea hacia carrito
	        inverseJoinColumns = @JoinColumn(name = "producto_id") // Clave foránea hacia  producto
	    )
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
	
		Venta ventaRegistrar=null;
		BigDecimal montoAPagar= calcularMontoDescuentoTarjeta(montoTotalConDescuentos(),tarjetaPago.getBanco().descuentoVigente(tarjetaPago.getTipoTarjeta(),fecha));
		//:TODO validar  y buscar tarjet a
		
		if (tarjetaPago.tarjetaValida(LocalDate.now())&&(tarjetaPago.getBanco().saldoTarjeta(tarjetaPago, montoAPagar))) {
			Venta venta = new Venta(this.cliente, LocalDateTime.now(), productos, tarjetaPago, montoTotalSinDescuentos(), montoAPagar, "compra registrada con exito");
			
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
	
	protected Map<TarjetaCredito,BigDecimal> descuentosPorTarjeta() {
		 LocalDate fecha = LocalDate.now();
		 BigDecimal precio=montoTotalConDescuentos();
		 Map<TarjetaCredito,BigDecimal> salida=new HashMap<TarjetaCredito,BigDecimal>();
		 for (TarjetaCredito tarjeta : cliente.getTarjetasCredito() ) {
			salida.put(tarjeta, calcularMontoDescuentoTarjeta(precio,tarjeta.getBanco().descuentoVigente(tarjeta.getTipoTarjeta(),fecha)));
		}
				 
				 
		 return salida;
		
	}
	/*
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
	}*/
	
	public void agregarProducto(Producto producto) {
		
		if (!productos.contains(producto)) {
			productos.add(producto);
		}
	}
	
	public void quitarProducto(Producto producto) {
		
		if (productos.contains(producto)) {
			productos.add(producto);
		}
	}
	
	
	protected BigDecimal calcularMontoDescuentoTarjeta(BigDecimal precio, int porcentajeDescuento) {
		return precio.multiply(BigDecimal.valueOf((100-porcentajeDescuento)/100));
	}
	
	public BigDecimal calcularMontoDescuentoTarjeta( TarjetaCredito tarjeta, LocalDate fecha ) {
		int porcentajeDescuento =tarjeta.getBanco().descuentoVigente(tarjeta.getTipoTarjeta(),fecha);
		BigDecimal precio= this.montoTotalConDescuentos();
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
		
		
	public Carrito(Cliente cliente) {
			super();
			this.cliente = cliente;
		}

	//getters and setters
	public Cliente getCliente() {
		return cliente;
	}

	private void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	private void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public Long getId() {
		return id;
	}

	protected void setId(Long id) {
		this.id = id;
	}
	
	@Override
	public boolean equals(Object o) {
	    if (this == o) return true;
	    if (o == null || getClass() != o.getClass()) return false;

	    Carrito carrito = (Carrito) o;

	    if (!id.equals(carrito.id)) return false;
	    if (!cliente.equals(carrito.cliente)) return false;
	    return productos.equals(carrito.productos);
	}

	@Override
	public int hashCode() {
	    int result = id.hashCode();
	    result = 31 * result + cliente.hashCode();
	    result = 31 * result + productos.hashCode();
	    return result;
	}
	
	

}
