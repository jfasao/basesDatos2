package ar.unrn.tp.modelo;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class ClienteTest {

    @Test
    public void testCrearClienteSinNombre() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Cliente(null, "Apellido", "email@dominio.com", "12345678", new ArrayList<>());
        }, "Se esperaba una excepción porque el nombre es null.");
    }

    @Test
    public void testCrearClienteSinApellido() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Cliente("Nombre", null, "email@dominio.com", "12345678", new ArrayList<>());
        }, "Se esperaba una excepción porque el apellido es null.");
    }

    @Test
    public void testCrearClienteSinDNI() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Cliente("Nombre", "Apellido", "email@dominio.com", null, new ArrayList<>());
        }, "Se esperaba una excepción porque el DNI es null.");
    }

    @Test
    public void testCrearClienteEmailInvalido() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Cliente("Nombre", "Apellido", "email_invalido", "12345678", new ArrayList<>());
        }, "Se esperaba una excepción porque el email es inválido.");
    }

    @Test
    public void testCrearClienteEmailValido() {
        new Cliente("Nombre", "Apellido", "email@dominio.com", "12345678", new ArrayList<>());
        // Este test pasa si no se lanza excepción, lo que indica que el email es válido.
    }
}