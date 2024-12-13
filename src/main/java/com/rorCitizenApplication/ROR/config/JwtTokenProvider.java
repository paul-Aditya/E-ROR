// JwtTokenProvider.java
package com.rorCitizenApplication.ROR.config;

import com.rorCitizenApplication.ROR.exception.InvalidTokenException;
import com.rorCitizenApplication.ROR.security.UserPrincipal;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class JwtTokenProvider {
    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private int jwtExpiration;

    @Value("${jwt.refresh-expiration}")
    private int refreshExpiration;

    public String generateToken(Authentication authentication) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(userPrincipal.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String generateRefreshToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + refreshExpiration))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String getUsernameFromToken(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(jwtSecret)
                    .parseClaimsJws(token)
                    .getBody();
            return claims.getSubject();
        } catch (Exception e) {
            throw new InvalidTokenException("Invalid JWT token");
        }
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (SignatureException ex) {
            throw new InvalidTokenException("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            throw new InvalidTokenException("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            throw new InvalidTokenException("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            throw new InvalidTokenException("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            throw new InvalidTokenException("JWT claims string is empty");
        }
    }

    public boolean isTokenExpiringSoon(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(jwtSecret)
                    .parseClaimsJws(token)
                    .getBody();
            Date expiration = claims.getExpiration();
            return (expiration.getTime() - System.currentTimeMillis()) < 300000; // 5 minutes
        } catch (Exception e) {
            return true;
        }
    }
}