package com.rw.studentMs.config.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.rw.studentMs.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenProvider {
    @Value("${security.jwt.token.secret-key}")
    private String JWT_SECRET;

    public String generateAccessToken(User user) {
        try{
            Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET);
            return JWT.create()
                    .withSubject(user.getUsername())
                    .withClaim("username", user.getUsername())
                    .sign(algorithm);
        } catch (JWTCreationException e) {
           throw new JWTCreationException("Error while creating JWT token",e);
        }
    }

    public String validateToken(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET);
            return JWT.require(algorithm).build().verify(token).getSubject();
        } catch (JWTVerificationException e) {
            throw new JWTVerificationException("Error while validating JWT token",e);
        }
    }

    private Instant getAccessExpirationDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
