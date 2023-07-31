package br.com.amarques.login.dto.view;

public record UserView(
        Long id,
        String firstname,
        String lastname,
        String email
) {
}
