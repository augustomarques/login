package br.com.amarques.login.dto.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record AuthenticationRequest(
        @Email
        String email,
        @NotEmpty(message = "The [password] cannot be empty")
        String password) {
}
