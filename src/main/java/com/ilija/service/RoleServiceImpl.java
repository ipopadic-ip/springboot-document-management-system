package com.ilija.service;

import com.ilija.dto.RoleDTO;
import com.ilija.model.Role;
import com.ilija.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository repository;

    private RoleDTO toDTO(Role role) {
        RoleDTO dto = new RoleDTO();
        dto.setId(role.getId());
        dto.setName(role.getName());
        return dto;
    }

    private Role toEntity(RoleDTO dto) {
        Role role = new Role();
        role.setId(dto.getId());
        role.setName(dto.getName());
        return role;
    }

    @Override
    public RoleDTO create(RoleDTO dto) {
        Role role = toEntity(dto);
        return toDTO(repository.save(role));
    }

    @Override
    public RoleDTO update(Long id, RoleDTO dto) {
        Optional<Role> existing = repository.findById(id);
        if (existing.isPresent()) {
            Role role = existing.get();
            role.setName(dto.getName());
            return toDTO(repository.save(role));
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public RoleDTO findById(Long id) {
        return repository.findById(id).map(this::toDTO).orElse(null);
    }

    @Override
    public List<RoleDTO> findAll() {
        return repository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }
}
