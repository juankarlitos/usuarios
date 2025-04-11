package com.usuarios.seguridad.mapper;

import com.usuarios.seguridad.dtos.PhoneDto;
import com.usuarios.seguridad.entities.Phone;
import com.usuarios.seguridad.entities.User;
import java.util.List;
import java.util.stream.Collectors;

public class PhoneMapper {

    private PhoneMapper() {
        throw new IllegalStateException("Mapper class");
    }

    public static List<Phone> mapToEntityList(List<PhoneDto> phoneDtos, User user) {
        return phoneDtos.stream().map(p -> {
            Phone phone = new Phone();
            phone.setNumero(p.getNumero());
            phone.setCodigoCiudad(p.getCodigoCiudad());
            phone.setCodigoPais(p.getCodigoPais());
            phone.setUser(user);
            return phone;
        }).collect(Collectors.toList());
    }
}