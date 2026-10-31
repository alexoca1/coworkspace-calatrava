package com.cowork.backend.repository;

import com.cowork.backend.model.Espacio;
import com.cowork.backend.model.enums.TipoEspacio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EspacioRepository extends JpaRepository<Espacio, Long> {
    List<Espacio> findByActivoTrue();
    List<Espacio> findByActivoTrueAndTipo(TipoEspacio tipo);
}
