# Sistema de Registro y Autenticación de Usuarios con JWT
Este proyecto es una API REST construida con **Java Spring Boot**, que permite el **registro**, **login** y **gestión de usuarios** usando **JWT (JSON Web Tokens)** para autenticación segura.
Ejecuta el proyecto con tu IDE o usando Maven: spring-boot:run
Accede a la consola H2 (opcional):
    URL: http://localhost:8092/h2-console
    JDBC URL: jdbc:h2:mem:testdb
    User: sa
    Password: (dejar en blanco)

    UNA VEZ ESTANDO DENTRO DE LA CONSOLA DEBES INSERTAR LOS ROLES DE USERS Y ADMIN 
    INSERT INTO role(name) VALUES ('ROLE_USER');
INSERT INTO role(name) VALUES ('ROLE_ADMIN');
Y DESPUES CONSULTAR SI ESTAN INSERTADOS 
SELECT *FROM ROLE;
SELECT * FROM USERS;
YA CON ESOS INSERT PUEDEN PROBAR LOS ENDPOINT    

    *Endpoints principales*
    Registro de usuario
        POST /auth/register
        Body:
        {
  "userName": "juan",
  "correo": "juan@gmail.com",
  "contrasena": "Clave123",
  "telefonos": [
   {
   
      "numero": "12345678",
      "codigoCiudad": "1",
"codigoPais": "56"
    }
  ]
}

    Login de usuario
        POST /auth/login

        debe coincidir con lo de la base de datos ok.
        Body:
        {
  "userName": "juan",
  "password": "Clave123"
}
Respuesta: token JWT(AQUI GENERA UN TOKEN EL CUAL SE OCUPARA EN LOS SIGUIENTES ENDPOINT)
 Consultar usuario por ID (requiere token)
GET /users/{id}

Header: Authorization: Bearer <EL TOKEN GENERADO EN EL LOGIN>

Validaciones importantes
Correo: formato válido (@dominio.com o @dominio.cl)
Contraseña: mínimo 8 caracteres, 1 mayúscula, 1 minúscula y 1 número
El correo y el userName no pueden repetirse

EL check-auth (SOLO AGREGAR EL TOKEN OBTENIDO DEL LOGIN )

EL /user/{id} es el que esta en la base de datos, formato UUID


