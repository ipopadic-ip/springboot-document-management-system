package com.ilija.auth.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.ilija.auth.dto.LoginRequest;
import com.ilija.auth.dto.LoginResponse;
import com.ilija.auth.dto.RegisterRequest;
import com.ilija.auth.service.AuthService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {
	@Autowired private AuthService authService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }
    
  @PostMapping("/register")
  public LoginResponse registerAdmin(@Validated @RequestBody RegisterRequest request) {
      return authService.register(request);
  }
    
//    @PostMapping("/register-kupac")
//    public LoginResponse registerKupac(@Validated @RequestBody RegisterRequest request) {
//        return authService.registerKupac(request);
//    }

//    @PostMapping("/register-admin")
//    public LoginResponse registerAdmin(@Validated @RequestBody RegisterRequest request) {
//        return authService.registerAdmin(request);
//    }
}
