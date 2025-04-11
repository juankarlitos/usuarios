package com.usuarios.seguridad.services;

import com.usuarios.seguridad.dtos.UserResponseDto;
import com.usuarios.seguridad.dtos.UserRequestDto;
import com.usuarios.seguridad.entities.Phone;
import com.usuarios.seguridad.entities.User;
import com.usuarios.seguridad.mapper.PhoneMapper;
import com.usuarios.seguridad.mapper.UsuarioMapper;
import com.usuarios.seguridad.repositories.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class UserService implements UserDetailsService {

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        System.out.println("Buscando userName: " + userName);
        User user = userRepository.findByUserName(userName)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().getName().toString());

        return new org.springframework.security.core.userdetails.User(
                user.getUserName(),
                user.getContraseña(),
                Collections.singleton(authority)
        );
    }
    public UserResponseDto getById(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado"));

        return UsuarioMapper.mapToResponse(user);
    }

    public UserResponseDto update(String id, UserRequestDto dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado"));

        user.setCorreo(dto.getCorreo());
        user.setContraseña(passwordEncoder.encode(dto.getContrasena()));
        user.setModificado(LocalDateTime.now());
        user.getTelefonos().clear();
        List<Phone> nuevosTelefonos = PhoneMapper.mapToEntityList(dto.getTelefonos(), user);

        user.getTelefonos().addAll(nuevosTelefonos);
        userRepository.save(user);
        return UsuarioMapper.mapToResponse(user);
    }
    public void delete(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado"));

        user.setActivo(false); // o userRepository.delete(user); si querés borrarlo físicamente
        user.setModificado(LocalDateTime.now());
        userRepository.save(user);
    }
    public UserResponseDto patch(String id, Map<String, Object> updates) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado"));

        if (updates.containsKey("correo")) {
            user.setCorreo((String) updates.get("correo"));
        }
        if (updates.containsKey("contraseña")) {
            user.setContraseña(passwordEncoder.encode((String) updates.get("contraseña")));
        }
        user.setModificado(LocalDateTime.now());
        userRepository.save(user);
        return UsuarioMapper.mapToResponse(user);
    }
    public boolean existsByUserName(String username){
        return userRepository.existsByUserName(username);
    }

    public void save(User user){
        userRepository.save(user);
    }

    public boolean existsByCorreo(String correo) {
        return userRepository.existsByCorreo(correo);
    }
}