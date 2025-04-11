package com.usuarios.seguridad.paswordConfig;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "password")
@Data
public class PasswordProperties {
    private String regex;
}
