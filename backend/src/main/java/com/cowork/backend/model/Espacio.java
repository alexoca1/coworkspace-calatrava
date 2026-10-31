package com.cowork.backend.model;


import com.cowork.backend.model.enums.TipoEspacio;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "espacio")
public class Espacio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre del espacio es obligatorio")
    @Size(max = 100, message = "El nombre no puede superar los 100 caracteres")
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @NotNull(message = "El tipo de espacio es obligatorio")
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false, length = 30)
    private TipoEspacio tipo;

    @NotNull(message = "La capacidad es obligatoria")
    @Min(value = 1, message = "La capacidad minima es de 1 persona")
    @Column(name = "capacidad", nullable = false)
    private Integer capacidad = 1;

    // relacion uno a muchos con la tabla intermedia
    @OneToMany(mappedBy = "espacio", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<EspacioEquipamiento> equipamientos = new HashSet<>();

    @NotNull(message = "El precio por hora es obligatorio")
    @DecimalMin(value = "0.0", inclusive = true, message = "El precio por hora no puede ser negativo")
    @Column(name = "precio_hora", nullable = false, precision = 8, scale = 2)
    private BigDecimal precioHora;

    @DecimalMin(value = "0.0", inclusive = true, message = "El precio por dia no puede ser negativo")
    @Column(name = "precio_dia", precision = 8, scale = 2)
    private BigDecimal precioDia;

    @DecimalMin(value = "0.0", inclusive = true, message = "El precio por mes no puede ser negativo")
    @Column(name = "precio_mes", precision = 8, scale = 2)
    private BigDecimal precioMes;

    @NotNull(message = "La coordenada X es obligatoria")
    @Min(value = 0, message = "pos_x debe estar entre 0 y 100")
    @Max(value = 100, message = "pos_x debe estar entre 0 y 100")
    @Column(name = "pos_x", nullable = false)
    private Integer posX;

    @NotNull(message = "La coordenada Y es obligatoria")
    @Min(value = 0, message = "pos_y debe estar entre 0 y 100")
    @Max(value = 100, message = "pos_y debe estar entre 0 y 100")
    @Column(name = "pos_y", nullable = false)
    private Integer posY;

    @Size(max = 255)
    @Column(name = "imagen_url", length = 255)
    private String imagenUrl;

    @Column(name = "activo", nullable = false)
    private Boolean activo = true;

    public Espacio() {
    }

    public Espacio(String nombre, TipoEspacio tipo, Integer capacidad, BigDecimal precioHora,
                   BigDecimal precioDia, BigDecimal precioMes, Integer posX, Integer posY, String imagenUrl) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.capacidad = (capacidad != null) ? capacidad : 1;
        this.precioHora = precioHora;
        this.precioDia = precioDia;
        this.precioMes = precioMes;
        this.posX = posX;
        this.posY = posY;
        this.imagenUrl = imagenUrl;
        this.activo = true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoEspacio getTipo() {
        return tipo;
    }

    public void setTipo(TipoEspacio tipo) {
        this.tipo = tipo;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public Set<EspacioEquipamiento> getEquipamientos() {
        return equipamientos;
    }

    public void setEquipamientos(Set<EspacioEquipamiento> equipamientos) {
        this.equipamientos = equipamientos;
    }

    public BigDecimal getPrecioHora() {
        return precioHora;
    }

    public void setPrecioHora(BigDecimal precioHora) {
        this.precioHora = precioHora;
    }

    public BigDecimal getPrecioDia() {
        return precioDia;
    }

    public void setPrecioDia(BigDecimal precioDia) {
        this.precioDia = precioDia;
    }

    public BigDecimal getPrecioMes() {
        return precioMes;
    }

    public void setPrecioMes(BigDecimal precioMes) {
        this.precioMes = precioMes;
    }

    public Integer getPosX() {
        return posX;
    }

    public void setPosX(Integer posX) {
        this.posX = posX;
    }

    public Integer getPosY() {
        return posY;
    }

    public void setPosY(Integer posY) {
        this.posY = posY;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}

