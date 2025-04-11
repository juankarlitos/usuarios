package com.usuarios.seguridad.entities;

import com.usuarios.seguridad.enums.RoleList;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public Integer getId() {
        return id;
    }

    public Role setId(Integer id) {
        this.id = id;
        return this;
    }

    public RoleList getName() {
        return name;
    }

    public Role setName(RoleList name) {
        this.name = name;
        return this;
    }


    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.STRING) // ESTA LÍNEA ES CLAVE
    private RoleList name;
}