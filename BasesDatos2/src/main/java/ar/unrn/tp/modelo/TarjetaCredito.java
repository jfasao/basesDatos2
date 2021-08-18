package ar.unrn.tp.modelo;

/**
 * 
 * Materia: Bases de Datos 2
 * 
 * @author Facundo Alcade
 * @version 1.0
 *
 */
public class TarjetaCredito {

	private String numero;
	private String titular;
	private Integer mesVencimiento;
	private Integer anioVencimiento;
	private Integer cvc;
	private Banco banco;
	
	
	
	//constructors
	public TarjetaCredito() {
		// TODO Auto-generated constructor stub
	}

	public TarjetaCredito(String numero, String titular, Integer mesVencimiento, Integer anioVencimiento, Integer cvc,
			Banco banco) {
		super();
		this.numero = numero;
		this.titular = titular;
		this.mesVencimiento = mesVencimiento;
		this.anioVencimiento = anioVencimiento;
		this.cvc = cvc;
		this.banco = banco;
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
	
	
	

}
