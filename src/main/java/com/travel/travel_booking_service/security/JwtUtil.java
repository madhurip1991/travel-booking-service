package com.travel.travel_booking_service.security;

import io.jsonwebtoken.Jwts;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;
import java.util.Date;


public class JwtUtil {
    private static final String SECRET_STRING = "ThisIsAVeryLongAndSecureSecretKeyThatMustBeSharedAcrossApplications";
    private static final Key key;
    static {
        // Ensure the key is at least 256 bits (32 bytes) for HS256 algorithm
        byte[] decodedKey = Base64.getEncoder().encode(SECRET_STRING.getBytes());
        key = new SecretKeySpec(decodedKey, 0, decodedKey.length, "HmacSHA256");
    }
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
