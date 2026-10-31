package com.cowork.backend.repository;

import com.cowork.backend.model.EspacioEquipamiento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EspacioEquipamientoRepository extends JpaRepository<EspacioEquipamiento, Long> {

    // listar los equipamientos asignados a un espacio por su id
    List<EspacioEquipamiento> findByEspacioId(Long espacioId);
}
