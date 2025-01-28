package com.rezende.learn.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.rezende.learn.entities.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.*;


@Service
public class TokenService {

    @Value("${security.client-secret}")
    private String secret;

    @Value("${security.jwt.duration}")
    private String expiration;

    public String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token =
                    JWT.create()
                    .withIssuer("learn")
                    .withSubject(user.getEmail())
                    .withExpiresAt(getExpirationDate())
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException e) {
            throw new RuntimeException("Error while generating token", e);
        }
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("learn")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException e) {
            return "";
        }
    }

    private Instant createdAt() {
        return ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")).toInstant();
    }

    private Instant expiration() {
        return ZonedDateTime.now(ZoneId.of("America/Sao_Paulo"))
                .plusHours(Long.parseLong(expiration)).toInstant();
    }

    private Instant getExpirationDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
