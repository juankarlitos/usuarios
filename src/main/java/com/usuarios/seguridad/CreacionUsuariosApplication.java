package com.usuarios.seguridad;

import com.usuarios.seguridad.regexProperties.RegexProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@SpringBootApplication
@EnableConfigurationProperties(RegexProperties.class)
public class CreacionUsuariosApplication {

	public static void main(String[] args) {
		SpringApplication.run(CreacionUsuariosApplication.class, args);
	}

}
