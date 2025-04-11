package com.usuarios.seguridad.regexProperties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "regex")
public class RegexProperties {
    private String email;
    private String password;
}
