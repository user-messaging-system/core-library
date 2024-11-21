package com.user_messaging_system.core_library.service;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.*;
import java.util.function.Function;

@Component
public class JWTService {
    private static final String SECRET_KEY = "05548d6d1cbd0d0f1c43332823ed32943dac7db78257fd7529c627e7e1e6e807";
    private static final SecretKey SIGNING_KEY = getSigningKey();

    public String generateJwtToken(String email, String id, List<String> roles) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + 5 * 24 * 60 * 60 * 1000L); // 5 days

        return Jwts.builder()
                .subject(email)
                .claim("userId", id)
                .claim("roles", roles)
                .issuedAt(now)
                .expiration(expiration)
                .signWith(SIGNING_KEY)
                .compact();
    }

    public void validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(SIGNING_KEY)
                    .build()
                    .parseSignedClaims(token);
        } catch (ExpiredJwtException e) {
            throw new JwtException("Token validation failed - Token is expired.", e);
        } catch (UnsupportedJwtException e) {
            throw new JwtException("Token validation failed - Unsupported JWT token.", e);
        } catch (MalformedJwtException e) {
            throw new JwtException("Token validation failed - Malformed JWT token.", e);
        } catch (SecurityException e) {
            throw new JwtException("Token validation failed - Invalid JWT signature.", e);
        } catch (IllegalArgumentException e) {
            throw new JwtException("Token validation failed - JWT token is invalid.", e);
        }
    }

    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public String extractUserId(String token) {
        return extractClaim(token, claims -> claims.get("userId", String.class));
    }

    public Collection<GrantedAuthority> extractRoles(String token) {
        return extractClaim(token, this::extractRolesFromClaims);
    }

    private List<GrantedAuthority> extractRolesFromClaims(Claims claims) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        Object rolesObject = claims.get("roles");
        if (rolesObject instanceof List<?>) {
            for (Object role : (List<?>) rolesObject) {
                if (role instanceof String) {
                    authorities.add(new SimpleGrantedAuthority(String.valueOf(role)));
                }
            }
        }
        return authorities;
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        try {
            return Jwts.parser()
                    .verifyWith(SIGNING_KEY)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (JwtException e) {
            throw new JwtException("Failed to extract claims from token.", e);
        }
    }

    public String extractToken(String bearerToken) {
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        throw new IllegalArgumentException("Invalid Bearer token format");
    }

    private static SecretKey getSigningKey() {
        byte[] keyBytes = hexStringToByteArray(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private static byte[] hexStringToByteArray(String hex) {
        int len = hex.length();
        if (len % 2 != 0) {
            throw new IllegalArgumentException("Hex string length must be even");
        }
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4)
                    + Character.digit(hex.charAt(i + 1), 16));
        }
        return data;
    }
}
