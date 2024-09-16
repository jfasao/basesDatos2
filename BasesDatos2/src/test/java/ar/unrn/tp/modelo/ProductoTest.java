package ar.unrn.tp.modelo;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;



public class ProductoTest {

	  @Test
	    public void testCrearProductoSinCategoria() {
	        assertThrows(IllegalArgumentException.class, () -> {
	            new Producto("001", "Producto sin categoría", null, new Marca("MarcaX", "Argentina"), new Precio(LocalDate.now(), null, new BigDecimal("100")), new ArrayList<>());
	        }, "Se esperaba una excepción porque la categoría es null.");
	    }

	    @Test
	    public void testCrearProductoSinDescripcion() {
	        Categoria categoria = new Categoria("Electrónica");
	        assertThrows(IllegalArgumentException.class, () -> {
	            new Producto("002", null, categoria, new Marca("MarcaY", "Brasil"), new Precio(LocalDate.now(), null, new BigDecimal("150")), new ArrayList<>());
	        }, "Se esperaba una excepción porque la descripción es null.");
	    }

	    @Test
	    public void testCrearProductoSinPrecio() {
	        Categoria categoria = new Categoria("Electrónica");
	        Marca marca = new Marca("MarcaZ", "España");
	        assertThrows(IllegalArgumentException.class, () -> {
	            new Producto("003", "Producto sin precio", categoria, marca, null, new ArrayList<>());
	        }, "Se esperaba una excepción porque el precio es null.");
	    }
}
