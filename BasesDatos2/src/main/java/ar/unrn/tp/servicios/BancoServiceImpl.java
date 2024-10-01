package ar.unrn.tp.servicios;

import java.util.List;

import javax.jdo.annotations.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ar.unrn.tp.api.BancoService;
import ar.unrn.tp.modelo.Banco;
import ar.unrn.tp.modelo.DescuentoBanco;
import ar.unrn.tp.modelo.TarjetaCredito;
import ar.unrn.tp.modelo.TipoTarjeta;

public class BancoServiceImpl implements BancoService {
	
	 @PersistenceContext
	    private EntityManager em;
	
	@Override
	@Transactional
	public void crearBanco(Banco banco) {
		em.persist(banco);
	}

	@Override
	@Transactional
	public void modificarBanco(Banco banco) {
		Banco  banco2 = em.find(Banco.class, banco.getId());
        if (banco == null) throw new IllegalArgumentException("Banco no encontrado.");
        if (!banco2.equals(banco)) {
        	em.merge(banco);
        }
	}

	

	@Override
	@Transactional
	public List<TarjetaCredito> listarTarjetas(Long idBanco) {
		Banco  banco = em.find(Banco.class, idBanco);
        if (banco == null) throw new IllegalArgumentException("Banco no encontrado.");
        return banco.getTarjetas();
        
	}

	@Override
	@Transactional
	public List<DescuentoBanco> descuentos(long idBanco) {
		Banco  banco = em.find(Banco.class, idBanco);
        if (banco == null) throw new IllegalArgumentException("Banco no encontrado.");
        return banco.getDescuentos();
	}

	@Override
	@Transactional
	public List<DescuentoBanco> descuentosPorTipoTarjeta(TipoTarjeta tipotarjeta, long idBanco) {
		Banco  banco = em.find(Banco.class, idBanco);
        if (banco == null) throw new IllegalArgumentException("Banco no encontrado.");
        return banco.getDescuentosPorTipoTarjeta().get(tipotarjeta);
	}

}
