package com.ilija.controller;

import com.ilija.dto.RoleDTO;
import com.ilija.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
@CrossOrigin(origins = "*")
@PreAuthorize("isAuthenticated()") // svi moraju biti prijavljeni
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'MENADZER')")
    public RoleDTO create(@RequestBody RoleDTO dto) {
        return roleService.create(dto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MENADZER')")
    public RoleDTO update(@PathVariable Long id, @RequestBody RoleDTO dto) {
        return roleService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MENADZER')")
    public void delete(@PathVariable Long id) {
        roleService.delete(id);
    }

    @GetMapping("/{id}")
    public RoleDTO getById(@PathVariable Long id) {
        return roleService.findById(id);
    }

    @GetMapping
    public List<RoleDTO> getAll() {
        return roleService.findAll();
    }
}
