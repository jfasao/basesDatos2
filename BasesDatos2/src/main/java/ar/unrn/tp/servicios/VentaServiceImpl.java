package ar.unrn.tp.servicios;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import ar.unrn.tp.api.VentaService;
import ar.unrn.tp.modelo.Carrito;
import ar.unrn.tp.modelo.Cliente;
import ar.unrn.tp.modelo.Producto;
import ar.unrn.tp.modelo.TarjetaCredito;
import ar.unrn.tp.modelo.Venta;

public class VentaServiceImpl implements VentaService {
	 @PersistenceContext
	  private EntityManager em;


	@Override
	@Transactional
	public void realizarVenta(Long idCliente, List<Long> productosIds, Long idTarjeta) {

		
		try {
			if(idCliente == null) {
				throw new IllegalArgumentException("El Cliente no puede ser nulo.");
			}
            if(productosIds.isEmpty()) {
            	throw new IllegalArgumentException("El Carrito no puede estar vacio.");
            }
            if(idTarjeta == null) {
            	throw new IllegalArgumentException("La Tarjeta no debe ser vacia.");
            }
          
            Cliente cliente = em.find(Cliente.class, idCliente);
            if (cliente==null) {
            	throw new IllegalArgumentException("El Cliente no esta registrado.");
            }
            TarjetaCredito tarjeta = em.find(TarjetaCredito.class, idTarjeta);
            if (tarjeta==null) {
            	throw new IllegalArgumentException("La Tarjeta no esta registrada.");
            }
            
            if (!cliente.perteneceTarjeta(tarjeta)) {
            	throw new IllegalArgumentException("La Tarjeta no pertenece al cliente.");
            }
			
           
            Carrito carrito = new Carrito(cliente);
            
            for (Long id : productosIds) {
				Producto producto = em.find(Producto.class, id);
				if (producto==null) {
					throw new IllegalArgumentException("El producto no esta registrado.");
				}
				carrito.agregarProducto(producto);
				
				
			}
            if (carrito.getProductos().isEmpty()) {
            	throw new IllegalArgumentException("La lista de productos es vacia.");
            }
            
            
            Venta venta = carrito.registrarVenta(tarjeta);
            
            em.persist(venta);
            
			
		} catch (Exception e) {
			 throw new RuntimeException(e);
		}
	}

	@Override
	@Transactional
	public BigDecimal calcularMonto(List<Long> productosIds, Long idTarjeta) {
		
		try {
			
            if(productosIds.isEmpty()) {
            	throw new IllegalArgumentException("El Carrito no puede estar vacio.");
            }
            if(idTarjeta == null) {
            	throw new IllegalArgumentException("La Tarjeta no debe ser vacia.");
            }
          
           
            TarjetaCredito tarjeta = em.find(TarjetaCredito.class, idTarjeta);
            if (tarjeta==null) {
            	throw new IllegalArgumentException("La Tarjeta no esta registrada.");
            }
            
            
			
           
            Carrito carrito = new Carrito();
            
            for (Long id : productosIds) {
				Producto producto = em.find(Producto.class, id);
				if (producto==null) {
					throw new IllegalArgumentException("El producto no esta registrado.");
				}
				carrito.agregarProducto(producto);
				
				
			}
            if (carrito.getProductos().isEmpty()) {
            	throw new IllegalArgumentException("La lista de productos es vacia.");
            }
            
            
            return carrito.calcularMontoDescuentoTarjeta(tarjeta, LocalDate.now());
            
			
		} catch (Exception e) {
			 throw new RuntimeException(e);
		}
		
		
	}

	@Override
	@Transactional
	public List<Venta> ventas() {
		
		try {
			
			TypedQuery<Venta> ventas = em.createQuery("SELECT v FROM Venta", Venta.class);
			
			return ventas.getResultList();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}	
		
	}

	@Override
	@Transactional
	public void crearVenta(Venta venta) {
		// TODO Auto-generated method stub

	}

	@Override
	@Transactional
	public void modificarVenta(Venta venta) {
		// TODO Auto-generated method stub

	}

	@Override
	@Transactional
	public void eliminarVenta(long IdVenta) {
		// TODO Auto-generated method stub

	}

	@Override
	@Transactional
	public List<Producto> listarProductos(Long idProducto) {
		// TODO Auto-generated method stub
		return null;
	}

}
