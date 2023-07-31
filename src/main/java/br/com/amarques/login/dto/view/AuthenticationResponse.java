package br.com.amarques.login.dto.view;

public record AuthenticationResponse(String accessToken, String refreshToken) {
}