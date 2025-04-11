package com.usuarios.seguridad.repositories;

import com.usuarios.seguridad.entities.Role;
import com.usuarios.seguridad.enums.RoleList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByName(RoleList name);
}