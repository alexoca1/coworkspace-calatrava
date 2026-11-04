package com.cowork.backend.repository;

import com.cowork.backend.model.Reserva;
import com.cowork.backend.model.enums.EstadoReserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    // buscar reserva por el codigo de checkin
    Optional<Reserva> findByCodigoCheckin(String codigoCheckin);

    // Historial de reservas de un usuario
    List<Reserva> findByUsuarioIdOrderByFechaInicioDesc(Long usuarioId);

    // Todas las reservas para el panel de administración
    List<Reserva> findAllByOrderByFechaInicioDesc();

    // Comprobar si ya existe una reserva en ese horario
    @Query("SELECT COUNT(r) > 0 FROM Reserva r " +
            "WHERE r.espacio.id = :espacioId " +
            "AND r.estado != :estadoCancelada " +
            "AND r.fechaInicio < :fechaFin " +
            "AND r.fechaFin > :fechaInicio")
    boolean existsSolapamiento(@Param("espacioId") Long espacioId,
                               @Param("fechaInicio") LocalDateTime fechaInicio,
                               @Param("fechaFin") LocalDateTime fechaFin,
                               @Param("estadoCancelada") EstadoReserva estadoCancelada);

    // Espacios ocupados en un rango de fechas
    @Query("SELECT DISTINCT r.espacio.id FROM Reserva r " +
            "WHERE r.estado != :estadoCancelada " +
            "AND r.fechaInicio < :fechaFin " +
            "AND r.fechaFin > :fechaInicio")

    List<Long> findEspacioIdsOcupadosEnRango(@Param("fechaInicio") LocalDateTime fechaInicio,
                                             @Param("fechaFin") LocalDateTime fechaFin,
                                             @Param("estadoCancelada") EstadoReserva estadoCancelada);
}