package com.ilija.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ilija.auth.model.CustomUserDetails;
import com.ilija.auth.service.JwtAuthFilter;
import com.ilija.auth.service.JwtService;
import com.ilija.repository.KorisnikRepository;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {
	@Bean
	public UserDetailsService userDetailsService(KorisnikRepository korisnikRepository) {
	    return username -> korisnikRepository.findByKorisnickoIme(username)
	            .map(CustomUserDetails::new)
	            .orElseThrow(() -> new UsernameNotFoundException("Korisnik nije pronađen"));
	}
	
	  @Bean
	    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
	        return config.getAuthenticationManager();
	    }

	    @Bean
	    public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService,
	                                                         BCryptPasswordEncoder passwordEncoder) {
	        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
	        provider.setUserDetailsService(userDetailsService);
	        provider.setPasswordEncoder(passwordEncoder);
	        return provider;
	    }

	    @Bean
	    public BCryptPasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }



	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http,
	                                       JwtService jwtService,
	                                       KorisnikRepository korisnikRepository) throws Exception {
	    http
	            .csrf(csrf -> csrf.disable())
	            .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	            .authorizeHttpRequests(auth -> auth
	                    .requestMatchers("/api/auth/**").permitAll()
	                    .anyRequest().authenticated()
	            )
	            .addFilterBefore(new JwtAuthFilter(jwtService, korisnikRepository), UsernamePasswordAuthenticationFilter.class);

	    return http.build();
	}


}
