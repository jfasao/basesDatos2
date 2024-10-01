package ar.unrn.tp.modelo;


import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

/**
 * 
 * Materia: Bases de Datos 2
 * 
 * @author Facundo Alcade
 * @version 1.1
 *
 */

@Entity
public class TarjetaCredito {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String numero;
	private String titular;
	private int mesVencimiento;
	private int anioVencimiento;
	private int cvc;
	
	@ManyToOne
	@JoinColumn(name = "banco_id")
	private Banco banco;
	
	@ManyToMany
	@JoinTable(
		        name = "tarjeta_tipo_tarjeta", // Nombre de la tabla intermedia
		        joinColumns = @JoinColumn(name = "tarjeta_credito_id"), // Clave foránea hacia TarjetaCredito
		        inverseJoinColumns = @JoinColumn(name = "tipo_tarjeta_id") // Clave foránea hacia TipoTarjeta
		    )
	private TipoTarjeta tipoTarjeta;
	
	 public int descuentoVigente(LocalDate fecha) {
		 return  banco.getDescuentos().stream()
	                         .filter(descuento -> descuento.descuentoVigente(fecha)) // Filtrar descuentos vigentes
                             .map(Descuento::getPorcentajeDescuento) // Obtener el porcentaje de descuento
                             .findFirst()// Tomar el primer descuento válido
	        				.orElse(0); 
		 
	    }

	
	
	 public boolean tarjetaValida(LocalDate fecha) {
		 
		 return mesVencimiento >=fecha.getMonthValue() && anioVencimiento>=fecha.getYear();
		 
	 }
	//constructors
	public TarjetaCredito() {
		// TODO Auto-generated constructor stub
	}

	

	public TarjetaCredito(String numero, String titular, Integer mesVencimiento, Integer anioVencimiento, Integer cvc,
			Banco banco, TipoTarjeta tipoTarjeta) {
		super();
		this.numero = numero;
		this.titular = titular;
		this.mesVencimiento = mesVencimiento;
		this.anioVencimiento = anioVencimiento;
		this.cvc = cvc;
		this.banco = banco;
		this.tipoTarjeta = tipoTarjeta;
	}



	//getters and setters
	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getTitular() {
		return titular;
	}

	public void setTitular(String titular) {
		this.titular = titular;
	}

	public Integer getMesVencimiento() {
		return mesVencimiento;
	}

	public void setMesVencimiento(Integer mesVencimiento) {
		this.mesVencimiento = mesVencimiento;
	}

	public Integer getAnioVencimiento() {
		return anioVencimiento;
	}

	public void setAnioVencimiento(Integer anioVencimiento) {
		this.anioVencimiento = anioVencimiento;
	}

	public Integer getCvc() {
		return cvc;
	}

	public void setCvc(Integer cvc) {
		this.cvc = cvc;
	}

	public Banco getBanco() {
		return banco;
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
	}



	protected TipoTarjeta getTipoTarjeta() {
		return tipoTarjeta;
	}



	protected void setTipoTarjeta(TipoTarjeta tipoTarjeta) {
		this.tipoTarjeta = tipoTarjeta;
	}



	protected Long getId() {
		return id;
	}



	protected void setId(Long id) {
		this.id = id;
	}



	protected void setMesVencimiento(int mesVencimiento) {
		this.mesVencimiento = mesVencimiento;
	}



	protected void setAnioVencimiento(int anioVencimiento) {
		this.anioVencimiento = anioVencimiento;
	}



	protected void setCvc(int cvc) {
		this.cvc = cvc;
	}
	
	
	@Override
	public boolean equals(Object o) {
	    if (this == o) return true;
	    if (o == null || getClass() != o.getClass()) return false;

	    TarjetaCredito that = (TarjetaCredito) o;

	    if (mesVencimiento != that.mesVencimiento) return false;
	    if (anioVencimiento != that.anioVencimiento) return false;
	    if (cvc != that.cvc) return false;
	    if (!numero.equals(that.numero)) return false;
	    if (!titular.equals(that.titular)) return false;
	    if (!banco.equals(that.banco)) return false;
	    return tipoTarjeta.equals(that.tipoTarjeta);
	}

	@Override
	public int hashCode() {
	    int result = numero.hashCode();
	    result = 31 * result + titular.hashCode();
	    result = 31 * result + mesVencimiento;
	    result = 31 * result + anioVencimiento;
	    result = 31 * result + cvc;
	    result = 31 * result + banco.hashCode();
	    result = 31 * result + tipoTarjeta.hashCode();
	    return result;
	}
	
	// Método para convertir a Map
    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("numero", numero);
        map.put("titular", titular);
        map.put("mesVencimiento", mesVencimiento);
        map.put("anioVencimiento", anioVencimiento);
        map.put("cvc", cvc);
        map.put("banco", banco != null ? banco.toMap() : null); // Convertir a Map si no es nulo
        map.put("tipoTarjeta", tipoTarjeta != null ? tipoTarjeta.toMap() : null);
        return map;
    }
}
