package br.com.amarques.login.resource;

import br.com.amarques.login.dto.form.AuthenticationRequest;
import br.com.amarques.login.dto.form.RefreshTokenForm;
import br.com.amarques.login.dto.form.RegisterRequest;
import br.com.amarques.login.dto.view.AuthenticationResponse;
import br.com.amarques.login.dto.view.UserView;
import br.com.amarques.login.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Register and Authentication")
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/register")
    @Operation(summary = "Register a new user")
    public ResponseEntity<UserView> register(@Valid @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    @Operation(summary = "Authenticates a user")
    public ResponseEntity<AuthenticationResponse> authenticate(@Valid @RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(service.authenticate(request));
    }

    @PostMapping("/refresh-token")
    @Operation(summary = "Updates the tokens")
    public ResponseEntity<AuthenticationResponse> refreshToken(@Valid @RequestBody RefreshTokenForm refreshTokenForm) {
        return ResponseEntity.ok(service.refreshToken(refreshTokenForm));
    }
}
