package br.com.amarques.login.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AccessTokenService extends TokenService {

    public AccessTokenService(
            @Value("${application.security.jwt.access-token.secret-key}") String secretKey,
            @Value("${application.security.jwt.access-token.expiration}") long expiration) {
        this.secretKey = secretKey;
        this.expiration = expiration;
    }
}
