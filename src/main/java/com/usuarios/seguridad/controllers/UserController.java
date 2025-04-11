package com.usuarios.seguridad.controllers;

import com.usuarios.seguridad.dtos.UserResponseDto;
import com.usuarios.seguridad.dtos.UserRequestDto;
import com.usuarios.seguridad.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getById(@PathVariable String id) {
        return ResponseEntity.ok(userService.getById(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> update(
            @PathVariable String id,
            @RequestBody @Valid UserRequestDto dto) {
        return ResponseEntity.ok(userService.update(id, dto));
    }
    @PatchMapping("/{id}")
    public ResponseEntity<UserResponseDto> patch(
            @PathVariable String id,
            @RequestBody Map<String, Object> updates) {
        return ResponseEntity.ok(userService.patch(id, updates));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}