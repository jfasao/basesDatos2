package ar.unrn.tp.servicios;

import java.time.LocalDate;

import javax.jdo.annotations.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ar.unrn.tp.api.DescuentosService;
import ar.unrn.tp.modelo.Banco;
import ar.unrn.tp.modelo.DescuentoBanco;
import ar.unrn.tp.modelo.DescuentoMarca;
import ar.unrn.tp.modelo.Marca;

public class DescuentosServiceImpl implements DescuentosService {

	  @PersistenceContext
	  private EntityManager em;


	@Override
	@Transactional
	public void crearDescuentoSobreTotal(LocalDate fechaDesde, LocalDate fechaHasta, int porcentaje, Long IdBanco) {
		try {
			Banco banco = em.find(Banco.class, IdBanco);
			if (banco==null) {
				throw new IllegalArgumentException("Banco no encontrado.");
			}
			DescuentoBanco descuento = new DescuentoBanco(fechaDesde, fechaHasta, porcentaje, banco);
			
			em.persist(descuento);
			
			
		} catch (Exception e) {
			 throw new RuntimeException(e);
		}

	}

	@Override
	@Transactional
	public void crearDescuento(LocalDate fechaDesde, LocalDate fechaHasta, int porcentaje, Long idMarca) {
		
		
		try {
			Marca marca= em.find(Marca.class, idMarca);
			
			if (marca==null) {
				throw new IllegalArgumentException("Marca no encontrada.");
			}
			DescuentoMarca descuento = new DescuentoMarca(fechaDesde, fechaHasta, porcentaje, marca);
			
			em.persist(descuento);
			
		} catch (Exception e) {
			 throw new RuntimeException(e);
		}
		
	}

}
