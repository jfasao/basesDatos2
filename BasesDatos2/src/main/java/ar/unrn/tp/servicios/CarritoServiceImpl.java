package ar.unrn.tp.servicios;

import java.util.List;

import javax.jdo.annotations.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ar.unrn.tp.api.CarritoService;
import ar.unrn.tp.modelo.Carrito;
import ar.unrn.tp.modelo.Producto;

public class CarritoServiceImpl implements CarritoService {

	 @PersistenceContext
	  private EntityManager em;


	@Override
	@Transactional
	public void crearCarrito(Carrito carrito) {
		em.persist(carrito);

	}

	@Override
	@Transactional
	public void modificarCarrito(Carrito carrito) {
		Carrito  carrito2 = em.find(Carrito.class, carrito.getId());
        if (carrito == null) throw new IllegalArgumentException("Carrito no encontrado.");
        if (carrito2.equals(carrito)) {
        	em.merge(carrito);
        }
	}

	

	@Override
	@Transactional
	public void eliminarCarrito(Long idCarrito) {
		Carrito carrito = em.find(Carrito.class, idCarrito);
		em.remove(carrito);
	}

	@Override
	@Transactional
	public List<Producto> listarProductos(Long idCarrito) {
		Carrito carrito = em.find(Carrito.class, idCarrito);
		return carrito.getProductos();
	}

}
