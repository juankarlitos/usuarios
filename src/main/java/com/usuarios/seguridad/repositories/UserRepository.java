package com.usuarios.seguridad.repositories;

import com.usuarios.seguridad.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    boolean existsByUserName(String userName);

    Optional<User> findByUserName(String userName);

    Optional<User> findByCorreo(String correo);

    boolean existsByCorreo(String correo);
}