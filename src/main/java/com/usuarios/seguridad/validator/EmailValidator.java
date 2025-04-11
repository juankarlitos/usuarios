package com.usuarios.seguridad.validator;

public class EmailValidator {

    public static boolean isValidEmail(String email) {
        if (email == null || email.isBlank()) {
            return false;
        }

        // Expresión regular directamente
        String regex = "^[A-Za-z0-9+_.-]+@(.+)\\.(com|cl)$";

        return email.matches(regex);
    }
}