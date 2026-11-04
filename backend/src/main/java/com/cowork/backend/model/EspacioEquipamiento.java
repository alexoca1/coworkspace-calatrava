package com.cowork.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name="espacio_equipamiento")
public class EspacioEquipamiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "espacio_id", nullable = false)
    private Espacio espacio;

    @ManyToOne
    @JoinColumn(name = "equipamiento_id", nullable = false)
    private Equipamiento equipamiento;

    public EspacioEquipamiento() {
    }

    public EspacioEquipamiento(Espacio espacio, Equipamiento equipamiento) {
        this.espacio = espacio;
        this.equipamiento = equipamiento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Espacio getEspacio() {
        return espacio;
    }

    public void setEspacio(Espacio espacio) {
        this.espacio = espacio;
    }

    public Equipamiento getEquipamiento() {
        return equipamiento;
    }

    public void setEquipamiento(Equipamiento equipamiento) {
        this.equipamiento = equipamiento;
    }
}
