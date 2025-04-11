package com.usuarios.seguridad.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(unique = true, nullable = false)
    private String correo;

    @Column(nullable = false)
    private String contraseña;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Phone> telefonos = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @Column(unique = true, nullable = false)
    private String userName;

    public String getUserName() {
        return userName;
    }

    public User setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    private LocalDateTime creado;
    private LocalDateTime modificado;
    private LocalDateTime ultimoLogin;
    private String token;
    private boolean activo;

    public String getId() {
        return id;
    }

    public LocalDateTime getCreado() {
        return creado;
    }

    public LocalDateTime getModificado() {
        return modificado;
    }

    public LocalDateTime getUltimoLogin() {
        return ultimoLogin;
    }

    public User setId(String id) {
        this.id = id;
        return this;
    }

    public String getCorreo() {
        return correo;
    }

    public User setCorreo(String correo) {
        this.correo = correo;
        return this;
    }

    public String getContraseña() {
        return contraseña;
    }

    public User setContraseña(String contraseña) {
        this.contraseña = contraseña;
        return this;
    }

    public List<Phone> getTelefonos() {
        return telefonos;
    }

    public User setTelefonos(List<Phone> telefonos) {
        this.telefonos = telefonos;
        return this;
    }

    public User setRole(Role role) {
        this.role = role;
        return this;
    }

    public User setCreado(LocalDateTime creado) {
        this.creado = creado;
        return this;
    }

    public User setModificado(LocalDateTime modificado) {
        this.modificado = modificado;
        return this;
    }

    public User setUltimoLogin(LocalDateTime ultimoLogin) {
        this.ultimoLogin = ultimoLogin;
        return this;
    }

    public User setToken(String token) {
        this.token = token;
        return this;
    }

    public User setActivo(boolean activo) {
        this.activo = activo;
        return this;
    }

    public String getToken() {
        return token;
    }

    public boolean isActivo() {
        return activo;
    }
    public Role getRole() {
        return role;
    }
}
