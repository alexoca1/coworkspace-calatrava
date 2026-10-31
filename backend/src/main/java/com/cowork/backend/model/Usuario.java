package com.cowork.backend.model;

import com.cowork.backend.model.enums.Rol;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.OffsetDateTime;




    @Entity
    @Table(name = "usuario")
    public class Usuario {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @NotBlank(message = "El nombre es obligatorio")
        @Size(min = 2, max = 120, message = "El nombre debe tener entre 2 y 120 caracteres")
        @Column(name = "nombre", nullable = false, length = 120)
        private String nombre;

        @NotBlank(message = "El email es obligatorio")
        @Email(message = "El formato de email no es valido")
        @Column(name = "email", nullable = false, unique = true, length = 160)
        private String email;

        @NotBlank(message = "La contrasena es obligatoria")
        @Column(name = "password", nullable = false, length = 255)
        private String password;

        @Enumerated(EnumType.STRING)
        @Column(name = "rol", nullable = false, length = 25)
        private Rol rol = Rol.COWORKER;

        // relacion con la tabla plan (muchos usuarios pueden tener el mismo plan)
        @ManyToOne
        @JoinColumn(name = "plan_id")
        private Plan plan;

        @Column(name = "creado_en", nullable = false, insertable = false, updatable = false)
        private OffsetDateTime creadoEn;

        public Usuario() {
        }

        public Usuario(String nombre, String email, String password, Rol rol, Plan plan) {
            this.nombre = nombre;
            this.email = email;
            this.password = password;
            this.rol = (rol != null) ? rol : Rol.COWORKER;
            this.plan = plan;
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

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public Rol getRol() {
            return rol;
        }

        public void setRol(Rol rol) {
            this.rol = rol;
        }

        public Plan getPlan() {
            return plan;
        }

        public void setPlan(Plan plan) {
            this.plan = plan;
        }

        public OffsetDateTime getCreadoEn() {
            return creadoEn;
        }

        public void setCreadoEn(OffsetDateTime creadoEn) {
            this.creadoEn = creadoEn;
        }
    }


