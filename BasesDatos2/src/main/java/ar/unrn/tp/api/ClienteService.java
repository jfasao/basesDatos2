package ar.unrn.tp.api;

import java.util.List;

import ar.unrn.tp.modelo.Cliente;
import ar.unrn.tp.modelo.TarjetaCredito;


public interface ClienteService {
	


	// validar que el dni no se repita
	void crearCliente(String nombre, String apellido, String dni, String email);
	
	// validar que sea un cliente existente
	void modificarCliente(Long idCliente,String nombre, String apellido, String dni, String email);
	
	// validar que sea un cliente existente
	void agregarTarjeta(Long idCliente, String nro, String titular,int mesVencimiento, int anioVencimiento, int cvc, Long idBanco, Long idTipoTarjeta);
	
	// 
	void crearCliente(Cliente Cliente);
	//
	void modificarCliente(Cliente cliente);
	
	 //Devuelve las tarjetas de un Cliente específico
	 List<TarjetaCredito> listarTarjetas(Long idCliente);
	 
	

}
