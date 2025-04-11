package com.usuarios.seguridad.services;

import com.usuarios.seguridad.dtos.UserResponseDto;
import com.usuarios.seguridad.dtos.UserRequestDto;
import com.usuarios.seguridad.entities.Phone;
import com.usuarios.seguridad.entities.Role;
import com.usuarios.seguridad.entities.User;
import com.usuarios.seguridad.enums.RoleList;
import com.usuarios.seguridad.jwt.JwtUtil;
import com.usuarios.seguridad.mapper.UsuarioMapper;
import com.usuarios.seguridad.repositories.RoleRepository;
import com.usuarios.seguridad.validator.EmailValidator;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthService {
    private final UserService userService;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public AuthService(UserService userService, RoleRepository roleRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }
    public String authenticate(String username, String password) {

        System.out.println("Intentando autenticar con: " + username + " / " + password);
        System.out.println("userName recibido: " + username);
        System.out.println("password recibido: " + password);

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(username, password);

        Authentication authResult = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authResult);
        return jwtUtil.generateToken(authResult);
    }

    @Transactional
    public UserResponseDto registerUser(UserRequestDto dto) {
        if (userService.existsByCorreo(dto.getCorreo())) {
            throw new IllegalArgumentException("El correo ya está registrado");
        }
        if (!EmailValidator.isValidEmail(dto.getCorreo())) {
            throw new IllegalArgumentException("Correo inválido");
        }
        if (userService.existsByUserName(dto.getUserName())) {
            throw new IllegalArgumentException("El nombre de usuario ya está registrado");
        }
        RoleList rolSolicitado = dto.getRole() != null ? dto.getRole() : RoleList.ROLE_USER;

        Role role = roleRepository.findByName(rolSolicitado)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

        User user = new User();
        user.setUserName(dto.getUserName());
        user.setCorreo(dto.getCorreo());
        user.setContraseña(passwordEncoder.encode(dto.getContrasena()));
        user.setRole(role);
        user.setActivo(true);

        LocalDateTime now = LocalDateTime.now();
        user.setCreado(now);
        user.setModificado(now);
        user.setUltimoLogin(now);

        List<Phone> telefonos = dto.getTelefonos().stream().map(p -> {
            Phone phone = new Phone();
            phone.setNumero(p.getNumero());
            phone.setCodigoCiudad(p.getCodigoCiudad());
            phone.setCodigoPais(p.getCodigoPais());
            phone.setUser(user);
            return phone;
        }).collect(Collectors.toList());

        user.setTelefonos(telefonos);

        userService.save(user);

        UserDetails userDetails = userService.loadUserByUsername(dto.getUserName());

        Authentication auth = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities()
        );
        String token = jwtUtil.generateToken(auth);
        user.setToken(token);
        userService.save(user);
        return UsuarioMapper.mapToResponse(user);
    }
}