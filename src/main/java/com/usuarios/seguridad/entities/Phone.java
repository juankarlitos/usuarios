package com.usuarios.seguridad.entities;

import jakarta.persistence.*;

@Entity
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    public Phone(String id, String numero, String codigoCiudad, String codigoPais, User user) {
        this.id = id;
        this.numero = numero;
        this.codigoCiudad = codigoCiudad;
        this.codigoPais = codigoPais;
        this.user = user;
    }

    public Phone() {
    }

    public String getId() {
        return id;
    }

    public String getNumero() {
        return numero;
    }

    public String getCodigoCiudad() {
        return codigoCiudad;
    }

    public String getCodigoPais() {
        return codigoPais;
    }

    public User getUser() {
        return user;
    }

    public Phone setId(String id) {
        this.id = id;
        return this;
    }

    public Phone setNumero(String numero) {
        this.numero = numero;
        return this;
    }

    public Phone setCodigoCiudad(String codigoCiudad) {
        this.codigoCiudad = codigoCiudad;
        return this;
    }

    public Phone setCodigoPais(String codigoPais) {
        this.codigoPais = codigoPais;
        return this;
    }

    public Phone setUser(User user) {
        this.user = user;
        return this;
    }

    private String numero;
    private String codigoCiudad;
    private String codigoPais;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}