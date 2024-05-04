package com.rasaboga.RasaBoga.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.rasaboga.RasaBoga.entity.UserCredential;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;
import java.util.stream.Stream;

@Component
public class JwtUtils {

    @Value("${app.restoran.jwt-secret}")
    private String secretKey;
    @Value("${app.restoran.jwt-expiration}")
    private long expirationInSecond;
    @Value("${app.restoran.app-name}")
    private String appName;

    public String generationToken(UserCredential userCredential){
        try {
            Algorithm algorithm = Algorithm.HMAC512(secretKey);
            List<String> stream = userCredential.getRoles().stream().map(role -> role.getRole().name()).toList();
            return JWT.create()
                    .withIssuer(appName)
                    .withSubject(userCredential.getId())
                    .withExpiresAt(Instant.now().plusSeconds(expirationInSecond))
                    .withClaim("roles", stream)
                    .sign(algorithm);
        }catch (JWTCreationException e){
            throw new RuntimeException(e);
        }
    }
}
