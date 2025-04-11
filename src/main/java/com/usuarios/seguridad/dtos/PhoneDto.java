package com.usuarios.seguridad.dtos;

import lombok.Data;

@Data
public class PhoneDto {
    private String numero;
    private String codigoCiudad;
    private String codigoPais;


    public String getNumero() {
        return numero;
    }

    public PhoneDto setNumero(String numero) {
        this.numero = numero;
        return this;
    }

    public String getCodigoCiudad() {
        return codigoCiudad;
    }

    public PhoneDto setCodigoCiudad(String codigoCiudad) {
        this.codigoCiudad = codigoCiudad;
        return this;
    }

    public String getCodigoPais() {
        return codigoPais;
    }

    public PhoneDto setCodigoPais(String codigoPais) {
        this.codigoPais = codigoPais;
        return this;
    }
}