package com.ilija.service;

import com.ilija.dto.ClanakDTO;
import java.util.List;

public interface ClanakService {
    ClanakDTO create(ClanakDTO dto);
    List<ClanakDTO> findAll();
    ClanakDTO findById(Long id);
    ClanakDTO update(Long id, ClanakDTO dto);
    void delete(Long id);
}
