package com.cowork.backend.repository;

import com.cowork.backend.model.Equipamiento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EquipamientoRepository extends JpaRepository<Equipamiento, Long>

    {
        // buscar un equipamiento por su nombre (ej: "Monitor 27")
        Optional<Equipamiento> findByNombre (String nombre);
    }
