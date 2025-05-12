package com.ilija.controller;

import com.ilija.dto.DokumentDTO;
import com.ilija.service.DokumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/dokumenti")
@CrossOrigin(origins = "*")
public class DokumentController {

    @Autowired
    private DokumentService service;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'MENADZER')")
    public DokumentDTO create(@RequestBody DokumentDTO dto) {
        return service.create(dto);
    }

    @GetMapping
    public List<DokumentDTO> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public DokumentDTO getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MENADZER')")
    public DokumentDTO update(@PathVariable Long id, @RequestBody DokumentDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MENADZER')")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
    
    @GetMapping("/pretraga")
    public List<DokumentDTO> pretraga(
            @RequestParam(required = false) String naslov,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate datumOd,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate datumDo,
            Authentication authentication) {

        String korisnickoIme = authentication.getName();
        boolean adminIliMenadzer = authentication.getAuthorities().stream()
            .anyMatch(ga -> ga.getAuthority().equals("ROLE_ADMIN") || ga.getAuthority().equals("ROLE_MENADZER"));

        return service.pretrazi(naslov, datumOd, datumDo, korisnickoIme, adminIliMenadzer);
    }

}
