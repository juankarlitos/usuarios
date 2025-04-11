package com.usuarios.seguridad.dtos;

import java.time.LocalDateTime;

public class UserResponseDto{

    public UserResponseDto(String id, LocalDateTime creado, LocalDateTime modificado,
                           LocalDateTime ultimoLogin, String token, boolean activo) {
        this.id = id;
        this.creado = creado;
        this.modificado = modificado;
        this.ultimoLogin = ultimoLogin;
        this.token = token;
        this.activo = activo;
    }

    public UserResponseDto() {
    }

    private String id;
    private LocalDateTime creado;
    private LocalDateTime modificado;
    private LocalDateTime ultimoLogin;
    private String token;
    private boolean activo;

    public String getId() {
        return id;
    }

    public UserResponseDto setId(String id) {
        this.id = id;
        return this;
    }

    public LocalDateTime getCreado() {
        return creado;
    }

    public UserResponseDto setCreado(LocalDateTime creado) {
        this.creado = creado;
        return this;
    }

    public LocalDateTime getModificado() {
        return modificado;
    }

    public UserResponseDto setModificado(LocalDateTime modificado) {
        this.modificado = modificado;
        return this;
    }

    public LocalDateTime getUltimoLogin() {
        return ultimoLogin;
    }

    public UserResponseDto setUltimoLogin(LocalDateTime ultimoLogin) {
        this.ultimoLogin = ultimoLogin;
        return this;
    }

    public String getToken() {
        return token;
    }

    public UserResponseDto setToken(String token) {
        this.token = token;
        return this;
    }

    public boolean isActivo() {
        return activo;
    }

    public UserResponseDto setActivo(boolean activo) {
        this.activo = activo;
        return this;
    }
}