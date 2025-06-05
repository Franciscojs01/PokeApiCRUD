package com.example.pokemon.infra.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.pokemon.domain.Jogador;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    private static final String ISSUER = "auth-api";
    private static final int EXPIRATION_HOURS = 2;
    private static final ZoneOffset TIMEZONE_OFFSET = ZoneOffset.of("-03:00");

    public String generateToken(Jogador jogador) {
        try {
            return JWT.create()
                    .withIssuer(ISSUER)
                    .withSubject(jogador.getEmail())
                    .withExpiresAt(getExpirationInstant())
                    .sign(getSigningAlgorithm());
        } catch (JWTCreationException ex) {
            throw new RuntimeException("Erro ao gerar token", ex);
        }
    }

    public String validateToken(String token) {
        try {
            return JWT.require(getSigningAlgorithm())
                    .withIssuer(ISSUER)
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException ex) {
            return "";
        }
    }

    private Algorithm getSigningAlgorithm() {
        return Algorithm.HMAC256(secret);
    }

    private Instant getExpirationInstant() {
        return LocalDateTime.now().plusHours(EXPIRATION_HOURS).toInstant(TIMEZONE_OFFSET);
    }
}
