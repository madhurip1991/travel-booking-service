package com.travel.travel_booking_service.security;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;


public class JwtUtil {

    // Shared secret key (store securely in production)
    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // Generate JWT token for a service
    public static String generateToken(String serviceName) {
        return Jwts.builder()
                .setSubject(serviceName)
                .claim("role", "webhook-caller")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 5 * 60 * 1000)) // 5 min
                .signWith(key)
                .compact();
    }

    // Validate JWT token
    public static void validateToken(String token) {
        Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);
    }
}
