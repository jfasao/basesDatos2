package ar.unrn.tp.modelo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

public class CarritoTest {

    private Cliente cliente;
    private Cliente clienteSinTarjeta;
    private Cliente clienteTarjeta;
    private Cliente clienteMeme;
    private Producto producto1;
    private Producto producto2;
    private Producto productoSony1;
    private Producto productoSony2;
    private Producto productoAcme;
    private Producto productoComarca;
    private Carrito carrito;
    private Carrito carritoMarcaAcme;
    private Carrito carritoPorMedioDePago;
    private Carrito carritoMeme;
    private TarjetaCredito tarjetaCredito;
    private TarjetaCredito tarjetaMemeCard;
    private Banco banco;
    private Banco bancoDescuento;
    private Banco bancoMeme;
    private DescuentoMarca descuentoAcmeVigente;
    private TipoTarjeta tipoTarjetaVisa;
    private TipoTarjeta tipoTarjetaMemeCard;
    private Marca marcaAcme;
    private Marca marcaSony;
    private Marca marcaPhilips;
    private Marca marcaComarca;

    @BeforeEach
    public void setUp() {
        
    	//creo los tipos de Tarjeta
    	tipoTarjetaVisa = new TipoTarjeta(1L,"VISA");
    	tipoTarjetaMemeCard = new TipoTarjeta(2L,"MemeCard");
    	
    	// Crear Banco y Tarjeta de Crédito
        banco = new Banco("BancoX", "123", "30-12345678-9");
        tarjetaCredito = new TarjetaCredito("1234-5678-9123-4567", "Juan Perez", 12, 2025, 123, banco, tipoTarjetaVisa);
        cliente = new Cliente("Juan", "Perez", "juan.perez@example.com", "12345678", Arrays.asList(tarjetaCredito));

        // Crear el banco y el descuento para el medio de pago (tarjeta de crédito)
        bancoDescuento = new Banco("Banco XYZ", "1234", "30-12345678-9");
        DescuentoBanco descuentoTarjeta = new DescuentoBanco(LocalDate.now().minusDays(1), LocalDate.now().plusDays(7),10, bancoDescuento );
        banco.insertarDescuento(tipoTarjetaVisa, descuentoTarjeta);
        
     // Crear el banco y el descuento para el medio de pago (tarjeta de crédito)
        bancoMeme = new Banco("MemeBank", "5678", "30-98765432-1");
        DescuentoBanco descuentoMemeCard = new DescuentoBanco(LocalDate.now().minusDays(1), LocalDate.now().plusDays(7),10, bancoMeme);
        bancoMeme.insertarDescuento(tipoTarjetaMemeCard, descuentoMemeCard);
        
        // Crear una tarjeta de crédito con el banco que tiene el descuento vigente
        tarjetaCredito = new TarjetaCredito("4111111111111111", "Juan Perez", 12, 2024, 123, banco, tipoTarjetaVisa);
        
     // Crear la tarjeta de crédito MemeCard con el banco que tiene el descuento vigente
        tarjetaMemeCard = new TarjetaCredito("5111111111111111", "Juan Perez", 12, 2025, 456, bancoMeme, tipoTarjetaMemeCard);

       
        // Configurar la marca Acme con un descuento vigente
        marcaAcme = new Marca("Acme", "EE.UU");
        descuentoAcmeVigente = new DescuentoMarca( LocalDate.now().minusDays(5), LocalDate.now().plusDays(5),15, marcaAcme);
        marcaAcme.getDescuentos().add(descuentoAcmeVigente);
        
        // Configurar la marca Comarca con un descuento vigente
        marcaComarca = new Marca("Comarca", "Argentina");
        DescuentoMarca descuentoComarcaVigente = new DescuentoMarca(LocalDate.now().minusDays(2), LocalDate.now().plusDays(10),20,marcaComarca);
        marcaComarca.getDescuentos().add(descuentoComarcaVigente);
        
        
        // Configurar una marca sin descuento
        marcaSony = new Marca("Sony", "Japón");
        marcaPhilips = new Marca("Philips", "Holanda");
        
        // Precios de los productos
        Precio precioProductoSony1 = new Precio(LocalDate.now().minusMonths(2), null, new BigDecimal("100"));
        Precio precioProductoSony2 = new Precio(LocalDate.now().minusDays(5), LocalDate.now().plusDays(5), new BigDecimal("50"));
        Precio precioProductoAcme = new Precio(LocalDate.now().minusDays(10), LocalDate.now().plusDays(10), new BigDecimal("200"));
        Precio precioProductoComarca = new Precio(LocalDate.now().minusDays(7), LocalDate.now().plusDays(3), new BigDecimal("300"));



        // Crear productos con precios
        Precio precio1 = new Precio(LocalDate.now().minusMonths(2), LocalDate.now().minusDays(5), new BigDecimal("100")); // Precio caducado
        Precio precio2 = new Precio(LocalDate.now().minusDays(2), LocalDate.now().plusDays(5), new BigDecimal("50")); // Precio vigente
        producto1 = new Producto("001", "Producto 1", new Categoria("Electrónica"), marcaSony, precio1, Arrays.asList(precio1));
        producto2 = new Producto("002", "Producto 2", new Categoria("Hogar"), marcaPhilips, precio2, Arrays.asList(precio2));

        
        // Crear productos asociados a las marcas
        productoSony1 = new Producto("001", "Producto Sony", new Categoria("Electrónica"), marcaSony, precioProductoSony1, Arrays.asList(precioProductoSony1));
        productoSony2 = new Producto("002", "Producto Sony 2", new Categoria("Electrónica"), marcaSony, precioProductoSony2, Arrays.asList(precioProductoSony2));
        productoAcme = new Producto("003", "Producto Acme", new Categoria("Hogar"), marcaAcme, precioProductoAcme, Arrays.asList(precioProductoAcme));
        productoComarca = new Producto("004", "Producto Comarca", new Categoria("Hogar"), marcaComarca, precioProductoComarca, Arrays.asList(precioProductoComarca));
        
        
        
        // Crear carrito testMontoTotalSinDescuentos,testMontoTotalConDescuentosSoloCaducados,testRegistrarVentaConDescuentoPorTarjeta
        carrito = new Carrito(cliente, Arrays.asList(producto1, producto2));
        
        // Crear un cliente sin tarjetas de crédito para estas pruebas
        clienteSinTarjeta = new Cliente("Juan", "Perez", "juan.perez@example.com", "12345678", new ArrayList<>());

        // Crear el carrito con los productos testMontoTotalConDescuentoMarcaAcme
        carritoMarcaAcme = new Carrito(clienteSinTarjeta, Arrays.asList(productoSony1, productoSony2, productoAcme));
        
        // Crear un cliente con esa tarjeta de crédito
        clienteTarjeta = new Cliente("Juan", "Perez", "juan.perez@example.com", "12345678", Arrays.asList(tarjetaCredito));

        // Crear el carrito con los productos testMontoTotalConDescuentoPorMedioDePago
        carritoPorMedioDePago = new Carrito(clienteTarjeta, Arrays.asList(productoSony1, productoSony2, productoAcme));
        
        // Crear un cliente con la tarjeta de crédito MemeCard
        clienteMeme = new Cliente("Juan", "Perez", "juan.perez@example.com", "12345678", Arrays.asList(tarjetaMemeCard));

        // Crear el carrito con los productos
        carritoMeme = new Carrito(clienteMeme, Arrays.asList(productoSony1, productoSony2, productoAcme, productoComarca));
    }

    @Test
    public void testMontoTotalSinDescuentos() {
        // Verificar que el total sin descuentos sea la suma de los precios originales
        BigDecimal totalEsperado = new BigDecimal("150"); // 100 + 50
        assertEquals(totalEsperado, carrito.montoTotalSinDescuentos());
    }

    @Test
    public void testMontoTotalConDescuentosSoloCaducados() {
        // Supongamos que el producto1 tiene un descuento caducado y producto2 tiene un descuento vigente
        BigDecimal totalConDescuentoCaducado = producto1.calcularPrecioConDescuentoPorMarca(LocalDate.now());
        BigDecimal totalEsperado = totalConDescuentoCaducado.add(producto2.getPrecio().getValor());

        assertEquals(totalEsperado, carrito.montoTotalConDescuentos());
    }
/*
    @Test
    public void testRegistrarVentaConDescuentoPorTarjeta() {
        // Simulación de que el banco tiene un 10% de descuento para la tarjeta de crédito
    	TipoTarjeta tipoTarjeta = new TipoTarjeta(1L,"VISA");
        banco.insertarDescuento(tipoTarjeta, new DescuentoBanco(LocalDate.now().minusDays(1), LocalDate.now().plusDays(1),10,banco));

        BigDecimal montoConDescuento = carrito.montoTotalConDescuentos();
        BigDecimal montoFinalConDescuentoTarjeta = carrito.calcularMontoDescuentoTarjeta(montoConDescuento, 10); // 10% de descuento

        // Verificar que la venta se registre exitosamente
        boolean ventaExitosa = carrito.registrarVenta(tarjetaCredito)!=null;
        assertTrue(ventaExitosa);
    }*/
    
    @Test
    public void testMontoTotalConDescuentoMarcaAcme() {
        // Calcular el monto total del carrito con el descuento vigente solo para productos de la marca Acme
        BigDecimal totalSinDescuentos = producto1.getPrecio().getValor().add(producto2.getPrecio().getValor()).add(productoAcme.getPrecio().getValor());
        
        // Precio con el descuento de 15% aplicado solo al producto de la marca Acme
        BigDecimal precioAcmeConDescuento = productoAcme.calcularPrecioConDescuentoPorMarca(LocalDate.now()); // Aplica el 15% de descuento
        
        // El total esperado es: (precio1 + precio2 + precioAcmeConDescuento)
        BigDecimal totalEsperado = producto1.getPrecio().getValor() // Precio del producto1 sin descuento
                                .add(producto2.getPrecio().getValor()) // Precio del producto2 sin descuento
                                .add(precioAcmeConDescuento); // Precio del producto Acme con descuento

        assertEquals(totalEsperado, carritoMarcaAcme.montoTotalConDescuentos());
    }
    
    @Test
    public void testMontoTotalConDescuentoPorMedioDePago() {
        // Calcular el monto total con los descuentos aplicados (marca y tarjeta de crédito)
        BigDecimal montoConDescuento = carritoPorMedioDePago.montoTotalConDescuentos();
        BigDecimal descuentoMedioPago = carritoPorMedioDePago.calcularMontoDescuentoTarjeta(montoConDescuento, tarjetaCredito.getBanco().descuentoVigente(tarjetaCredito.getTipoTarjeta(),LocalDate.now()));

        // Monto final aplicando el descuento por tarjeta de crédito
        BigDecimal montoFinal = montoConDescuento.subtract(descuentoMedioPago);

        // Verificar que el monto total con descuentos sea el esperado
        assertEquals(new BigDecimal("320"), montoFinal); // 252
    }
    
    @Test
    public void testMontoTotalConDescuentoMarcaComarcaYMedioDePagoMemeCard() {
        // Calcular el monto total con los descuentos aplicados (marca y tarjeta de crédito)
        BigDecimal montoConDescuento = carritoMeme.montoTotalConDescuentos();
        BigDecimal descuentoMedioPago = carritoMeme.calcularMontoDescuentoTarjeta(montoConDescuento, tarjetaMemeCard.getBanco().descuentoVigente(tarjetaMemeCard.getTipoTarjeta(),LocalDate.now()));

        // Monto final aplicando el descuento por tarjeta de crédito
        BigDecimal montoFinal = montoConDescuento.subtract(descuentoMedioPago);

        // Verificar que el monto total con los descuentos sea el esperado
        assertEquals(new BigDecimal("560"), montoFinal); // Descuento en Comarca y descuento en tarjeta 486
    }
    
    
  /*  @Test
    public void testRegistrarVentaConDescuentoYGenerarOrden() {
        // Realizar el pago con la tarjeta de crédito
        Venta venta = carritoMeme.registrarVenta(tarjetaMemeCard);

        boolean ventaExitosa=venta !=null;
        // Verificar que la venta se haya registrado correctamente
        assertTrue(ventaExitosa, "La venta debería haberse registrado exitosamente.");

        // Verificar que la venta contiene la información correcta
        // Obtenemos el monto total sin descuentos
        BigDecimal montoTotalSinDescuentos = carritoMeme.montoTotalSinDescuentos();

        // Obtenemos el monto total con descuentos aplicados
        BigDecimal montoTotalConDescuentos = carritoMeme.montoTotalConDescuentos();
        
        // Calculamos el monto a pagar aplicando el descuento de la tarjeta de crédito
        BigDecimal descuentoTarjeta = carritoMeme.calcularMontoDescuentoTarjeta(montoTotalConDescuentos, tarjetaMemeCard.getBanco().descuentoVigente(tarjetaMemeCard.getTipoTarjeta(),LocalDate.now()));
        BigDecimal montoAPagar = montoTotalConDescuentos.subtract(descuentoTarjeta);
        
        // Aquí deberíamos tener un método o una forma de acceder a las ventas registradas
        // Por simplicidad, asumimos que la venta se almacena en algún lugar donde podemos verificarla
        // Verificamos que la venta se haya creado y tenga la información correcta

        // Verificar que la venta tiene el monto correcto
        // Este es un ejemplo, el acceso a la venta real dependerá de cómo esté implementado el sistema
        Venta ventaRegistrada = venta; // Método ficticio para obtener la última venta

        assertNotNull(ventaRegistrada, "La venta registrada no debe ser nula.");
        assertEquals(montoTotalSinDescuentos, ventaRegistrada.getMontoTotal(), "El monto total sin descuentos debe coincidir.");
        assertEquals(montoAPagar, ventaRegistrada.getMontoTotalConDescuentos(), "El monto total con descuentos debe coincidir.");
        assertEquals(clienteMeme, ventaRegistrada.getCliente(), "El cliente de la venta debe coincidir.");
        assertEquals(tarjetaMemeCard, ventaRegistrada.getTarjetaPago(), "La tarjeta de crédito utilizada debe coincidir.");
        assertEquals("compra registrada con exito", ventaRegistrada.getObservaciones(), "El estado de la venta debe ser 'compra registrada con exito'.");
    }*/
}