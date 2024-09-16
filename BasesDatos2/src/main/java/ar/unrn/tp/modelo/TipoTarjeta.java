package ar.unrn.tp.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;


@Entity
public class TipoTarjeta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String descripcion;
	
	@ManyToMany(mappedBy = "tiposTarjeta")
	private List<TarjetaCredito> tarjetas = new ArrayList<TarjetaCredito>();
	

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



	protected Long getId() {
		return id;
	}



	protected void setId(Long id) {
		this.id = id;
	}

	
}
