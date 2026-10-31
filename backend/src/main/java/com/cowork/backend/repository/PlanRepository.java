package com.cowork.backend.repository;

import com.cowork.backend.model.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PlanRepository extends JpaRepository<Plan, Long>
{
    // buscar plan por su codigo unico: FREE, FLEX, PRO
    Optional<Plan> findByCodigo(String codigo);
    // obtener solo los planes que siguen vigentes
    List<Plan> findByActivoTrue();
}
