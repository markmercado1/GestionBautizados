package com.upeu.RegisterUser.modelo;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name ="pastores")
public class Pastores {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private String apellido;
    private String celular;
    private int edad;
    private String cargo;
    private String iglesia;

    @Version
    private int version; // Control de versión para concurrencia optimista
}
