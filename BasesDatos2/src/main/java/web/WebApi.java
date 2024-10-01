package web;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import ar.unrn.tp.api.ClienteService;
import ar.unrn.tp.api.DescuentosService;
import ar.unrn.tp.api.ProductoService;
import ar.unrn.tp.api.VentaService;
import ar.unrn.tp.modelo.Producto;
import ar.unrn.tp.modelo.TarjetaCredito;
import exceptions.ApiRestException;
import io.javalin.Javalin;
import io.javalin.http.Handler;

public class WebApi {

    private ProductoService productoService;
    private DescuentosService descuentoService;
    private ClienteService clienteServicio;
    private VentaService ventaService;
    private int webPort;

    public WebApi(int webPort, ProductoService productoService, DescuentosService descuentoService, ClienteService clienteService, VentaService ventaService) {
        this.webPort = webPort;
        this.productoService = productoService;
        this.descuentoService = descuentoService;
        this.clienteServicio = clienteService;
        this.ventaService = ventaService;
    }

    public void start() {
      /*  // Si estás usando Javalin 4.x o superior da error en plugins
    	Javalin app = Javalin.create(config -> {
    	    config.plugins.enableCors(cors -> {
    	        cors.add(it -> {
    	            it.allowHost("*"); // Permitir todos los orígenes
    	        });
    	    });
    	}).start(this.webPort);*/
    	
    	Javalin app = Javalin.create(config -> {
    	    // Configuración adicional si la tienes
    	}).before(ctx -> {
    	    // Agregar encabezados CORS manualmente
    	    ctx.header("Access-Control-Allow-Origin", "*");
    	    ctx.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
    	    ctx.header("Access-Control-Allow-Headers", "Content-Type, Authorization");
    	}).start(this.webPort);

        // Rutas GET y POST
        app.get("/productos", this.listarProductos());
        app.get("/tarjetas", this.listarTarjetas());
        app.post("/calcularmonto", this.precioTotal());
        app.post("/pagar", this.comprar());

        // Manejo de excepciones
        app.exception(ApiRestException.class, (e, ctx) -> {
            e.printStackTrace();
            ctx.json(Map.of("result", "error", "errors", e.toMap()));
        });

        app.exception(Exception.class, (e, ctx) -> {
            e.printStackTrace();
            ctx.json(Map.of("result", "error", "message", "Ups... algo se rompió.: " + e.getMessage()));
        });
    }

    public Handler listarProductos() {
        return ctx -> {
            List<Producto> productos = this.productoService.listarProductos();
            List<Map<String, Object>> list = productos.stream()
                .map(Producto::toMap)
                .collect(Collectors.toList());

            ctx.json(Map.of("result", "success", "productos", list));
        };
    }

    public Handler listarTarjetas() {
        return ctx -> {
            List<TarjetaCredito> tarjetaList = this.clienteServicio.listarTarjetas(1L);
            List<Map<String, Object>> list = tarjetaList.stream()
                .map(TarjetaCredito::toMap)
                .collect(Collectors.toList());

            ctx.json(Map.of("result", "success", "tarjetas", list));
        };
    }

    public Handler precioTotal() throws RuntimeException {
        return ctx -> {
            Map<String, List<String>> parametros = ctx.queryParamMap();
            String prod = parametros.get("productos").get(0);
            String tarj = parametros.get("tarjeta").get(0);

            if (prod.isEmpty())
                throw new RuntimeException("No se puede calcular el monto sin productos seleccionados.");
            if (tarj.isEmpty())
                throw new RuntimeException("No se puede calcular el monto sin una tarjeta seleccionada.");

            List<Long> productosLong = Arrays.stream(prod.split(","))
                .map(s -> Long.parseLong(s.trim()))
                .collect(Collectors.toList());
            BigDecimal monto = this.ventaService.calcularMonto(productosLong, Long.valueOf(tarj));

            ctx.json(Map.of("result", "success", "monto", monto));
        };
    }

    public Handler comprar() throws RuntimeException {
        return ctx -> {
            Map<String, List<String>> parametros = ctx.queryParamMap();
            String prod = parametros.get("productos").get(0);
            String tarj = parametros.get("tarjeta").get(0);

            if (prod.isEmpty())
                throw new RuntimeException("No se puede generar la compra sin productos seleccionados.");
            if (tarj.isEmpty())
                throw new RuntimeException("No se puede generar la compra sin una tarjeta seleccionada.");

            List<Long> productosLong = Arrays.stream(prod.split(","))
                .map(s -> Long.parseLong(s.trim()))
                .collect(Collectors.toList());

            this.ventaService.realizarVenta(1L, productosLong, Long.valueOf(tarj));

            ctx.json(Map.of("result", "success"));
        };
    }
}
