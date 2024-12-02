package com.upeu.RegisterUser.modelo;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "personas")
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;
    private String apellido;
    private String celular;
    private int edad;

    @OneToMany(mappedBy = "persona") // Relación inversa con Bautizado
    private List<Bautizado> bautizados;

    @Version
    private int version; // Control de versión para concurrencia optimista
}
