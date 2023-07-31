package br.com.amarques.login.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RefreshTokenService extends TokenService {

    public RefreshTokenService(
            @Value("${application.security.jwt.refresh-token.secret-key}")
            String secretKey,
            @Value("${application.security.jwt.refresh-token.expiration}")
            long expiration) {
        this.secretKey = secretKey;
        this.expiration = expiration;
    }
}
