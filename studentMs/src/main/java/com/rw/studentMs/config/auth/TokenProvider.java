package com.rw.studentMs.config.auth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TokenProvider {
    @Value("${security.jwt.token.secret-key}")
    private String JWT_SECRET;
}
