package br.com.amarques.login.service;

import br.com.amarques.login.domain.Token;
import br.com.amarques.login.domain.User;
import br.com.amarques.login.dto.form.AuthenticationRequest;
import br.com.amarques.login.dto.form.RefreshTokenForm;
import br.com.amarques.login.dto.form.RegisterRequest;
import br.com.amarques.login.dto.view.AuthenticationResponse;
import br.com.amarques.login.dto.view.UserView;
import br.com.amarques.login.exceptions.ExpiredTokenException;
import br.com.amarques.login.exceptions.InvalidTokenException;
import br.com.amarques.login.exceptions.NotFoundException;
import br.com.amarques.login.repository.TokenRepository;
import br.com.amarques.login.repository.UserRepository;
import br.com.amarques.login.security.AccessTokenService;
import br.com.amarques.login.security.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthenticationService {
    private final UserRepository repository;
    private final AccessTokenService accessTokenService;
    private final RefreshTokenService refreshTokenService;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Transactional
    public UserView register(RegisterRequest request) {
        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        repository.save(user);
        return new UserView(user.getId(), user.getFirstname(), user.getLastname(), user.getEmail());
    }

    @Transactional
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.password()));

        var user = getUserByEmail(request.email());
        return createNewTokensAndRevokeTheOldOnes(user);
    }

    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());

        if (validUserTokens.isEmpty()) return;

        validUserTokens.forEach(token -> token.setRevoked(true));
        tokenRepository.saveAll(validUserTokens);
    }

    @Transactional
    public AuthenticationResponse refreshToken(RefreshTokenForm refreshTokenForm) {
        final String userEmail = refreshTokenService.extractUsername(refreshTokenForm.refreshToken());

        if (Objects.isNull(userEmail) || userEmail.isEmpty()) {
            throw new InvalidTokenException("Invalid token. Could not retrieve the user's email");
        }

        final var user = getUserByEmail(userEmail);

        if (!refreshTokenService.isTokenValid(refreshTokenForm.refreshToken(), user)) {
            throw new ExpiredTokenException("Invalid token. The token is expired");
        }

        return createNewTokensAndRevokeTheOldOnes(user);
    }

    private User getUserByEmail(String email) {
        return repository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User [email: %s ] not found".formatted(email)));
    }

    private AuthenticationResponse createNewTokensAndRevokeTheOldOnes(User user) {
        final var accessToken = accessTokenService.generateToken(user);
        final var refreshToken = refreshTokenService.generateToken(user);

        revokeAllUserTokens(user);
        saveUserToken(user, accessToken);

        return new AuthenticationResponse(accessToken, refreshToken);
    }

    public UserView me(String refreshToken) {
        final String email = accessTokenService.extractUsername(refreshToken);
        final User user = getUserByEmail(email);
        return new UserView(user.getId(), user.getFirstname(), user.getLastname(), user.getEmail());
    }
}
