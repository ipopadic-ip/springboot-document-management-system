package com.ilija.auth.service;

import jakarta.servlet.FilterChain;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ilija.auth.model.CustomUserDetails;
import com.ilija.model.Korisnik;
import com.ilija.repository.KorisnikRepository;

import io.jsonwebtoken.Claims;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final KorisnikRepository korisnikRepository;

    public JwtAuthFilter(JwtService jwtService, KorisnikRepository korisnikRepository) {
        this.jwtService = jwtService;
        this.korisnikRepository = korisnikRepository;
    }
    
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String jwt = authHeader.substring(7);

        // 1. Izvuci Claims iz JWT tokena
        Claims claims = jwtService.extractAllClaims(jwt);  // dodaj ovu metodu u JwtService ako već ne postoji

        String korisnickoIme = claims.getSubject();
        List<String> roleNames = claims.get("uloge", List.class);

        Set<GrantedAuthority> authorities = roleNames.stream()
            .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
            .collect(Collectors.toSet());

        UsernamePasswordAuthenticationToken authToken =
            new UsernamePasswordAuthenticationToken(korisnickoIme, null, authorities);

        SecurityContextHolder.getContext().setAuthentication(authToken);

        filterChain.doFilter(request, response);
    }


//    @Override
//    protected void doFilterInternal(HttpServletRequest request,
//                                    HttpServletResponse response,
//                                    FilterChain filterChain)
//            throws ServletException, IOException {
//
//        String authHeader = request.getHeader("Authorization");
//
//        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//        String jwt = authHeader.substring(7);
//        String korisnickoIme = jwtService.extractKorisnickoIme(jwt);
//        String korisnickoIme = claims.getSubject();
//        List<String> roleNames = claims.get("uloge", List.class);
//        
//        Set<GrantedAuthority> authorities = roleNames.stream()
//        	    .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
//        	    .collect(Collectors.toSet());
//
//        	UsernamePasswordAuthenticationToken authToken =
//        	    new UsernamePasswordAuthenticationToken(korisnickoIme, null, authorities);
//
//        	SecurityContextHolder.getContext().setAuthentication(authToken);
//
//        if (korisnickoIme != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//        	if (jwtService.isTokenValid(jwt)) {
//        	    Optional<Korisnik> korisnikOpt = korisnikRepository.findByKorisnickoIme(korisnickoIme);
//        	    if (korisnikOpt.isPresent()) {
//        	        Korisnik korisnik = korisnikOpt.get();
//
//        	        Set<GrantedAuthority> authorities = korisnik.getUloge().stream()
//        	            .map(role -> new SimpleGrantedAuthority("ROLE_" + role.name()))
//        	            .collect(Collectors.toSet());
//
//        	        UsernamePasswordAuthenticationToken authToken =
//        	            new UsernamePasswordAuthenticationToken(korisnik.getKorisnickoIme(), null, authorities);
//
//        	        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//        	        SecurityContextHolder.getContext().setAuthentication(authToken);
//        	    }
//        	}
//        }
//
//        filterChain.doFilter(request, response);
//    }
    
}
