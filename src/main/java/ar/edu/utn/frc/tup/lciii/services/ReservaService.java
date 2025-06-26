package ar.edu.utn.frc.tup.lciii.services;

import ar.edu.utn.frc.tup.lciii.dtos.habitacion.ReservaDTO;
import ar.edu.utn.frc.tup.lciii.dtos.habitacion.ReservaCreateDTO;
import ar.edu.utn.frc.tup.lciii.entities.Reserva;
import ar.edu.utn.frc.tup.lciii.repositories.ReservaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class ReservaService {
    @Autowired
    ValidacionService validacionService;
    @Autowired
    DisponibilidadService disponibilidadService;
    @Autowired
    PrecioService precioService;
    @Autowired
    ReservaRepository reservaRepository;
    @Autowired
    ModelMapper modelMapper;

    /**
     * Orquesta la creación de una reserva: valida parámetros, verifica disponibilidad, calcula precio y persiste la reserva.
     * @param reservaCreateDTO DTO con los datos de la reserva solicitada
     * @return ReservaDTO con los datos de la reserva creada
     * @throws IllegalArgumentException si alguna validación falla
     */
    public ReservaDTO crearReserva(ReservaCreateDTO reservaCreateDTO) {
        // TODO-- done: Implementar la lógica para crear la reserva
        
        // Aquí deberás mapear manualmente o usando un mapper de ReservaCreateDTO a ReservaDTO o a la entidad correspondiente
        // Validar parámetros de entrada
        validacionService.validarParametros(
                reservaCreateDTO.getIdHotel(),
                reservaCreateDTO.getTipoHabitacion(),
                reservaCreateDTO.getFechaIngreso(),
                reservaCreateDTO.getFechaSalida(),
                reservaCreateDTO.getMedioPago()
        );
        // Verificar disponibilidad
        if (!disponibilidadService.hayDisponibilidad(
                reservaCreateDTO.getIdHotel(),
                reservaCreateDTO.getTipoHabitacion(),
                reservaCreateDTO.getFechaIngreso(),
                reservaCreateDTO.getFechaSalida())) {
            throw new IllegalArgumentException("No hay disponibilidad");
        }
        // Calcular precio
        BigDecimal precioTotal = precioService.calcularPrecio(
                reservaCreateDTO.getIdHotel(),
                reservaCreateDTO.getTipoHabitacion(),
                reservaCreateDTO.getFechaIngreso(),
                reservaCreateDTO.getFechaSalida(),
                reservaCreateDTO.getMedioPago()
        );
        // Mapear DTO a entidad y persistir
        Reserva reserva = modelMapper.map(reservaCreateDTO, Reserva.class);
        reserva.setPrecio(precioTotal);
        reserva.setEstadoReserva("EXITOSA");
        Reserva reservaGuardada = reservaRepository.save(reserva);


        // Mapear entidad persistida a DTO de respuesta


        return modelMapper.map(reservaGuardada, ReservaDTO.class);
    }

    /**
     * Busca una reserva por su ID y la retorna como DTO.
     * @param idReserva ID de la reserva
     * @return ReservaDTO con los datos de la reserva encontrada
     * @throws IllegalArgumentException si no se encuentra la reserva
     */
    public ReservaDTO buscarReserva(Long idReserva) {
        // TODO -- done: Implementar la lógica para buscar la reserva
        Optional<Reserva> reservaOptional = reservaRepository.findById(idReserva);
        if (reservaOptional.isPresent()) {
            Reserva reserva = reservaOptional.get();
            return modelMapper.map(reserva, ReservaDTO.class);
        }
        return null;
    }
} 