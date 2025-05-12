package com.ilija.auth.service;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ilija.auth.dto.LoginRequest;
import com.ilija.auth.dto.LoginResponse;
import com.ilija.auth.dto.RegisterRequest;
import com.ilija.model.Autor;
import com.ilija.model.Korisnik;
//import com.ilija.model.Kupac;
import com.ilija.model.Role;
import com.ilija.repository.KorisnikRepository;
//import com.ilija.repository.KupacRepository;
import com.ilija.repository.RoleRepository;

@Service
public class AuthService {

    @Autowired private KorisnikRepository korisnikRepository;
    @Autowired private RoleRepository roleRepository;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private JwtService jwtService;
    @Autowired private AuthenticationManager authManager;

    public LoginResponse login(LoginRequest loginRequest) {
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getKorisnickoIme(),
                        loginRequest.getLozinka()
                )
        );

        Korisnik korisnik = korisnikRepository.findByKorisnickoIme(loginRequest.getKorisnickoIme())
                .orElseThrow(() -> new RuntimeException("Korisnik nije pronađen"));

        String jwt = jwtService.generateToken(korisnik);
        return new LoginResponse(jwt);
    }
    
//    public LoginResponse registerKupac(RegisterRequest request) {
//        if (korisnikRepository.existsByKorisnickoIme(request.getKorisnickoIme())) {
//            throw new RuntimeException("Korisničko ime već postoji.");
//        }
//
//        Kupac kupac = new Kupac();
//        kupac.setKorisnickoIme(request.getKorisnickoIme());
//        kupac.setLozinka(passwordEncoder.encode(request.getLozinka()));
//        kupac.setUloge(Set.of(Role.KUPAC));
//        kupac.setAdresa(request.getAdresa());
//
//        kupacRepository.save(kupac);
//
//        String jwt = jwtService.generateToken(kupac);
//        return new LoginResponse(jwt);
//    }
    
    public LoginResponse register(RegisterRequest request) {
        if (korisnikRepository.existsByKorisnickoIme(request.getKorisnickoIme())) {
            throw new RuntimeException("Korisničko ime već postoji.");
        }

        Set<Role> roles = request.getUloge().stream()
            .map(roleName -> roleRepository.findByName(roleName)
                .orElseThrow(() -> new RuntimeException("Uloga ne postoji: " + roleName)))
            .collect(Collectors.toSet());

//        Korisnik korisnik = new Korisnik();
        Korisnik korisnik;
        
        if (roles.stream().anyMatch(role -> role.getName().equals("AUTOR"))) {
            Autor autor = new Autor();
            autor.setIme(request.getIme());
            autor.setPrezime(request.getPrezime());
            korisnik = autor;
        } else {
            korisnik = new Korisnik();
        }
        
        korisnik.setKorisnickoIme(request.getKorisnickoIme());
        korisnik.setLozinka(passwordEncoder.encode(request.getLozinka()));
        korisnik.setUloge(roles);

        korisnikRepository.save(korisnik);

        String jwt = jwtService.generateToken(korisnik);
        return new LoginResponse(jwt);
    }


//    public LoginResponse registerAdmin(RegisterRequest request) {
//        if (korisnikRepository.existsByKorisnickoIme(request.getKorisnickoIme())) {
//            throw new RuntimeException("Korisničko ime već postoji.");
//        }
//
//        Korisnik admin = new Korisnik();
//        admin.setKorisnickoIme(request.getKorisnickoIme());
//        admin.setLozinka(passwordEncoder.encode(request.getLozinka()));
//        admin.setUloge(Set.of(Role.ADMIN));
//
//        korisnikRepository.save(admin);
//
//        String jwt = jwtService.generateToken(admin);
//        return new LoginResponse(jwt);
//    }

}
