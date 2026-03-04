package com.cts.authservice.security;

import com.cts.authservice.entity.Role;
import com.cts.authservice.entity.User;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long validityInMs;

    private Key key;

    @PostConstruct
    protected void init(){
        key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String generateToken(User user){
        Date now = new Date();
        Date expiry = new Date(now.getTime() +validityInMs);

//        return Jwts.builder()
//                .setSubject(email)
//                .setIssuedAt(now)
//                .setExpiration(expiry)
//                .signWith(key)
//                .compact();
        Role role = user.getRoles().iterator().next();
        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("userId",user.getUserId())
                .claim("role",role.getName().name())
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(key)
                .compact();
    }

    public String getEmailFromToken(String token){
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token){
        try{
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        }catch (JwtException | IllegalArgumentException e){
            return false;
        }
    }
}
