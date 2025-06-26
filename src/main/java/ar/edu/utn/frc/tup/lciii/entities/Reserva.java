package ar.edu.utn.frc.tup.lciii.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@Table(name = "reservas")
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReserva;

    private String nombreCliente;
    private Long idHotel;
    private String tipoHabitacion;
    private Date fechaIngreso;
    private Date fechaSalida;
    private String estadoReserva;
    private BigDecimal precio;
    private String medioPago;

} 