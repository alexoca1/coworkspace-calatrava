package com.cowork.backend.repository;

import com.cowork.backend.model.Pago;
import com.cowork.backend.model.enums.TipoPago;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PagoRepository extends JpaRepository<Pago, Long> {

    // listar los pagos o cobros de una reserva concreta
    List<Pago> findByReservaId(Long reservaId);
    // comprobar si una reserva ya tiene cobro o reembolso registrado
    Optional<Pago> findByReservaIdAndTipo(Long reservaId, TipoPago tipo);
}
