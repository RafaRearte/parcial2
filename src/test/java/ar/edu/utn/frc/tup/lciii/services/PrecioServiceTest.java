package ar.edu.utn.frc.tup.lciii.services;


import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PrecioServiceTest {
    @Test
    void calcularPrecio() {

    }

    @Test
    void testCalcularPrecio() {
        PrecioService service = new PrecioService();
        Date fechaIngreso = new Date(System.currentTimeMillis() + 86400000);
        Date fechaSalida = new Date(System.currentTimeMillis() + 2 * 86400000);

        BigDecimal precio = service.calcularPrecio(1L, "SIMPLE", fechaIngreso, fechaSalida, "EFECTIVO");

        assertNotNull(precio);
        assertTrue(precio.compareTo(BigDecimal.ZERO) > 0);
    }
    // TODO: Implementar test para servicios de precio
} 