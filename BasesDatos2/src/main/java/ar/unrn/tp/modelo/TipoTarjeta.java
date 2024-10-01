package ar.unrn.tp.modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	public TipoTarjeta(Long id,String descripcion) {
		super();
		this.id=id;
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

	@Override
	public boolean equals(Object o) {
	    if (this == o) return true;
	    if (o == null || getClass() != o.getClass()) return false;

	    TipoTarjeta that = (TipoTarjeta) o;

	    if (!id.equals(that.id)) return false;
	    return descripcion.equals(that.descripcion);
	}

	@Override
	public int hashCode() {
	    int result = id.hashCode();
	    result = 31 * result + descripcion.hashCode();
	    return result;
	}
	
	  // Método para convertir a Map
    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("descripcion", descripcion);
        return map;
    }
	
}
