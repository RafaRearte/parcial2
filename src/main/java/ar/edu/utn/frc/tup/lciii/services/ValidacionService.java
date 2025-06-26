package ar.edu.utn.frc.tup.lciii.services;

import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class ValidacionService {
    private static final List<String> TIPOS_HABITACION = Arrays.asList("SIMPLE", "DOBLE", "TRIPLE");
    private static final List<String> MEDIOS_PAGO = Arrays.asList("EFECTIVO", "TARJETA_DEBITO", "TARJETA_CREDITO");
    private static final List<Long> IDS_HOTEL = Arrays.asList(1L, 2L, 3L);

    /**
     * Valida los parámetros de la reserva: id de hotel, tipo de habitación, fechas y medio de pago.
     * Lanza IllegalArgumentException si algún parámetro es inválido.
     */
    public void validarParametros(Long idHotel, String tipoHabitacion, Date fechaIngreso, Date fechaSalida, String medioPago) {
        // --TODO-- done: Implementar la lógica para validar los parámetros
        if (idHotel == null || !IDS_HOTEL.contains(idHotel)) {
            throw new IllegalArgumentException("id hotel inválido");
        }
        if (tipoHabitacion == null || !TIPOS_HABITACION.contains(tipoHabitacion.toUpperCase())) {
            throw new IllegalArgumentException("Tipo habitación inválido");
        }
        if (fechaIngreso == null || fechaSalida == null || fechaIngreso.after(fechaSalida) || fechaIngreso.before(new Date())) {
            throw new IllegalArgumentException("Fechas inválidas");
        }
        if (medioPago == null || !MEDIOS_PAGO.contains(medioPago.toUpperCase())) {
            throw new IllegalArgumentException("Medio de pago inválido");
        }
        return;
    }
} 