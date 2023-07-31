package br.com.amarques.login.resource;

import br.com.amarques.login.dto.view.UserView;
import br.com.amarques.login.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "User information")
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class MeController {

    private final AuthenticationService service;

    @GetMapping("/me")
    @Operation(summary = "Returns the data of the user who is logged in")
    public ResponseEntity<UserView> me(HttpServletRequest request) {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken = authHeader.substring(7);
        return ResponseEntity.ok(service.me(refreshToken));
    }
}
