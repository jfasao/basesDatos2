package ar.unrn.tp.modelo;

import java.util.ArrayList;
import java.util.List;

public class TipoTarjeta {
	
	private String descripcion;
	private List<TarjetaCredito> tarjetas = new ArrayList<TarjetaCredito>();
	private List<Banco> bancos = new ArrayList<Banco>();

	public TipoTarjeta() {
		// TODO Auto-generated constructor stub
	}
	
	

	public TipoTarjeta(String descripcion) {
		super();
		this.descripcion = descripcion;
	}



	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<TarjetaCredito> getTarjetas() {
		return tarjetas;
	}

	public void setTarjetas(List<TarjetaCredito> tarjetas) {
		this.tarjetas = tarjetas;
	}

	public List<Banco> getBancos() {
		return bancos;
	}

	public void setBancos(List<Banco> bancos) {
		this.bancos = bancos;
	}

	
}
