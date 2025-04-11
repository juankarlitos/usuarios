package com.usuarios.seguridad.mapper;
import com.usuarios.seguridad.dtos.UserResponseDto;
import com.usuarios.seguridad.entities.User;

public class UsuarioMapper {

    public static UserResponseDto mapToResponse(User user){

        return new UserResponseDto(
                user.getId(),
                user.getCreado(),
                user.getModificado(),
                user.getUltimoLogin(),
                user.getToken(),
                user.isActivo()
        );
}
}
