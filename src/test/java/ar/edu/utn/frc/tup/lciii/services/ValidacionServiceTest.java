package ar.edu.utn.frc.tup.lciii.services;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;

class ValidacionServiceTest {
    private final ValidacionService service = new ValidacionService();

    @Test
    void testValidacionExitosa() {
        assertDoesNotThrow(() -> service.validarParametros(1L, "SIMPLE", new Date(System.currentTimeMillis() + 86400000), new Date(System.currentTimeMillis() + 2*86400000), "EFECTIVO"));
    }

    @Test
    void testHotelInvalido() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> service.validarParametros(99L, "SIMPLE", new Date(), new Date(), "EFECTIVO"));
        assertTrue(e.getMessage().contains("hotel"));
    }

    @Test
    void testTipoHabitacionInvalido() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> service.validarParametros(1L, "DELUXE", new Date(), new Date(), "EFECTIVO"));
        assertTrue(e.getMessage().contains("habitación"));
    }

    @Test
    void testMedioPagoInvalido() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> service.validarParametros(1L, "SIMPLE", new Date(), new Date(), "BITCOIN"));
        assertTrue(e.getMessage().contains("pago"));
    }

    @Test
    void testFechasInvalidas() {
        Date pasada = new Date(System.currentTimeMillis() - 86400000);
        Date futura = new Date(System.currentTimeMillis() + 86400000);
        Exception e1 = assertThrows(IllegalArgumentException.class, () -> service.validarParametros(1L, "SIMPLE", pasada, futura, "EFECTIVO"));
        Exception e2 = assertThrows(IllegalArgumentException.class, () -> service.validarParametros(1L, "SIMPLE", futura, pasada, "EFECTIVO"));
        assertTrue(e1.getMessage().contains("Fechas"));
        assertTrue(e2.getMessage().contains("Fechas"));
    }
} 