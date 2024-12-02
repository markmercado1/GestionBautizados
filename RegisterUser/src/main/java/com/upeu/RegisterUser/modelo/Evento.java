package com.upeu.RegisterUser.modelo;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "tbl_eventos") // Cambiado para que coincida con la entidad
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalTime horaInicio; // Hora de inicio del evento
    private LocalTime horaFin;    // Hora de finalización del evento
    private LocalDate fecha;      // Fecha del evento

    private String tipo;          // Tipo de evento

    @Version
    @Column(name = "version")
    private int version;          // Control de versión para concurrencia optimista


    // Si necesitas constructores personalizados, puedes incluirlos aquí.
}
