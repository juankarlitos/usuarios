package com.usuarios.seguridad.dtos;

import com.usuarios.seguridad.enums.RoleList;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import java.util.List;

@Data
public class UserRequestDto {

    @NotBlank(message = "El nombre de usuario es obligatorio")
    private String userName;

    public String getUserName() {
        return userName;
    }

    public UserRequestDto setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getCorreo() {
        return correo;
    }

    public UserRequestDto setCorreo(String correo) {
        this.correo = correo;
        return this;
    }

    public String getContrasena() {
        return contrasena;
    }

    public UserRequestDto setContrasena(String contrasena) {
        this.contrasena = contrasena;
        return this;
    }

    public List<PhoneDto> getTelefonos() {
        return telefonos;
    }

    public UserRequestDto setTelefonos(List<PhoneDto> telefonos) {
        this.telefonos = telefonos;
        return this;
    }

    private RoleList role;

    public RoleList getRole() {
        return role;
    }

    public UserRequestDto setRole(RoleList role) {
        this.role = role;
        return this;
    }

    @NotBlank(message = "El correo no puede estar vacío")
    @Pattern(
            regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.(com|cl)$",
            message = "Correo no Valido"
    )
    private String correo;

    @NotBlank(message = "La contraseña no puede estar vacía")
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{8,}$",
            message = "La contraseña debe tener al menos 8 caracteres, una mayúscula, una minúscula y un número"
    )
    private String contrasena;

    private List<PhoneDto> telefonos;
}