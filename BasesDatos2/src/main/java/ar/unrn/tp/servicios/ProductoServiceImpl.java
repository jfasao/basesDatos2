package ar.unrn.tp.servicios;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.jdo.annotations.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import ar.unrn.tp.api.ProductoService;
import ar.unrn.tp.modelo.Categoria;
import ar.unrn.tp.modelo.Marca;
import ar.unrn.tp.modelo.Precio;
import ar.unrn.tp.modelo.Producto;

public class ProductoServiceImpl implements ProductoService {

	 @PersistenceContext
	  private EntityManager em;

	
	@Override
	@Transactional
	public void crearProducto(String codigo, String descripcion, Long idCategoria, Long idMarca, BigDecimal valor) {
		try {
			Categoria categoria = em.find(Categoria.class, idCategoria);
			
			if (categoria==null) {
				throw new IllegalArgumentException("La categoria con id: "+idCategoria+" no existe!");
			}
			
			Marca marca =em.find(Marca.class, idMarca);
			if (categoria==null) {
				throw new IllegalArgumentException("La marca con id: "+idMarca+" no existe!");
			}

			TypedQuery<Producto> productos = em.createQuery("SELECT p FROM Producto p where p.codigo = :codigo", Producto.class);
			productos.setParameter("codigo", codigo);
			if (!productos.getResultList().isEmpty()) {
				throw new IllegalArgumentException("El codigo ya se encuentra registrado");
			}
			
			Precio precio = new Precio(LocalDate.now(), null, valor);
			
			Producto producto = new Producto(codigo, descripcion, categoria, marca, precio);
			
			em.persist(producto);
			
		} catch (Exception e) {
			throw new javax.persistence.PersistenceException(e);
		}
		
		
		
	}

	@Override
	@Transactional
	public void modificarProducto(Long idProducto, String codigo, String descripcion, Long idCategoria, Long idMarca, BigDecimal valor) {
		
		try {
			 TypedQuery<Producto> produc= em.createQuery("select p from Producto p where p.id=:id", Producto.class);
			 produc.setParameter("id", idProducto);
			 List<Producto> productos = produc.getResultList();
			 if (productos.isEmpty()) {
				 throw new IllegalArgumentException("El Producto id: "+idProducto+" no se encuentra registrado");
			 }
			 Producto producto = productos.getFirst();
			 
			 //cambio el codigo del producto verifico que no haya otro con ese codigo
			 if (!producto.getCodigo().equals(codigo)) {
				 
				 TypedQuery<Producto> produc2 = em.createQuery("SELECT p FROM Producto p where p.id <> :id and  p.codigo = :codigo ", Producto.class);
				 produc2.setParameter("id", idProducto);
				 produc2.setParameter("codigo", codigo);
					if (!produc2.getResultList().isEmpty()) {
						throw new IllegalArgumentException("El codigo ya se encuentra registrado");
					}	 
			 }
			 
			 Categoria categoria = em.find(Categoria.class, idCategoria);
				
				if (categoria==null) {
					throw new IllegalArgumentException("La categoria con id: "+idCategoria+" no existe!");
				}
				
				Marca marca =em.find(Marca.class, idMarca);
				if (categoria==null) {
					throw new IllegalArgumentException("La marca con id: "+idMarca+" no existe!");
				}
				
				Precio precio = new Precio(LocalDate.now(), null, valor);
				
				producto.setCodigo(codigo);
				producto.setDescripcion(descripcion);
				producto.setCategoria(categoria);
				producto.setMarca(marca);
				producto.cambiarPrecio(precio);
				
				em.merge(producto);		 
			 
			
		} catch (Exception e) {
			throw new javax.persistence.PersistenceException(e);
		}

	}

	@Override
	@Transactional
	public List<Producto> listarProductos() {
		List<Producto> productos ;
		try {
			
			 TypedQuery<Producto> product = em.createQuery("select p from Producto p", Producto.class);
	            productos = product.getResultList();
	            if (productos.isEmpty()) {
	                throw new RuntimeException("No hay productos registrados.");
	            }
	            
	            return productos;

			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		

	}

	@Override
	@Transactional
	public void crearProducto(Producto producto) {
		// TODO Auto-generated method stub

	}

	@Override
	@Transactional
	public void modificarProducto(Producto producto) {
		// TODO Auto-generated method stub

	}

	@Override
	@Transactional
	public void eliminarProducto(long IdProducto) {
		try {
			
		
		Producto producto = em.find(Producto.class, IdProducto);
		if (producto!=null)
		em.remove(producto);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	@Transactional
	public List<Precio> listarPrecios(Long idProducto) {
		// TODO Auto-generated method stub
		return null;
	}

}
