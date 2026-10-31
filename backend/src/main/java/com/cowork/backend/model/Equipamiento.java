package com.cowork.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name="equipamiento")
public class Equipamiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // monitor, proyector, fibra, etc
    @NotBlank(message = "El nombre del equipamiento no puede estar vacio")
    @Column(name = "nombre", nullable = false, unique = true, length = 60)
    private String nombre;

    public Equipamiento() {
    }

    public Equipamiento(String nombre) {
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
