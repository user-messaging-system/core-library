package com.user_messaging_system.core_library.service;


import com.auth0.jwt.exceptions.JWTVerificationException;
import com.user_messaging_system.core_library.exception.UnauthorizedException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.crypto.SecretKey;
import java.util.*;
import java.util.function.Function;

@Component
public class JWTService {
    private static final Logger logger = LoggerFactory.getLogger(JWTService.class);
    private static final String SECRET_KEY = "05548d6d1cbd0d0f1c43332823ed32943dac7db78257fd7529c627e7e1e6e807";
    private static final SecretKey SIGNING_KEY = getSignInKey();

    public String generateJwtToken(String email, String id, List<String> roles) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + 5 * 24 * 60 * 60 * 1000L); // 5 days

        String token = Jwts.builder()
            .setSubject(email)
            .claim("userId", id)
            .claim("roles", roles)
            .setIssuedAt(now)
            .setExpiration(expiration)
            .signWith(SIGNING_KEY, SignatureAlgorithm.HS256)
            .compact();

        logger.info("Generated JWT token for user ID: {}", id);
        return token;
    }

    public void validateToken(String token) {
        try {
            Jwts.parser()
                .setSigningKey(SIGNING_KEY)
                .build()
                .parseClaimsJws(token);
            logger.info("Token validated successfully.");
        } catch (ExpiredJwtException e) {
            logger.error("Token is expired: {}", e.getMessage());
            throw new JWTVerificationException("Token is expired.", e);
        } catch (UnsupportedJwtException e) {
            logger.error("Unsupported JWT token: {}", e.getMessage());
            throw new JWTVerificationException("Unsupported JWT token.", e);
        } catch (MalformedJwtException e) {
            logger.error("Malformed JWT token: {}", e.getMessage());
            throw new JWTVerificationException("Malformed JWT token.", e);
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature: {}", e.getMessage());
            throw new JWTVerificationException("Invalid JWT signature.", e);
        } catch (IllegalArgumentException e) {
            logger.error("JWT token compact of handler are invalid: {}", e.getMessage());
            throw new JWTVerificationException("JWT token is invalid.", e);
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

    public String extractToken(String bearerToken) {
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            String token = bearerToken.substring(7);
            logger.debug("Extracted token: {}", token);
            return token;
        }
        logger.error("Authorization header is missing or does not start with Bearer.");
        throw new UnauthorizedException("Authorization header is missing or invalid");
    }

    private List<GrantedAuthority> extractRolesFromClaims(Claims claims) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        Object rolesObject = claims.get("roles");
        if (rolesObject instanceof List<?>) {
            for (Object role : (List<?>) rolesObject) {
                if (role instanceof String) {
                    authorities.add(new SimpleGrantedAuthority((String) role));
                } else {
                    logger.warn("Role is not a String: {}", role);
                }
            }
        } else {
            logger.warn("Roles claim is not a list: {}", rolesObject);
        }
        return authorities;
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                .setSigningKey(SIGNING_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
            logger.debug("Extracted claims: {}", claims);
            return claims;
        } catch (ExpiredJwtException e) {
            logger.error("Failed to extract claims - Token is expired: {}", e.getMessage());
            throw new JWTVerificationException("Token is expired.", e);
        } catch (UnsupportedJwtException e) {
            logger.error("Failed to extract claims - Unsupported JWT: {}", e.getMessage());
            throw new JWTVerificationException("Unsupported JWT token.", e);
        } catch (MalformedJwtException e) {
            logger.error("Failed to extract claims - Malformed JWT: {}", e.getMessage());
            throw new JWTVerificationException("Malformed JWT token.", e);
        } catch (SignatureException e) {
            logger.error("Failed to extract claims - Invalid signature: {}", e.getMessage());
            throw new JWTVerificationException("Invalid JWT signature.", e);
        } catch (IllegalArgumentException e) {
            logger.error("Failed to extract claims - Token is null or empty: {}", e.getMessage());
            throw new JWTVerificationException("JWT token is invalid.", e);
        }
    }

    private void validateTokenIsExpired(String token) {
        Date expiration = extractExpiration(token);
        if (expiration.before(new Date())) {
            throw new JWTVerificationException("Token is expired");
        }
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private static SecretKey getSignInKey() {
        try {
            byte[] keyBytes = hexStringToByteArray(SECRET_KEY);
            return Keys.hmacShaKeyFor(keyBytes);
        } catch (Exception e) {
            logger.error("Failed to generate signing key: {}", e.getMessage());
            throw new IllegalStateException("Cannot generate signing key", e);
        }
    }

    private static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        if (len % 2 != 0) {
            throw new IllegalArgumentException("Invalid SECRET_KEY length.");
        }
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            int firstDigit = Character.digit(s.charAt(i), 16);
            int secondDigit = Character.digit(s.charAt(i + 1), 16);
            if (firstDigit == -1 || secondDigit == -1) {
                throw new IllegalArgumentException("Invalid hex character in SECRET_KEY.");
            }
            data[i / 2] = (byte) ((firstDigit << 4) + secondDigit);
        }
        return data;
    }
}
