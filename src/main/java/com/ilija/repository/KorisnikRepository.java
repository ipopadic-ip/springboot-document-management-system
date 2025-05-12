package com.ilija.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ilija.model.Korisnik;

//public interface KorisnikRepository extends JpaRepository<Korisnik, Long> {
//}
public interface KorisnikRepository extends JpaRepository<Korisnik, Long> {
    Optional<Korisnik> findByKorisnickoIme(String korisnickoIme);
    boolean existsByKorisnickoIme(String korisnickoIme);

}
