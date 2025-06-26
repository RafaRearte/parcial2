package ar.edu.utn.frc.tup.lciii.services;

import ar.edu.utn.frc.tup.lciii.repositories.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class DisponibilidadService {
    ReservaRepository reservaRepository;

    @Autowired
    public DisponibilidadService(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    public DisponibilidadService() {}

    /**
     * Verifica si hay disponibilidad de habitaciones para el hotel, tipo y rango de fechas indicado.
     * Retorna true si no hay reservas superpuestas, false en caso contrario.
     */
    public boolean hayDisponibilidad(Long idHotel, String tipoHabitacion, Date fechaIngreso, Date fechaSalida) {
        // TODO: Implementar la lógica para verificar la disponibilidad de habitaciones
        return false;
    }
} 