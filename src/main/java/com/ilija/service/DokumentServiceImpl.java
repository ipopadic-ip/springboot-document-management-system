package com.ilija.service;

import com.ilija.dto.DokumentDTO;
import com.ilija.model.Dokument;
import com.ilija.model.Izvestaj;
import com.ilija.repository.AutorRepository;
import com.ilija.repository.DokumentRepository;
import com.ilija.repository.IzvestajRepository;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DokumentServiceImpl implements DokumentService {

    @Autowired
    private DokumentRepository repository;
    
    @Autowired
    private IzvestajRepository izvestajRepository;

    @Autowired
    private AutorRepository autorRepository;


    private DokumentDTO toDTO(Dokument dokument) {
        DokumentDTO dto = new DokumentDTO();
        dto.setId(dokument.getId());
        dto.setNaslov(dokument.getNaslov());
        dto.setDatumObjave(dokument.getDatumObjave());
        dto.setDatumBrisanja(dokument.getDatumBrisanja());
        return dto;
    }

    private Dokument toEntity(DokumentDTO dto) {
        return new Dokument() {{
            setId(dto.getId());
            setNaslov(dto.getNaslov());
            setDatumObjave(dto.getDatumObjave());
            setDatumBrisanja(dto.getDatumBrisanja());
        }};
    }

    @Override
    public DokumentDTO create(DokumentDTO dto) {
        return toDTO(repository.save(toEntity(dto)));
    }

    @Override
    public List<DokumentDTO> findAll() {
        return repository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public DokumentDTO findById(Long id) {
        return repository.findById(id).map(this::toDTO).orElse(null);
    }

    @Override
    public DokumentDTO update(Long id, DokumentDTO dto) {
        return repository.findById(id).map(existing -> {
            existing.setNaslov(dto.getNaslov());
            existing.setDatumObjave(dto.getDatumObjave());
            existing.setDatumBrisanja(dto.getDatumBrisanja());

            DokumentDTO updated = toDTO(repository.save(existing));

            // Kreiraj izveštaj
            kreirajIzvestaj(SecurityContextHolder.getContext().getAuthentication().getName(), "IZMENA", existing);

            return updated;
        }).orElse(null);
    }


    @Override
    public void delete(Long id) {
        repository.findById(id).ifPresent(dokument -> {
            repository.deleteById(id);

            // Kreiraj izveštaj
            kreirajIzvestaj(SecurityContextHolder.getContext().getAuthentication().getName(), "BRISANJE", dokument);
        });
    }

    
    @Override
    public List<DokumentDTO> pretrazi(String naslov, LocalDate datumOd, LocalDate datumDo, String korisnickoIme, boolean jeAdminIliMenadzer) {
        String autor = jeAdminIliMenadzer ? null : korisnickoIme;
        return repository
            .pretraziDokumente(naslov, datumOd, datumDo, autor)
            .stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
    }
    
    private void kreirajIzvestaj(String korisnickoIme, String tipOperacije, Dokument dokument) {
        Izvestaj izvestaj = new Izvestaj();
        izvestaj.setTip(tipOperacije);
        izvestaj.setSadrzaj("Korisnik " + korisnickoIme + " izvršio je operaciju " + tipOperacije + " nad dokumentom " + dokument.getNaslov());
        izvestaj.setDatumObjave(LocalDate.now());
        izvestaj.setDatumBrisanja(null); 
        izvestaj.setNaslov("Izveštaj o " + tipOperacije.toLowerCase() + " dokumenta");

        izvestaj.setAutor(null); // eksplicitno, po zahtevu zadatka

        izvestajRepository.save(izvestaj);
    }



}
