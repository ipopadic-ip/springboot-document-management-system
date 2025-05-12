package com.ilija.service;

import com.ilija.dto.IzvestajDTO;
import com.ilija.model.Izvestaj;
import com.ilija.model.Autor;
import com.ilija.repository.IzvestajRepository;
import com.ilija.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IzvestajServiceImpl implements IzvestajService {

    @Autowired
    private IzvestajRepository repository;

    @Autowired
    private AutorRepository autorRepository;

    private IzvestajDTO toDTO(Izvestaj izvestaj) {
        IzvestajDTO dto = new IzvestajDTO();
        dto.setId(izvestaj.getId());
        dto.setNaslov(izvestaj.getNaslov());
        dto.setDatumObjave(izvestaj.getDatumObjave());
        dto.setDatumBrisanja(izvestaj.getDatumBrisanja());
        dto.setAutorId(izvestaj.getAutor().getId());
        dto.setTip(izvestaj.getTip());
        dto.setSadrzaj(izvestaj.getSadrzaj());
        return dto;
    }

    private Izvestaj toEntity(IzvestajDTO dto) {
        Izvestaj izvestaj = new Izvestaj();
        izvestaj.setId(dto.getId());
        izvestaj.setNaslov(dto.getNaslov());
        izvestaj.setDatumObjave(dto.getDatumObjave());
        izvestaj.setDatumBrisanja(dto.getDatumBrisanja());
        izvestaj.setTip(dto.getTip());
        izvestaj.setSadrzaj(dto.getSadrzaj());

        Autor autor = autorRepository.findById(dto.getAutorId()).orElseThrow();
        izvestaj.setAutor(autor);

        return izvestaj;
    }

    @Override
    public IzvestajDTO create(IzvestajDTO dto) {
        return toDTO(repository.save(toEntity(dto)));
    }

    @Override
    public List<IzvestajDTO> findAll() {
        return repository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public IzvestajDTO findById(Long id) {
        return repository.findById(id).map(this::toDTO).orElse(null);
    }

    @Override
    public IzvestajDTO update(Long id, IzvestajDTO dto) {
        return repository.findById(id).map(existing -> {
            existing.setNaslov(dto.getNaslov());
            existing.setDatumObjave(dto.getDatumObjave());
            existing.setDatumBrisanja(dto.getDatumBrisanja());
            existing.setTip(dto.getTip());
            existing.setSadrzaj(dto.getSadrzaj());
            existing.setAutor(autorRepository.findById(dto.getAutorId()).orElseThrow());
            return toDTO(repository.save(existing));
        }).orElse(null);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
