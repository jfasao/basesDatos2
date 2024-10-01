package ar.unrn.tp.servicios;

import java.util.List;

import javax.jdo.annotations.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import ar.unrn.tp.api.CategoriaService;
import ar.unrn.tp.modelo.Categoria;

public class CategoriaServiceImpl implements CategoriaService {

	 @PersistenceContext
	  private EntityManager em;

	@Override
	@Transactional
	public void crearCategoria(Categoria categoria) {
		em.persist(categoria);
	}

	@Override
	@Transactional
	public void modificarCategoria(Categoria categoria) {
		Categoria  categoria2 = em.find(Categoria.class, categoria.getId());
        if (categoria == null) throw new IllegalArgumentException("categoria no encontrado.");
        if (categoria2.equals(categoria)) {
        	em.merge(categoria);
        }
	}

	@Override
	@Transactional
	public void eliminarCategoria(long idCtagoria) {
		Categoria categoria =em.find(Categoria.class, idCtagoria);
		em.remove(categoria);
	}

	@Override
	 @Transactional
	public List<Categoria> listarCategorias() {
		TypedQuery<Categoria> query = em.createQuery("SELECT c FROM Categoria c", Categoria.class);
        return query.getResultList();
	}

}
