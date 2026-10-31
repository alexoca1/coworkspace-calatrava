package com.cowork.backend.repository;
import com.cowork.backend.model.Reserva;
import com.cowork.backend.model.enums.EstadoReserva;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    // Búsqueda por token criptográfico para el escáner QR en recepción
    Optional<Reserva> findByCodigoCheckin(String codigoCheckin);

    // Listado histórico del coworker ordenado cronológicamente
    List<Reserva> findByUsuarioIdOrderByFechaInicioDesc(Long usuarioId);

    // Listado global para el panel de administración
    List<Reserva> findAllByOrderByFechaInicioDesc();

    // Consulta transaccional previa: comprueba solapamiento para un espacio específico
    @Query("SELECT COUNT(r) > 0 FROM Reserva r " +
            "WHERE r.espacio.id = :espacioId " +
            "AND r.estado != :estadoCancelada " +
            "AND r.fechaInicio < :fechaFin " +
            "AND r.fechaFin > :fechaInicio")
    boolean existsSolapamiento(@Param("espacioId") Long espacioId,
                               @Param("fechaInicio") OffsetDateTime fechaInicio,
                               @Param("fechaFin") OffsetDateTime fechaFin,
                               @Param("estadoCancelada") EstadoReserva estadoCancelada);

    // Consulta para el plano interactivo: IDs de espacios ocupados en la franja seleccionada
    @Query("SELECT DISTINCT r.espacio.id FROM Reserva r " +
            "WHERE r.estado != :estadoCancelada " +
            "AND r.fechaInicio < :fechaFin " +
            "AND r.fechaFin > :fechaInicio")
    List<Long> findEspacioIdsOcupadosEnRango(@Param("fechaInicio") OffsetDateTime fechaInicio,
                                             @Param("fechaFin") OffsetDateTime fechaFin,
                                             @Param("estadoCancelada") EstadoReserva estadoCancelada);
}
