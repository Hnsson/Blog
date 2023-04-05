package dev.hns.blog;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

public class JwtAuthentication {
    private static final SecretKey SECRET_KEY = Keys.hmacShaKeyFor("xM1R9cZe4MhL4jK3wS6nH8qU2bY5aG7tP0oFpDfVgJyKlN9i".getBytes(StandardCharsets.UTF_8));

    public static boolean validateToken(String jwtToken) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(jwtToken);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Extract future claims like (roles) to make decisions based on that instead of only validation
    public static Claims parseToken(String jwtToken) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(jwtToken)
                .getBody();
    }

    public static String createToken(String username, Date expiration) {
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(expiration)
                .signWith(SECRET_KEY)
                .compact();
    }
}
