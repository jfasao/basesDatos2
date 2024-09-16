package ar.unrn.tp.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;

/**
*
* Materia: Bases de Datos 2
* 
* @author Facundo Alcalde
* @version 1.0
*
*/

@Entity
public class Producto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String codigo;
	private String descripcion;
	
	@ManyToOne
	@JoinColumn(name="categoria_id")
	private Categoria categoria;
	@ManyToOne
	@JoinColumn(name="marca_id")
	private Marca marca;
	
	@OneToMany
	@MapsId
	private Precio precio;
	
	@OneToMany(cascade = CascadeType.PERSIST)
	private List<Precio> historialPrecios = new ArrayList<Precio>();
	
	//cosntructors
	public Producto() {
		// TODO Auto-generated constructor stub
	}

	public Producto(String codigo, String descripcion, Categoria categoria, Marca marca, Precio precio,
			List<Precio> historialPrecios) {
		super();
		if (categoria == null) {
		        throw new IllegalArgumentException("La categoría no puede ser null.");
		}
		if (marca == null) {
	        throw new IllegalArgumentException("La marca no puede ser null.");
		}
		if (precio == null) {
	        throw new IllegalArgumentException("La precio no puede ser null.");
		}
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.categoria = categoria;
		this.marca = marca;
		this.precio = precio;
		this.historialPrecios = historialPrecios;
	}
	
	public void cambiarPrecio(Precio precio) {
		
		if (this.getPrecio().precioVigente(precio)) {
			this.getHistorialPrecios().add(precio);
			this.setPrecio(precio);
		}
	}
	
	 // Método para calcular el precio con descuento vigente
    public BigDecimal calcularPrecioConDescuentoPorMarca(LocalDate fecha) {
        return marca.getDescuentos().stream()
                         .filter(descuento -> descuento.descuentoVigente(fecha)) // Filtrar descuentos vigentes
                         .map(descuento -> aplicarDescuento(precio.getValor(), descuento.getPorcentajeDescuento())) // Aplicar descuentos
                         .findFirst() // Tomar el primer descuento válido
                         .orElse(precio.getValor()); // Si no hay descuentos vigentes, retorna el precio original
    }

    // Método auxiliar para aplicar el descuento
    private BigDecimal aplicarDescuento(BigDecimal precio, int porcentajeDescuento) {
        BigDecimal descuento = precio.multiply(BigDecimal.valueOf(porcentajeDescuento)).divide(BigDecimal.valueOf(100));
        return precio.subtract(descuento);
    }


	//getters and setters
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public Precio getPrecio() {
		return precio;
	}

	public void setPrecio(Precio precio) {
		this.precio = precio;
	}

	public List<Precio> getHistorialPrecios() {
		return historialPrecios;
	}

	public void setHistorialPrecios(List<Precio> historialPrecios) {
		this.historialPrecios = historialPrecios;
	}

	
	
}
