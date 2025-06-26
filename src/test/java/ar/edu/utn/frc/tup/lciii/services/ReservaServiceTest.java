package ar.edu.utn.frc.tup.lciii.services;


import ar.edu.utn.frc.tup.lciii.dtos.habitacion.ReservaCreateDTO;
import ar.edu.utn.frc.tup.lciii.dtos.habitacion.ReservaDTO;
import ar.edu.utn.frc.tup.lciii.entities.Reserva;
import ar.edu.utn.frc.tup.lciii.repositories.ReservaRepository;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.modelmapper.internal.bytebuddy.matcher.ElementMatchers.any;

@SpringBootTest
class ReservaServiceTest {

    @Autowired
    private ReservaService reservaService;

    @Test
    void crearReserva() {
        ReservaCreateDTO createDTO = new ReservaCreateDTO();
        createDTO.setIdHotel(1L);
        createDTO.setNombreCliente("RAFAEL");
        createDTO.setTipoHabitacion("SIMPLE");
        createDTO.setFechaIngreso(new Date(System.currentTimeMillis() + 86400000));
        createDTO.setFechaSalida(new Date(System.currentTimeMillis() + 2 * 86400000));
        createDTO.setMedioPago("EFECTIVO");

        ReservaDTO resultado = reservaService.crearReserva(createDTO);

        assertNotNull(resultado);
        assertEquals("RAFAEL", resultado.getNombreCliente());
    }

    @Test
    void buscarReserva() {
        ReservaCreateDTO createDTO = new ReservaCreateDTO();
        createDTO.setIdHotel(1L);
        createDTO.setNombreCliente("RAFAEL");
        createDTO.setTipoHabitacion("SIMPLE");
        createDTO.setFechaIngreso(new Date(System.currentTimeMillis() + 86400000));
        createDTO.setFechaSalida(new Date(System.currentTimeMillis() + 2 * 86400000));
        createDTO.setMedioPago("EFECTIVO");

        ReservaDTO creada = reservaService.crearReserva(createDTO);

        ReservaDTO encontrada = reservaService.buscarReserva(creada.getIdReserva());

        assertNotNull(encontrada);
        assertEquals("RAFAEL", encontrada.getNombreCliente()); 
    }
    // TODO: Implementar test para servicios de reserva
} 