package com.communityhub.userservice.config.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.security.Key;

@Service
public class TokenService {

    private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public String generateToken(String email) {

        Date now = new Date();

        Date expirationTime = new Date(System.currentTimeMillis() + 7200000);

        return Jwts.builder().setSubject(email)
                .setIssuedAt(now).setExpiration(expirationTime)
                .signWith(SECRET_KEY).compact();
    }
}
