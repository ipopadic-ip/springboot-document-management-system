package com.ilija.controller;

import com.ilija.dto.AutorDTO;
import com.ilija.service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/autori")
@CrossOrigin(origins = "*")
public class AutorController {

    @Autowired
    private AutorService service;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'MENADZER')")
    public AutorDTO create(@RequestBody AutorDTO dto) {
        return service.create(dto);
    }

    @GetMapping
    public List<AutorDTO> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public AutorDTO getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MENADZER')")
    public AutorDTO update(@PathVariable Long id, @RequestBody AutorDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MENADZER')")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
