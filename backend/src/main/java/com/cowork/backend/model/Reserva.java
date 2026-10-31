package com.cowork.backend.model;


import com.cowork.backend.model.enums.EstadoReserva;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "reserva")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El usuario es obligatorio")
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @NotNull(message = "Debes seleccionar un espacio")
    @ManyToOne
    @JoinColumn(name = "espacio_id", nullable = false)
    private Espacio espacio;

    @NotNull(message = "Debes seleccionar la fecha de inicio")
    @Column(name = "fecha_inicio", nullable = false)
    private OffsetDateTime fechaInicio;

    @NotNull(message = "Debes seleccionar la fecha de finalizacion")
    @Column(name = "fecha_fin", nullable = false)
    private OffsetDateTime fechaFin;

    @NotNull(message = "El precio total es obligatorio")
    @DecimalMin(value = "0.0", inclusive = true, message = "El precio total no puede ser negativo")
    @Column(name = "precio_total", nullable = false, precision = 8, scale = 2)
    private BigDecimal precioTotal;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false, length = 20)
    private EstadoReserva estado = EstadoReserva.CONFIRMADA;

    @NotBlank(message = "El codigo de check-in es obligatorio")
    @Size(max = 64, message = "El codigo de check-in no puede superar 64 caracteres")
    @Column(name = "codigo_checkin", nullable = false, unique = true, length = 64)
    private String codigoCheckin;

    @Column(name = "fecha_checkin")
    private OffsetDateTime fechaCheckin;

    @Column(name = "creado_en", nullable = false, insertable = false, updatable = false)
    private OffsetDateTime creadoEn;

    // pagos y cobros de esta reserva
    @OneToMany(mappedBy = "reserva")
    private List<Pago> pagos = new ArrayList<>();

    public Reserva() {
    }
    public Reserva(Usuario usuario, Espacio espacio, OffsetDateTime fechaInicio, OffsetDateTime fechaFin,
                   BigDecimal precioTotal, EstadoReserva estado, String codigoCheckin) {
        this.usuario = usuario;
        this.espacio = espacio;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.precioTotal = precioTotal;
        this.estado = (estado != null) ? estado : EstadoReserva.CONFIRMADA;
        this.codigoCheckin = codigoCheckin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Espacio getEspacio() {
        return espacio;
    }

    public void setEspacio(Espacio espacio) {
        this.espacio = espacio;
    }

    public OffsetDateTime getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(OffsetDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public OffsetDateTime getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(OffsetDateTime fechaFin) {
        this.fechaFin = fechaFin;
    }

    public BigDecimal getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(BigDecimal precioTotal) {
        this.precioTotal = precioTotal;
    }

    public EstadoReserva getEstado() {
        return estado;
    }

    public void setEstado(EstadoReserva estado) {
        this.estado = estado;
    }

    public String getCodigoCheckin() {
        return codigoCheckin;
    }

    public void setCodigoCheckin(String codigoCheckin) {
        this.codigoCheckin = codigoCheckin;
    }

    public OffsetDateTime getFechaCheckin() {
        return fechaCheckin;
    }

    public void setFechaCheckin(OffsetDateTime fechaCheckin) {
        this.fechaCheckin = fechaCheckin;
    }

    public OffsetDateTime getCreadoEn() {
        return creadoEn;
    }

    public void setCreadoEn(OffsetDateTime creadoEn) {
        this.creadoEn = creadoEn;
    }

    public List<Pago> getPagos() {
        return pagos;
    }

    public void setPagos(List<Pago> pagos) {
        this.pagos = pagos;
    }
}

