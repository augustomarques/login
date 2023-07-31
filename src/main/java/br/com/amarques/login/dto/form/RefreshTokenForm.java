package br.com.amarques.login.dto.form;

import jakarta.validation.constraints.NotEmpty;

public record RefreshTokenForm(
        @NotEmpty(message = "The [refresh_token] cannot be empty")
        String refreshToken) {
}
