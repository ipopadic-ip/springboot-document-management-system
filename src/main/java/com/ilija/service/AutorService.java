package com.ilija.service;

import com.ilija.dto.AutorDTO;
import java.util.List;

public interface AutorService {
    AutorDTO create(AutorDTO dto);
    List<AutorDTO> findAll();
    AutorDTO findById(Long id);
    AutorDTO update(Long id, AutorDTO dto);
    void delete(Long id);
}
