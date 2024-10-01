package ar.unrn.tp.servicios;

import java.util.List;
import javax.jdo.annotations.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import ar.unrn.tp.api.ClienteService;
import ar.unrn.tp.modelo.Cliente;
import ar.unrn.tp.modelo.TarjetaCredito;
import ar.unrn.tp.modelo.TipoTarjeta;
import ar.unrn.tp.modelo.Banco;

public class ClienteServiceImpl implements ClienteService {

	
	 @PersistenceContext
	  private EntityManager em;

	
	@Override
	@Transactional
	public void crearCliente(String nombre, String apellido, String dni, String email) {
		
		try {
			
		
		TypedQuery<Cliente> query = em.createQuery("SELECT c FROM Cliente c where c.dni ='"+dni+"'", Cliente.class);
		
		if (!query.getResultList().isEmpty()) {
			throw new IllegalArgumentException("El cliente ya se encuentra registrado");
		}
		
		Cliente cliente = new Cliente (nombre, apellido,  email, dni);
		
		
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
	}

	@Override
	@Transactional
	public void modificarCliente(Long idCliente, String nombre, String apellido, String dni, String email) {
		try {
			Cliente cliente = em.find(Cliente.class, idCliente);
			//verifico que el cliente exista
			if (cliente==null) {
				throw new IllegalArgumentException("El cliente con id: "+idCliente+" no existe!");
			}
			
			cliente.setNombre(nombre);
			cliente.setApellido(apellido);
			cliente.setDni(dni);
			cliente.setEmail(email);
			
			em.merge(cliente);
			
					
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		

	}

	@Override
	@Transactional
	public void agregarTarjeta(Long idCliente, String nro, String titular, int mesVencimiento, int anioVencimiento,
			int cvc, Long idBanco, Long idTipoTarjeta) {
			
		try {
			Cliente cliente = em.find(Cliente.class, idCliente);
			
			if (cliente==null) {
				throw new IllegalArgumentException("El cliente con id: "+idCliente+" no existe!");
			}
			TipoTarjeta tipoTarjeta = em.find(TipoTarjeta.class, idTipoTarjeta);
			
			if (tipoTarjeta==null) {
				throw new IllegalArgumentException("El Tipo de Tarjeta con id: "+idTipoTarjeta+" no existe!");
			}
			
			Banco banco = em.find(Banco.class, idBanco);
			
			if (banco==null) {
				throw new IllegalArgumentException("El banco con id: "+idBanco+" no existe!");
			}
			
			 TarjetaCredito tarjeta= new TarjetaCredito(nro,  titular,  mesVencimiento,  anioVencimiento,
						 cvc, banco,  tipoTarjeta);
			 
			 cliente.agregarTarjeta(tarjeta);
			 
			 em.merge(cliente);
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}


	}

	

	@Override
	@Transactional
	public List<TarjetaCredito> listarTarjetas(Long idCliente) {
		
		try {
			Cliente cliente = em.find(Cliente.class, idCliente);
			
			if (cliente==null) {
				throw new IllegalArgumentException("El cliente con id: "+idCliente+" no existe!");
			}
			
			return cliente.getTarjetasCredito();
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		
	}
	
	
	
	@Override
	@Transactional
	public void crearCliente(Cliente Cliente) {
		// TODO Auto-generated method stub

	}

	@Override
	@Transactional
	public void modificarCliente(Cliente cliente) {
		// TODO Auto-generated method stub

	}

}
