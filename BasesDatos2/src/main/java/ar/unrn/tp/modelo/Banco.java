package ar.unrn.tp.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;

/**
 *  Materia: Bases de Datos 2
 *  
 * @author Facundo Alcade
 * @version 1.0
 * 
 */
@Entity
public class Banco  {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nombre;
	private String codigo;
	private String cuit;
	@OneToMany(mappedBy = "banco", cascade = CascadeType.ALL)
	private List<DescuentoBanco> descuentos= new ArrayList<DescuentoBanco>();
	@OneToMany(mappedBy = "banco",cascade = CascadeType.ALL)
	private List<TarjetaCredito> tarjetas = new ArrayList<TarjetaCredito>();
	 @OneToMany
	 @MapKey(name = "tipoTarjeta")
	private Map<TipoTarjeta,List<DescuentoBanco>> descuentosPorTipoTarjeta= new HashMap<>();
	
	
	
//Devuelve el descuento vigente paa un tipo de tarjeta	
public int descuentoVigente(TipoTarjeta tipoTarjeta, LocalDate fecha) {
	return Optional.ofNullable(descuentosPorTipoTarjeta.get(tipoTarjeta))
            .orElse(List.of()) // Si no hay descuentos, devolver una lista vacía
            .stream()
            .filter(descuento -> descuento.descuentoVigente(fecha)) // Filtrar descuentos vigentes
            .map(DescuentoBanco::getPorcentajeDescuento) // Obtener el porcentaje de descuento
            .findFirst() // Tomar el primer descuento válido
            .orElse(0); // Si no hay descuentos vigentes, devolver 0
	
}

//Inserta un descuento en la lista de descuentos del tipo de tarjeta, si no esta solapado o la lista es vacia
public boolean insertarDescuento(TipoTarjeta tipoTarjeta, DescuentoBanco nuevoDescuento) {
	// Obtener la lista de descuentos para el tipo de tarjeta
	List<DescuentoBanco> descuentosExistentes = descuentosPorTipoTarjeta.getOrDefault(tipoTarjeta, new ArrayList<>());

	// Verificar si la lista de descuentos existentes es nula o está vacía
	boolean solapado = false;

	if (descuentosExistentes != null && !descuentosExistentes.isEmpty()) {
	    // Verificar si el nuevo descuento solapa con algún descuento existente
	    solapado = descuentosExistentes.stream()
	        .anyMatch(descuentoExistente -> descuentoExistente.descuentoSolapado(nuevoDescuento));
	}

	// Si no está solapado, agregarlo a la lista
	if (!solapado) {
	    descuentosExistentes.add(nuevoDescuento);
	    descuentosPorTipoTarjeta.put(tipoTarjeta, descuentosExistentes);
	}

	return !solapado;

    }

	public boolean saldoTarjeta(TarjetaCredito tarjeta, BigDecimal montoPagar) {
		boolean salida=false;
		
		if (tarjetas.contains(tarjeta)) {
			//llamar web service saldo en cuenta para el monto a pagar
			salida=true;
		}
		return salida;
		
	}

	//constructors
	public Banco() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Banco(String nombre, String codigo, String cuit) {
		super();
		this.nombre=nombre;
		this.codigo=codigo;
		this.cuit=cuit;
		
		
	}

	

	public Banco(String nombre, String codigo, String cuit, List<DescuentoBanco> descuentos,
			List<TarjetaCredito> tarjetas, Map<TipoTarjeta, List<DescuentoBanco>> descuentosPorTipoTarjeta) {
		super();
		this.nombre = nombre;
		this.codigo = codigo;
		this.cuit = cuit;
		this.descuentos = descuentos;
		this.tarjetas = tarjetas;
		this.descuentosPorTipoTarjeta = descuentosPorTipoTarjeta;
	}


	public Banco(String nombre, String codigo, String cuit, List<DescuentoBanco> descuento) {
		super();
		nombre = nombre;
		codigo = codigo;
		cuit = cuit;
		this.descuentos = descuento;
	}


	// getters and setters
	protected String getNombre() {
		return nombre;
	}
	
	protected void setNombre(String nombre) {
		nombre = nombre;
	}
	protected String getCodigo() {
		return codigo;
	}
	protected void setCodigo(String codigo) {
		codigo = codigo;
	}
	protected String getCuit() {
		return cuit;
	}
	protected void setCuit(String cuit) {
		cuit = cuit;
	}


	

	public List<TarjetaCredito> getTarjetas() {
		return tarjetas;
	}


	protected void setTarjetas(List<TarjetaCredito> tarjetas) {
		this.tarjetas = tarjetas;
	}


	public List<DescuentoBanco> getDescuentos() {
		return descuentos;
	}


	protected void setDescuentos(List<DescuentoBanco> descuentos) {
		this.descuentos = descuentos;
	}


	public Map<TipoTarjeta, List<DescuentoBanco>> getDescuentosPorTipoTarjeta() {
		return descuentosPorTipoTarjeta;
	}


	public void setDescuentosPorTipoTarjeta(Map<TipoTarjeta, List<DescuentoBanco>> descuentosPorTipoTarjeta) {
		this.descuentosPorTipoTarjeta = descuentosPorTipoTarjeta;
	}

	public Long getId() {
		return id;
	}

	protected void setId(Long id) {
		this.id = id;
	}
	
	@Override
	public boolean equals(Object o) {
	    if (this == o) return true;
	    if (o == null || getClass() != o.getClass()) return false;

	    Banco banco = (Banco) o;

	    if (!id.equals(banco.id)) return false;
	    if (!codigo.equals(banco.codigo)) return false;
	    return cuit.equals(banco.cuit);
	}

	@Override
	public int hashCode() {
	    int result = id.hashCode();
	    result = 31 * result + codigo.hashCode();
	    result = 31 * result + cuit.hashCode();
	    return result;
	}
	
	  // Método para convertir a Map
    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("nombre", nombre);
        map.put("codigo", codigo);
        map.put("cuit", cuit);
        return map;
    }

}
