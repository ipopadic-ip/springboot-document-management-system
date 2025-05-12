package com.ilija.service;

import java.util.List;

import com.ilija.dto.KorisnikDTO;

public interface KorisnikService {
    KorisnikDTO create(KorisnikDTO dto);
    List<KorisnikDTO> findAll();
    KorisnikDTO findById(Long id);
    KorisnikDTO update(Long id, KorisnikDTO dto);
    void delete(Long id);
}
