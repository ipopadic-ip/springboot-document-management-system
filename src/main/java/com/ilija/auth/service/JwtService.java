package com.ilija.auth.service;

import io.jsonwebtoken.*;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import com.ilija.model.Korisnik;
import com.ilija.model.Role;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JwtService {

    private final String SECRET_KEY = Base64.getEncoder()
            .encodeToString("tajni_kljuc_koji_je_dovoljno_dug_123456".getBytes());    
    
    private final long EXPIRATION_TIME = 1000 * 60 * 60 * 10; // 10h

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

//    public String generateToken(Korisnik korisnik) {
//        return Jwts.builder()
//        		.claim("id", korisnik.getId())
//                .claim("korisnickoIme", korisnik.getKorisnickoIme())
//
//                .setSubject(korisnik.getKorisnickoIme())
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
//                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
//                .compact();
//    }
    
    public String generateToken(Korisnik korisnik) {
        List<String> roleNames = korisnik.getUloge().stream()
            .map(Role::getName)
            .collect(Collectors.toList());

        return Jwts.builder()
            .claim("id", korisnik.getId())
            .claim("korisnickoIme", korisnik.getKorisnickoIme())
            .claim("uloge", roleNames)
            .setSubject(korisnik.getKorisnickoIme())
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
            .signWith(getSigningKey(), SignatureAlgorithm.HS256)
            .compact();
    }

    
    public Long extractId(String token) {
        Claims claims = Jwts.parserBuilder()
            .setSigningKey(getSigningKey())
            .build()
            .parseClaimsJws(token)
            .getBody();

        return claims.get("id", Integer.class).longValue();
    }



    public String extractKorisnickoIme(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean isTokenValid(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
    
    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }


}
