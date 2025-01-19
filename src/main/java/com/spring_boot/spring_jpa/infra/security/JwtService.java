package com.spring_boot.spring_jpa.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.spring_boot.spring_jpa.domain.User;
import com.spring_boot.spring_jpa.errors.TokenErrorException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secret;

    public String generatedToken(User user) {
     try{
         Instant now = Instant.now();
         Algorithm algorithm = Algorithm.HMAC256(this.secret);
         String token = JWT.create()
                 .withIssuer("spring-jpa")
                 .withIssuedAt(now)
                 .withSubject(user.getEmail())
                 .withExpiresAt(getExpiryDate())
                 .sign(algorithm);
         return token;
     }catch(JWTCreationException e){
        throw new TokenErrorException("Erro ao gerar o token da aplicação");
     }
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(this.secret);
            return JWT.require(algorithm)
                    .withIssuer("spring-jpa")
                    .build()
                    .verify(token)
                    .getSubject();

        } catch (JWTVerificationException exception){
            throw new TokenErrorException("Token inválido");
        }
    }

    private Instant getExpiryDate() {
        return LocalDateTime.now().plusHours(8).toInstant(ZoneOffset.of("-03:00"));
    }
}
