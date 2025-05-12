package com.ilija.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.ilija.dto.KorisnikDTO;
import com.ilija.service.KorisnikService;

import java.util.List;

@RestController
@RequestMapping("/api/korisnici")
@CrossOrigin(origins = "*")
public class KorisnikController {

    @Autowired
    private KorisnikService service;

//    @PostMapping
//    @PreAuthorize("hasRole('ADMIN')")
//    public KorisnikDTO create(@RequestBody KorisnikDTO dto) {
//        return service.create(dto);
//    }

    @GetMapping
    public List<KorisnikDTO> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public KorisnikDTO getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MENADZER')")
    public KorisnikDTO update(@PathVariable Long id, @RequestBody KorisnikDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MENADZER')")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
