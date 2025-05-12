package com.ilija.controller;

import com.ilija.dto.ClanakDTO;
import com.ilija.service.ClanakService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clanci")
@CrossOrigin(origins = "*")
public class ClanakController {

    @Autowired
    private ClanakService clanakService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'MENADZER', 'AUTOR')")
    public ClanakDTO create(@RequestBody ClanakDTO dto) {
        return clanakService.create(dto);
    }

    @GetMapping
    public List<ClanakDTO> getAll() {
        return clanakService.findAll();
    }

    @GetMapping("/{id}")
    public ClanakDTO getById(@PathVariable Long id) {
        return clanakService.findById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MENADZER')")
    public ClanakDTO update(@PathVariable Long id, @RequestBody ClanakDTO dto) {
        return clanakService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MENADZER')")
    public void delete(@PathVariable Long id) {
        clanakService.delete(id);
    }
}
