package ar.edu.utn.frc.tup.lciii.services;

import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.math.BigDecimal;

@Service
public class PrecioService {
    private static final Map<Long, Map<String, Double>> PRECIOS_BASE = new HashMap<>();
    static {
        Map<String, Double> hotel1 = new HashMap<>();
        hotel1.put("SIMPLE", 1250.0);
        hotel1.put("DOBLE", 2100.0);
        hotel1.put("TRIPLE", 2850.0);
        PRECIOS_BASE.put(1L, hotel1);
        Map<String, Double> hotel2 = new HashMap<>();
        hotel2.put("SIMPLE", 370.0);
        hotel2.put("DOBLE", 650.0);
        hotel2.put("TRIPLE", 875.0);
        PRECIOS_BASE.put(2L, hotel2);
        Map<String, Double> hotel3 = new HashMap<>();
        hotel3.put("SIMPLE", 2200.0);
        hotel3.put("DOBLE", 3700.0);
        hotel3.put("TRIPLE", 4100.0);
        PRECIOS_BASE.put(3L, hotel3);
    }

    /**
     * Calcula el precio total de la reserva según hotel, tipo de habitación, fechas y medio de pago.
     * Aplica el mayor factor de temporada del rango y el descuento correspondiente.
     */
    public BigDecimal calcularPrecio(Long idHotel, String tipoHabitacion, Date fechaIngreso, Date fechaSalida, String medioPago) {
        // TODO: Implementar la lógica para calcular el precio de la reserva
        return null;
    }

    /**
     * Obtiene el mayor factor de temporada para el rango de fechas de la estadía.
     * Recorre cada día desde ingreso (inclusive) hasta salida (exclusive).
     */
    private double getFactorTemporada(Date ingreso, Date salida) {
        double factor = 0.0;
        long millisPorDia = 24 * 60 * 60 * 1000;
        for (long t = ingreso.getTime(); t < salida.getTime(); t += millisPorDia) {
            Date date = new Date(t);
            double f = getFactorPorMes(date.getMonth() + 1);
            if (f > factor) factor = f;
        }
        return factor;
    }

    /**
     * Devuelve el factor de temporada según el mes (alta, baja, media).
     */
    private double getFactorPorMes(int mes) {
        switch (mes) {
            case 1: case 2: case 7: case 8:
                return 1.3; // Alta
            case 3: case 4: case 10: case 11:
                return 0.9; // Baja
            case 5: case 6: case 9: case 12:
                return 1.0; // Media
            default:
                return 1.0;
        }
    }

    /**
     * Aplica el descuento correspondiente según el medio de pago.
     */
    private double aplicarDescuento(double precio, String medioPago) {
        // TODO: Implementar la lógica para aplicar el descuento según el medio de pago
        return 0.0;
    }
} 