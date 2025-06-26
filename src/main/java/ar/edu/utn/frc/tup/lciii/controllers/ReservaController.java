package ar.edu.utn.frc.tup.lciii.controllers;

import ar.edu.utn.frc.tup.lciii.dtos.habitacion.ReservaDTO;
import ar.edu.utn.frc.tup.lciii.dtos.habitacion.ReservaCreateDTO;
import ar.edu.utn.frc.tup.lciii.dtos.common.ErrorApi;
import ar.edu.utn.frc.tup.lciii.services.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

@RestController
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @GetMapping("/reserva/{id_reserva}")
    /**
     * Obtiene la información de una reserva específica según su ID.
     * @param idReserva ID de la reserva a buscar.
     * @return Si la reserva existe, retorna un objeto ReservaDTO con los datos de la reserva y status 200 OK.
     *         Si no existe, retorna un objeto ErrorApi con mensaje de error y status 404 Not Found.
     */
    public ResponseEntity<?> getReserva(@PathVariable("id_reserva") Long idReserva) {
       // TODO --done: Implementar la lógica para obtener la reserva
        ReservaDTO reserva = reservaService.buscarReserva(idReserva);
        if (reserva != null) {
            return ResponseEntity.status(HttpStatus.OK).body(reserva);
        } else {
            ErrorApi error = new ErrorApi();
            error.setStatus(404);
            error.setError("Not Found");
            error.setMessage("Reserva no encontrada");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    }

    @PostMapping("/reserva")
    /**
     * Crea una nueva reserva con los datos proporcionados en el cuerpo de la petición.
     * @param reservaCreateDTO Objeto ReservaCreateDTO con los datos de la reserva a crear.
     * @return Si la reserva se crea correctamente, retorna el objeto ReservaDTO creado y status 201 Created.
     *         Si hay un error de validación, retorna un objeto ErrorApi con mensaje de error y status 400 Bad Request.
     */
    public ResponseEntity<?> crearReserva(@RequestBody ReservaCreateDTO reservaCreateDTO) {
        // TODO --done: Implementar la lógica para crear la reserva
        try {
            ReservaDTO reserva = reservaService.crearReserva(reservaCreateDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(reserva);
        } catch (IllegalArgumentException e) {
            ErrorApi error = new ErrorApi();
            error.setStatus(400);
            error.setError("Bad Request");
            error.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

}