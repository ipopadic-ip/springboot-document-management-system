package com.ilija.service;

import com.ilija.dto.IzvestajDTO;

import java.util.List;

public interface IzvestajService {
    IzvestajDTO create(IzvestajDTO dto);
    List<IzvestajDTO> findAll();
    IzvestajDTO findById(Long id);
    IzvestajDTO update(Long id, IzvestajDTO dto);
    void delete(Long id);
}