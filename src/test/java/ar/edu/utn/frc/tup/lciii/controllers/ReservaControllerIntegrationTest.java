package ar.edu.utn.frc.tup.lciii.controllers;

import ar.edu.utn.frc.tup.lciii.dtos.habitacion.ReservaDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ReservaControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCrearYBuscarReserva() throws Exception {
        ReservaDTO reserva = ReservaDTO.builder()
                .nombreCliente("Test Cliente")
                .idHotel(1L)
                .tipoHabitacion("SIMPLE")
                .fechaIngreso(new Date(System.currentTimeMillis() + 86400000))
                .fechaSalida(new Date(System.currentTimeMillis() + 2*86400000))
                .medioPago("EFECTIVO")
                .build();
        // Crear reserva
        String response = mockMvc.perform(post("/reserva")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(reserva)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id_reserva").exists())
                .andReturn().getResponse().getContentAsString();
        ReservaDTO creada = objectMapper.readValue(response, ReservaDTO.class);
        // Buscar reserva
        mockMvc.perform(get("/reserva/" + creada.getIdReserva()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre_cliente").value("Test Cliente"));
    }

    @Test
    void testReservaParametrosInvalidos() throws Exception {
        ReservaDTO reserva = ReservaDTO.builder()
                .nombreCliente("")
                .idHotel(99L)
                .tipoHabitacion("DELUXE")
                .fechaIngreso(new Date(System.currentTimeMillis() - 86400000))
                .fechaSalida(new Date(System.currentTimeMillis() - 2*86400000))
                .medioPago("BITCOIN")
                .build();
        mockMvc.perform(post("/reserva")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(reserva)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Bad Request"));
    }
} 