package com.vegeta2001.jwtspring.config;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService {

    @Value("${vegeta2001.app.jwt-secret}")
    private String SECRET_KEY;

    @Value("${vegeta2001.app.accessExpirationTime}")
    private Integer accessExpirationTime;

    @Value("${vegeta2001.app.refreshExpirationTime}")
    private Integer refreshExpirationTime;

    public String generateToken(
        UserDetails userDetails
    ) {
        return buildToken(new HashMap<>(), userDetails, accessExpirationTime);
    }

    public String generateToken(
        Map<String, Object> extraClaims,
        UserDetails userDetails
    ) {
        return buildToken(extraClaims, userDetails, accessExpirationTime);
    }

    public String generateRefreshToken(
        UserDetails userDetails
    ) {
        return buildToken(new HashMap<>(), userDetails, refreshExpirationTime);
    }

    public String buildToken(
        Map<String, Object> extraClaims,
        UserDetails userDetails,
        Integer expirationTime
    ) {
        return Jwts
            .builder()
            .setClaims(extraClaims)
            .setSubject(userDetails.getUsername())
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
            .signWith(getSignInKey(), SignatureAlgorithm.HS256)
            .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return ((username.equals(userDetails.getUsername())) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date(System.currentTimeMillis()));
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    public Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
