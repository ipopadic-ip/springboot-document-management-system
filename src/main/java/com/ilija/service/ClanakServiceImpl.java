package com.ilija.service;

import com.ilija.dto.ClanakDTO;
import com.ilija.model.Autor;
import com.ilija.model.Clanak;
import com.ilija.repository.AutorRepository;
import com.ilija.repository.ClanakRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClanakServiceImpl implements ClanakService {

    @Autowired
    private ClanakRepository clanakRepository;

    @Autowired
    private AutorRepository autorRepository;

    private ClanakDTO toDTO(Clanak clanak) {
        ClanakDTO dto = new ClanakDTO();
        dto.setId(clanak.getId());
        dto.setNaslov(clanak.getNaslov());
        dto.setDatumObjave(clanak.getDatumObjave());
        dto.setDatumBrisanja(clanak.getDatumBrisanja());
        dto.setSadrzaj(clanak.getSadrzaj());
        if (clanak.getAutor() != null) {
            dto.setAutorId(clanak.getAutor().getId());
        }
        return dto;
    }

    private Clanak toEntity(ClanakDTO dto) {
        Clanak clanak = new Clanak();
        clanak.setId(dto.getId());
        clanak.setNaslov(dto.getNaslov());
        clanak.setDatumObjave(dto.getDatumObjave());
        clanak.setDatumBrisanja(dto.getDatumBrisanja());
        clanak.setSadrzaj(dto.getSadrzaj());

        if (dto.getAutorId() != null) {
            Autor autor = autorRepository.findById(dto.getAutorId()).orElse(null);
            clanak.setAutor(autor);
        }

        return clanak;
    }

    @Override
    public ClanakDTO create(ClanakDTO dto) {
        return toDTO(clanakRepository.save(toEntity(dto)));
    }

    @Override
    public List<ClanakDTO> findAll() {
        return clanakRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public ClanakDTO findById(Long id) {
        return clanakRepository.findById(id).map(this::toDTO).orElse(null);
    }

    @Override
    public ClanakDTO update(Long id, ClanakDTO dto) {
        return clanakRepository.findById(id).map(existing -> {
            existing.setNaslov(dto.getNaslov());
            existing.setDatumObjave(dto.getDatumObjave());
            existing.setDatumBrisanja(dto.getDatumBrisanja());
            existing.setSadrzaj(dto.getSadrzaj());

            if (dto.getAutorId() != null) {
                Autor autor = autorRepository.findById(dto.getAutorId()).orElse(null);
                existing.setAutor(autor);
            }

            return toDTO(clanakRepository.save(existing));
        }).orElse(null);
    }

    @Override
    public void delete(Long id) {
        clanakRepository.deleteById(id);
    }
}
