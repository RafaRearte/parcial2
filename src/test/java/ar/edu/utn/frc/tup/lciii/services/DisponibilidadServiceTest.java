package ar.edu.utn.frc.tup.lciii.services;


import ar.edu.utn.frc.tup.lciii.repositories.ReservaRepository;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DisponibilidadServiceTest {
    @Test
    void hayDisponibilidad() {
        ReservaRepository mockRepo = mock(ReservaRepository.class);
        when(mockRepo.findAll()).thenReturn(Collections.emptyList());

        DisponibilidadService service = new DisponibilidadService(mockRepo);
        Date fechaIngreso = new Date(System.currentTimeMillis() + 86400000);
        Date fechaSalida = new Date(System.currentTimeMillis() + 2 * 86400000);

        boolean resultado = service.hayDisponibilidad(1L, "SIMPLE", fechaIngreso, fechaSalida);

        assertTrue(resultado);
    }
    // TODO: Implementar test para servicios de disponibilidad
} 