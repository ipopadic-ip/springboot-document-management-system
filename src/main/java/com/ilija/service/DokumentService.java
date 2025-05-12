package com.ilija.service;

import com.ilija.dto.DokumentDTO;

import java.time.LocalDate;
import java.util.List;

public interface DokumentService {
    DokumentDTO create(DokumentDTO dto);
    List<DokumentDTO> findAll();
    DokumentDTO findById(Long id);
    DokumentDTO update(Long id, DokumentDTO dto);
    void delete(Long id);
    public List<DokumentDTO> pretrazi(String naslov, LocalDate datumOd, LocalDate datumDo, String korisnickoIme, boolean jeAdminIliMenadzer);

}
