package ar.unrn.tp.modelo;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

public class DescuentoBancoTest {

    @Test
    public void testCrearDescuentoConFechasSuperpuestas() {
    	Banco bancoDescuento = new Banco("Banco XYZ", "1234", "30-12345678-9");
        DescuentoBanco descuento1 = new DescuentoBanco(LocalDate.of(2024, 1, 1), LocalDate.of(2024, 6, 30), 10,bancoDescuento);
        DescuentoBanco descuento2 = new DescuentoBanco(LocalDate.of(2024, 5, 1), LocalDate.of(2024, 12, 31), 15,bancoDescuento);

        assertThrows(IllegalArgumentException.class, () -> {
            // Aquí se asumiría que hay una validación en algún lugar que verifica la superposición de fechas.
            if (descuento1.descuentoSolapado(descuento2)) {
                throw new IllegalArgumentException("Las fechas de validez están solapadas.");
            }
        }, "Se esperaba una excepción porque las fechas de validez están solapadas.");
    }

    @Test
    public void testCrearDescuentoSinFechasSuperpuestas() {
    	Banco bancoDescuento = new Banco("Banco XYZ", "1234", "30-12345678-9");
        DescuentoBanco descuento1 = new DescuentoBanco(LocalDate.of(2024, 1, 1), LocalDate.of(2024, 6, 30), 10,bancoDescuento);
        DescuentoBanco descuento2 = new DescuentoBanco(LocalDate.of(2024, 7, 1), LocalDate.of(2024, 12, 31), 15,bancoDescuento);

        // No debería lanzar excepción ya que las fechas no están solapadas.
        if (descuento1.descuentoSolapado(descuento2)) {
            throw new IllegalStateException("Las fechas no deberían estar solapadas.");
        }
    }
}
