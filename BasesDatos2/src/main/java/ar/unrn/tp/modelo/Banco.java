package ar.unrn.tp.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 *  Materia: Bases de Datos 2
 *  
 * @author Facundo Alcade
 * @version 1.0
 * 
 */

public class Banco  {
	
	private String Nombre;
	private String Codigo;
	private String Cuit;
	private List<DescuentoBanco> descuentos= new ArrayList<DescuentoBanco>();
	private List<TarjetaCredito> tarjetas = new ArrayList<TarjetaCredito>();
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

    // Verificar si el nuevo descuento solapa con algún descuento existente
    boolean solapado = descuentosExistentes.stream()
        .anyMatch(descuentoExistente -> descuentoExistente.descuentoSolapado(nuevoDescuento));

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
		Nombre=nombre;
		Codigo=codigo;
		Cuit=cuit;
		
		
	}

	

	public Banco(String nombre, String codigo, String cuit, List<DescuentoBanco> descuentos,
			List<TarjetaCredito> tarjetas, Map<TipoTarjeta, List<DescuentoBanco>> descuentosPorTipoTarjeta) {
		super();
		Nombre = nombre;
		Codigo = codigo;
		Cuit = cuit;
		this.descuentos = descuentos;
		this.tarjetas = tarjetas;
		this.descuentosPorTipoTarjeta = descuentosPorTipoTarjeta;
	}


	public Banco(String nombre, String codigo, String cuit, List<DescuentoBanco> descuento) {
		super();
		Nombre = nombre;
		Codigo = codigo;
		Cuit = cuit;
		this.descuentos = descuento;
	}


	// getters and setters
	protected String getNombre() {
		return Nombre;
	}
	
	protected void setNombre(String nombre) {
		Nombre = nombre;
	}
	protected String getCodigo() {
		return Codigo;
	}
	protected void setCodigo(String codigo) {
		Codigo = codigo;
	}
	protected String getCuit() {
		return Cuit;
	}
	protected void setCuit(String cuit) {
		Cuit = cuit;
	}


	

	protected List<TarjetaCredito> getTarjetas() {
		return tarjetas;
	}


	protected void setTarjetas(List<TarjetaCredito> tarjetas) {
		this.tarjetas = tarjetas;
	}


	protected List<DescuentoBanco> getDescuentos() {
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
	
	

}
