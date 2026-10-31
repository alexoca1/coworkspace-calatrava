package com.cowork.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

@Entity
@Table(name = "plan")
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // codigo del plan: FREE, FLEX, PRO
    @NotBlank(message = "El codigo es obligatorio")
    @Column(name = "codigo", nullable = false, unique = true, length = 25)
    private String codigo;

    @NotBlank(message = "El nombre es obligatorio")
    @Column(name = "nombre", nullable = false, length = 60)
    private String nombre;

    @NotNull(message = "El precio mensual es obligatorio")
    @DecimalMin(value = "0.0", message = "No puede ser negativo")
    @Column(name = "precio_mensual", nullable = false, precision = 8, scale = 2)
    private BigDecimal precioMensual;

    @NotNull
    @Column(name = "descuento_porcentaje", nullable = false, precision = 5, scale = 2)
    private BigDecimal descuentoPorcentaje = BigDecimal.ZERO;

    @Column(name = "activo", nullable = false)
    private Boolean activo = true;

    // constructor vacio para jpa
    public Plan() {
    }

    public Plan(String codigo, String nombre, BigDecimal precioMensual, BigDecimal descuentoPorcentaje) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precioMensual = precioMensual;
        this.descuentoPorcentaje = descuentoPorcentaje;
        this.activo = true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getPrecioMensual() {
        return precioMensual;
    }

    public void setPrecioMensual(BigDecimal precioMensual) {
        this.precioMensual = precioMensual;
    }

    public BigDecimal getDescuentoPorcentaje() {
        return descuentoPorcentaje;
    }

    public void setDescuentoPorcentaje(BigDecimal descuentoPorcentaje) {
        this.descuentoPorcentaje = descuentoPorcentaje;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}


