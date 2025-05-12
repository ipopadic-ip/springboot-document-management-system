package com.ilija.controller;

import com.ilija.dto.IzvestajDTO;
import com.ilija.service.IzvestajService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@RestController
@RequestMapping("/api/izvestaji")
@CrossOrigin(origins = "*")
public class IzvestajController {

    @Autowired
    private IzvestajService service;

    @PostMapping
//    @PreAuthorize("hasRole('ADMIN')")
    @PreAuthorize("hasAnyRole('ADMIN', 'MENADZER')")
    public IzvestajDTO create(@RequestBody IzvestajDTO dto) {
        return service.create(dto);
    }

    @GetMapping
    public List<IzvestajDTO> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public IzvestajDTO getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MENADZER')")
    public IzvestajDTO update(@PathVariable Long id, @RequestBody IzvestajDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MENADZER')")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
