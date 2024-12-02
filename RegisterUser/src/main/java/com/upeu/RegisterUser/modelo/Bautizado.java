package com.upeu.RegisterUser.modelo;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "bautizados")
public class Bautizado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDate fechaBautizo; // Usamos LocalDate para la fecha

    private String lugar;  // Lugar del bautizo

    @OneToOne
    @JoinColumn(name = "persona_id", referencedColumnName = "id") // FK hacia Persona
    private Persona persona;

    @Version
    private int version; // Control de versión para concurrencia optimista
}
