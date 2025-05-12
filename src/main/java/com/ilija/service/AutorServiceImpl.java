package com.ilija.service;

import com.ilija.dto.AutorDTO;
import com.ilija.model.Autor;
import com.ilija.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AutorServiceImpl implements AutorService {

    @Autowired
    private AutorRepository repository;

    private AutorDTO toDTO(Autor autor) {
        AutorDTO dto = new AutorDTO();
        dto.setId(autor.getId());
        dto.setKorisnickoIme(autor.getKorisnickoIme());
        dto.setUloge(autor.getUloge());
        dto.setIme(autor.getIme());
        dto.setPrezime(autor.getPrezime());
        return dto;
    }

    private Autor toEntity(AutorDTO dto) {
        Autor autor = new Autor();
        autor.setId(dto.getId());
        autor.setKorisnickoIme(dto.getKorisnickoIme());
        autor.setUloge(dto.getUloge());
        autor.setIme(dto.getIme());
        autor.setPrezime(dto.getPrezime());
        return autor;
    }

    @Override
    public AutorDTO create(AutorDTO dto) {
        return toDTO(repository.save(toEntity(dto)));
    }

    @Override
    public List<AutorDTO> findAll() {
        return repository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public AutorDTO findById(Long id) {
        return repository.findById(id).map(this::toDTO).orElse(null);
    }

    @Override
    public AutorDTO update(Long id, AutorDTO dto) {
        return repository.findById(id).map(existing -> {
            existing.setKorisnickoIme(dto.getKorisnickoIme());
            existing.setIme(dto.getIme());
            existing.setPrezime(dto.getPrezime());
            return toDTO(repository.save(existing));
        }).orElse(null);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
