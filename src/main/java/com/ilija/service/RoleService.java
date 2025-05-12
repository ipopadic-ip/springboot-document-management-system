package com.ilija.service;

import java.util.List;

import com.ilija.dto.RoleDTO;

public interface RoleService {
    RoleDTO create(RoleDTO dto);
    RoleDTO update(Long id, RoleDTO dto);
    void delete(Long id);
    RoleDTO findById(Long id);
    List<RoleDTO> findAll();
}
