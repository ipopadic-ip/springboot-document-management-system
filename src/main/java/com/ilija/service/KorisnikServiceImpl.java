package com.ilija.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ilija.dto.KorisnikDTO;
import com.ilija.model.Korisnik;
import com.ilija.repository.KorisnikRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class KorisnikServiceImpl implements KorisnikService {

    @Autowired
    private KorisnikRepository repository;

    private KorisnikDTO toDTO(Korisnik korisnik) {
        KorisnikDTO dto = new KorisnikDTO();
        dto.setId(korisnik.getId());
        dto.setKorisnickoIme(korisnik.getKorisnickoIme());
        dto.setUloge(korisnik.getUloge());
        return dto;
    }

    private Korisnik toEntity(KorisnikDTO dto) {
        Korisnik korisnik = new Korisnik();
        korisnik.setId(dto.getId());
        korisnik.setKorisnickoIme(dto.getKorisnickoIme());
        korisnik.setUloge(dto.getUloge());
        // lozinka se ne postavlja ovde jer bi trebalo ići kroz registration/login
        return korisnik;
    }

    @Override
    public KorisnikDTO create(KorisnikDTO dto) {
        return toDTO(repository.save(toEntity(dto)));
    }

    @Override
    public List<KorisnikDTO> findAll() {
        return repository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public KorisnikDTO findById(Long id) {
        return repository.findById(id).map(this::toDTO).orElse(null);
    }

    @Override
    public KorisnikDTO update(Long id, KorisnikDTO dto) {
        return repository.findById(id).map(existing -> {
            existing.setKorisnickoIme(dto.getKorisnickoIme());
            existing.setUloge(dto.getUloge());
            return toDTO(repository.save(existing));
        }).orElse(null);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
